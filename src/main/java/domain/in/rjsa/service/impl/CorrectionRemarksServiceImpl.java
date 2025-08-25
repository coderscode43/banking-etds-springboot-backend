package domain.in.rjsa.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.controller.DownloadCertificateController;
import domain.in.rjsa.dao.BranchDao;
import domain.in.rjsa.dao.CorrectionRemarksDao;
import domain.in.rjsa.dao.CorrectionRequestDao;
import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.dao.UserDetailsDao;
import domain.in.rjsa.email.CorrectionRequestMail;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.CorrectionRemarks;
import domain.in.rjsa.model.form.CorrectionRequest;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.CorrectionRemarksService;
import domain.in.rjsa.util.StaticData;
import domain.in.rjsa.web.ApplicationCache;

@Transactional("transactionManager")
@Service("correctionRemarkService")
public class CorrectionRemarksServiceImpl extends AbstractServiceForm<Long, CorrectionRemarks, CorrectionRemarksDao>
		implements CorrectionRemarksService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CorrectionRemarksDao dao;

	@Autowired
	CorrectionRequestDao crDao;

	@Autowired
	UserDetailsDao uDao;

	@Autowired
	BranchDao brDao;

	@Autowired
	StaticDataDao sDao;

	@Autowired
	BranchDao bDao;

	@Autowired
	ApplicationCache applicationCache;

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		return null;
	}

	@Override
	public CorrectionRemarksDao getPrimaryDao() {
		return dao;
	}

	@Override
	public List<CorrectionRemarks> findForm(HashMap<String, Object> constrains) {
		return dao.findForm(constrains);
	}

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		return dao.search(map, pageNo, resultPerPage);
	}

	@Override
	public void SaveRemarkWithDocument(MultipartFile downloadFile, Long branchCode, Long crId, String remark,
			String principal, String status, String fy, String quarter) {
		try {
			CorrectionRemarks cr = new CorrectionRemarks();
			String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
			cr.setBranchCode(branchCode);
			cr.setCorrectionRequestId(crId);
			cr.setCorrectionRemark(remark);
			cr.setSupportingDocName(downloadFile.getOriginalFilename());
			cr.setDateTime(timeStamp);
			cr.setRemarkStatus(status);
			cr.setAddedBy(principal);
			CorrectionRequest crt = crDao.getByKey(crId);
			if (crt.getStatus().equalsIgnoreCase("Sent for Correction")) {
				Branch b = brDao.getByKey(crt.getBranchCode());
				String[] file = downloadFile.getOriginalFilename().split(Pattern.quote("_"), -1);
				String p = StaticData.CertificatePath;
				String cfp = "";
				if(downloadFile.getOriginalFilename().endsWith(".zip")) {
					String documentSavePath = StaticData.documentSave;
					if (documentSavePath != null) {
						cfp = documentSavePath + "//" + date + "//" + fy + "//" + quarter.replace(", ", "_") + "//"
								+ branchCode + "//CorrectionRequest//" + crt.getTicketNumber() + "//RemarkDocument";
					}else {
						throw new CustomException("Document save path not found");
					}
				}else {
					cfp = p + "download/" + fy + "//" + file[1] + "//" + "Form16A//" + b.getTan();
				}
				InputStream inputStream = downloadFile.getInputStream();
				logger.info(cfp);
				
				File path = new File(cfp.toString());
				if (!path.exists()) {
					path.mkdirs();
				}
				
				Path fp = Paths.get(cfp, downloadFile.getOriginalFilename());
				logger.info(fp.toString());
				try {
					Files.copy(inputStream, fp);
				} catch (Exception e) {
					e.printStackTrace();
				}
//				downloadFile.transferTo(form);
				saveRemark(cr, principal);
				if (crt.getPan() != null) {
					if (crt.getPan().equalsIgnoreCase(file[0])) {
						if (crt.getTan() != null) {
							DownloadCertificateController dc = new DownloadCertificateController();
							dc.createBranchZip(null, null, crt.getTan(), "Form16A", fy, file[1]);
						} else {
							DownloadCertificateController dc = new DownloadCertificateController();
							dc.createBranchZip(null, null, b.getTan(), "Form16A", fy, file[1]);
						}
					} else {
						Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
						Matcher matcher = pattern.matcher(file[0]);
						if (matcher.matches()) {
							DownloadCertificateController dc = new DownloadCertificateController();
							dc.createBranchZip(null, null, b.getTan(), "Form16A", fy, file[1]);
						}
					}
				} else {
					Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
					Matcher matcher = pattern.matcher(file[0]);
					if (matcher.matches()) {
						DownloadCertificateController dc = new DownloadCertificateController();
						dc.createBranchZip(null, null, b.getTan(), "Form16A", fy, file[1]);
					}

				}
			} else {
				String path = StaticData.documentSave;
				if (path != null) {
					String filepath = path + "//" + date + "//" + fy + "//" + quarter.replace(", ", "_") + "//"
							+ branchCode + "//CorrectionRequest//" + crt.getTicketNumber() + "//RemarkDocument";
					File file = new File(filepath.toString());
					if (!file.exists()) {
						file.mkdirs();
					}
					file = new File(file + "//" + downloadFile.getOriginalFilename());
					InputStream inputStream = downloadFile.getInputStream();
					logger.info(filepath);
					Path fp = Paths.get(filepath, downloadFile.getOriginalFilename());
					logger.info(fp.toString());
					try {
						Files.copy(inputStream, fp);
					} catch (Exception e) {
						e.printStackTrace();
					}
//					downloadFile.transferTo(file);
					saveRemark(cr, principal);
				} else {
					throw new CustomException("Document save path not found");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveRemark(CorrectionRemarks entity, String principal) {
		CorrectionRequest cr = crDao.getByKey(entity.getCorrectionRequestId());
		String makerBy = cr.getMakerBy();
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		String status = entity.getRemarkStatus();
		if (!makerBy.equalsIgnoreCase(principal)) {
			if (status.equalsIgnoreCase("Approved")) {
				if (cr.getCheckerApprovedBy() == null) {
					entity.setDateTime(timeStamp);
					entity.setAddedBy(principal);
					dao.persist(entity);
					cr.setCheckerApprovedBy(principal);
					cr.setCheckerApprovedOn(new Date());
					cr.setStatus("Correction Checked");
					cr.setTaxTeamApprovedBy(principal);
					cr.setTaxTeamApprovedOn(new Date());
					cr.setLastUpdatedOn(new Date());
					crDao.update(cr);
					try {
						sendMail(cr);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (cr.getCorrectionBy() == null) {
					entity.setDateTime(timeStamp);
					entity.setAddedBy(principal);
					dao.persist(entity);
					cr.setStatus("Sent for Correction");
					cr.setCorrectionBy(principal);
					cr.setCorrectionOn(new Date());
					cr.setLastUpdatedOn(new Date());
					crDao.update(cr);
					try {
						sendMail(cr);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (!cr.correctionStatus && cr.getCorrectionBy() != null) {
					entity.setDateTime(timeStamp);
					entity.setAddedBy(principal);
					entity.setRemarkStatus("Resolved");
					dao.persist(entity);
					cr.setCorrectionStatus(true);
					cr.setLastUpdatedOn(new Date());
					cr.setStatus("Resolved");
					crDao.update(cr);
					try {
						sendMail(cr);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (status.equalsIgnoreCase("Rejected")) {
				entity.setDateTime(timeStamp);
				entity.setAddedBy(principal);
				dao.persist(entity);
				cr.setStatus("Rejected");
				cr.setCheckerApprovedBy(principal);
				cr.setCheckerApprovedOn(new Date());
				cr.setLastUpdatedOn(new Date());
				cr.setRejectStatus(true);
				cr.setRegenarateRequest(false);
				crDao.update(cr);
				try {
					sendMail(cr);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (status.equalsIgnoreCase("Remark")) {
				entity.setDateTime(timeStamp);
				entity.setAddedBy(principal);
				dao.persist(entity);
				cr.setStatus("Sent for Clarification");
				cr.setLastUpdatedOn(new Date());
				crDao.update(cr);
			}
		} else {
			if (status.equalsIgnoreCase("Remark")) {
				entity.setDateTime(timeStamp);
				entity.setAddedBy(principal);
				dao.persist(entity);
				cr.setStatus("Pending Checker Approval");
				cr.setLastUpdatedOn(new Date());
				crDao.update(cr);
			} else {
				throw new CustomException("Branch can not approved/Reject own correction request.");
			}
		}
	}

	@Override
	public void downloadDocument(Long id, HttpServletResponse response) {
		try {
			CorrectionRemarks cr = dao.getByKey(id);
			CorrectionRequest c = crDao.getByKey(cr.getCorrectionRequestId());
			if (cr.getRemarkStatus().equalsIgnoreCase("Resolved") && !cr.getSupportingDocName().endsWith(".zip")) {
				String path = StaticData.CertificatePath;
				if (path != null) {
					Branch b = brDao.getByKey(c.getBranchCode());
					String[] fName = cr.getSupportingDocName().split(Pattern.quote("_"), -1);
					String filePath = path + "download/" + c.fy + "//" + fName[1] + "//" + "Form16A//" + b.getTan()
							+ "//" + cr.getSupportingDocName();
					logger.info(filePath);
					File file = new File(filePath);
					byte[] fileContent;
					fileContent = Files.readAllBytes(file.toPath());
					String mimeType = URLConnection.guessContentTypeFromName(cr.getSupportingDocName());
					response.setContentType(mimeType);
					response.setStatus(HttpServletResponse.SC_OK);
					response.setHeader("Content-Disposition", " attachment; filename=" + cr.getSupportingDocName());
					response.getOutputStream().write(fileContent);
					response.getOutputStream().close();
				} else {
					throw new CustomException("Certificate Path Not Found");
				}
			} else {
				String path = StaticData.documentSave;
				if (path != null) {
					String date = cr.getDateTime();

			        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
					String date1 = dateTime.toLocalDate().format(outputFormatter);

					String filePath = path + "//" + date1 + "//" + c.getFy() + "//" + c.getQuarter().replace(", ", "_")
							+ "//" + cr.getBranchCode() + "//CorrectionRequest//" + c.getTicketNumber()
							+ "//RemarkDocument//" + cr.getSupportingDocName();
					logger.info(filePath);
					File file = new File(filePath);
					byte[] fileContent;
					fileContent = Files.readAllBytes(file.toPath());
					String mimeType = URLConnection.guessContentTypeFromName(cr.getSupportingDocName());
					response.setContentType(mimeType);
					response.setStatus(HttpServletResponse.SC_OK);
					response.setHeader("Content-Disposition", " attachment; filename=" + cr.getSupportingDocName());
					response.getOutputStream().write(fileContent);
					response.getOutputStream().close();
				} else {
					throw new CustomException("Document Save Path Not Found");
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMail(CorrectionRequest cr) {
		CorrectionRequestMail crm = new CorrectionRequestMail();
		HashMap<String, String> map = new HashMap<String, String>();
		String branchCode = cr.getBranchCode().toString();
		Branch branchModel = getBranch(Long.parseLong(branchCode));
		String emailTo = branchModel.getBranchEmail();
		map.put("emailTo", emailTo);
		map.put("subject", "Correction Request [" + cr.getTicketNumber() + "]");
		map.put("message",
				"We wish to inform you that the status of the correction request has been changed as mentioned below,");
		crm.sendEmail(map, cr);

	}

	public Branch getBranch(long branchCode) {
		return bDao.getByKey(branchCode);
	}

}
