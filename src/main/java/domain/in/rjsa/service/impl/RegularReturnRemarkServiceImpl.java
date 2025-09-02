package domain.in.rjsa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.dao.BranchDao;
import domain.in.rjsa.dao.RegularReturnDao;
import domain.in.rjsa.dao.RegularReturnRemarkDao;
import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.email.QueryMail;
import domain.in.rjsa.email.ReminderMail;
import domain.in.rjsa.email.ReturnFiledMail;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.RegularReturn;
import domain.in.rjsa.model.form.RegularReturnRemark;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.RegularReturnRemarkService;
import domain.in.rjsa.util.StaticData;
import jakarta.servlet.http.HttpServletResponse;

@Transactional("transactionManager")
@Service("regularReturnRemark")
public class RegularReturnRemarkServiceImpl extends
		AbstractServiceForm<Long, RegularReturnRemark, RegularReturnRemarkDao> implements RegularReturnRemarkService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	RegularReturnRemarkDao dao;

	@Autowired
	RegularReturnDao rDao;

	@Autowired
	BranchDao bDao;

	@Autowired
	StaticDataDao sDao;

	@Autowired
	ReminderMail reminderMail;

	@Autowired
	QueryMail queryMail;

	@Autowired
	ReturnFiledMail returnFiledMail;

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		return null;
	}

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		return dao.search(map);
	}

	@Override
	public RegularReturnRemarkDao getPrimaryDao() {
		return dao;
	}

	@Override
	public void save(RegularReturnRemark entity) {
		getPrimaryDao().persist(entity);

	}

	@Override
	public void save(LinkedHashMap<String, Object> map, String principal) {
		try {
			String jsonElement = mapper.writeValueAsString(map);
			RegularReturnRemark rrr = mapper.readValue(jsonElement, RegularReturnRemark.class);
			RegularReturn r = rDao.getByKey(rrr.getRegularReturnId());
			rrr.setRoCode(r.getBranchCode());
			rrr.setAddedBy(principal);
			rrr.setAddedOn(new Date());
			dao.persist(rrr);
			r.setLatestRemark(rrr.getRemark());
			r.setStatus(rrr.getRemarkStatus());
			if (map.containsKey("returnFilingDate")) {
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    Date date = dateFormat.parse(map.get("returnFilingDate").toString());

			    // Create a Calendar object and set it to the date
			    Calendar calendar = Calendar.getInstance();
			    calendar.setTime(date);

			    // Add one day
			    calendar.add(Calendar.DAY_OF_MONTH, 1);

			    // Get the updated date
			    Date updatedDate = calendar.getTime();
			    r.setReturnFilingDate(updatedDate);
				
			}
			rDao.update(r);
			if (map.get("remarkStatus").equals("Certificate Generated")) {
				HashMap<String, String> mailMap = new HashMap<String, String>();
				Branch branchModel = getBranch(Long.parseLong(r.getBranchCode()));
				String emailTo = branchModel.getBranchEmail();
				mailMap.put("emailTo", emailTo);
				mailMap.put("subject",
						"Issuance of TDS/TCS Certificate for Regular Return of TAN : "
								+ r.getTan().split(Pattern.quote("-"), -1)[0] + " for FY :" + r.getFy() + ", "
								+ r.getQuarter() + ", " + r.getForm());
				mailMap.put("fy", r.getFy());
				mailMap.put("quarter", r.getQuarter());
				mailMap.put("form", r.getForm());
				mailMap.put("tan", r.getTan());
				returnFiledMail.sendEmail(mailMap);
			}
			if (map.get("remarkStatus").toString().equalsIgnoreCase("Waiting for Query Reply")) {
				HashMap<String, String> mailMap = new HashMap<String, String>();
				Branch branchModel = getBranch(Long.parseLong(r.getBranchCode()));
				String emailTo = branchModel.getBranchEmail();
				mailMap.put("emailTo", emailTo);
				mailMap.put("subject", "Immediate Action Required: TDS/TCS Regular Return Query for FY : " + r.getFy()
						+ ", " + r.getQuarter() + ", " + r.getForm());
				mailMap.put("fy", r.getFy());
				mailMap.put("quarter", r.getQuarter());
				mailMap.put("form", r.getForm());
				mailMap.put("tan", r.getTan());
				queryMail.sendEmail(mailMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveWithFile(MultipartFile blob, LinkedHashMap<String, Object> map, String principal) {
		try {
			map.put("supportingDocName", blob.getOriginalFilename());
			String jsonElement = mapper.writeValueAsString(map);
			RegularReturnRemark rrr = mapper.readValue(jsonElement, RegularReturnRemark.class);
			RegularReturn r = rDao.getByKey(rrr.getRegularReturnId());
			rrr.setRoCode(r.getBranchCode());
			rrr.setAddedBy(principal);
			rrr.setAddedOn(new Date());
			saveFile(blob, rrr);
			if (rrr.getRemarkStatus() != null) {
//				if (rrr.getRemarkStatus().equalsIgnoreCase("Return filed")) {
//					HashMap<String, String> mailMap = new HashMap<String, String>();
//					Branch branchModel = getBranch(Long.parseLong(r.getBranchCode()));
//					String emailTo = branchModel.getBranchEmail();
//					mailMap.put("emailTo", emailTo);
//					mailMap.put("subject", "Return Filed");
//					mailMap.put("fy", r.getFy());
//					mailMap.put("quarter", r.getQuarter());
//					mailMap.put("form", r.getForm());
//					mailMap.put("tan", r.getTan());
//					if (map.containsKey("returnFilingDate")) {
//						mailMap.put("returnFilingDate", map.get("returnFilingDate").toString());
//					}
//					returnFiledMail.sendEmail(mailMap, r, rrr);
//				}
//
				if (rrr.getRemarkStatus().equalsIgnoreCase("Certificate Generated")) {
					HashMap<String, String> mailMap = new HashMap<String, String>();
					Branch branchModel = getBranch(Long.parseLong(r.getBranchCode()));
					String emailTo = branchModel.getBranchEmail();
					mailMap.put("emailTo", emailTo);
					mailMap.put("subject",
							"Issuance of TDS/TCS Certificate for Regular Return of TAN : "
									+ r.getTan().split(Pattern.quote("-"), -1)[0] + " for FY :" + r.getFy() + ", "
									+ r.getQuarter() + ", " + r.getForm());
					mailMap.put("fy", r.getFy());
					mailMap.put("quarter", r.getQuarter());
					mailMap.put("form", r.getForm());
					mailMap.put("tan", r.getTan());
					logger.info("Certificate generated Mail Details send to Abstract mail");
					returnFiledMail.sendEmail(mailMap);
				}
				if (rrr.getRemarkStatus().toString().equalsIgnoreCase("Waiting for Query Reply")) {
					HashMap<String, String> mailMap = new HashMap<String, String>();
					Branch branchModel = getBranch(Long.parseLong(r.getBranchCode()));
					String emailTo = branchModel.getBranchEmail();
					mailMap.put("emailTo", emailTo);
					mailMap.put("subject", "Immediate Action Required: TDS/TCS Regular Return Query for FY : "
							+ r.getFy() + ", " + r.getQuarter() + ", " + r.getForm());
					mailMap.put("fy", r.getFy());
					mailMap.put("quarter", r.getQuarter());
					mailMap.put("form", r.getForm());
					mailMap.put("tan", r.getTan());
					logger.info("Waiting for Query Reply Mail Details send to Abstract mail");
					queryMail.sendEmail(mailMap);
				}
			}
			dao.persist(rrr);
			r.setLatestRemark(rrr.getRemark());
			r.setStatus(rrr.getRemarkStatus());
			if (map.containsKey("returnFilingDate")) {
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    Date date = dateFormat.parse(map.get("returnFilingDate").toString());

			    // Create a Calendar object and set it to the date
			    Calendar calendar = Calendar.getInstance();
			    calendar.setTime(date);

			    // Add one day
			    calendar.add(Calendar.DAY_OF_MONTH, 1);

			    // Get the updated date
			    Date updatedDate = calendar.getTime();
			    r.setReturnFilingDate(updatedDate);
			}

			rDao.update(r);

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

	}

	public void saveFile(MultipartFile file, RegularReturnRemark rrr) {
		try {
			RegularReturn r = rDao.getByKey(rrr.getRegularReturnId());
			String path = StaticData.documentSave;
			SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
			if (path != null) {
				String filepath = path + "//" + date.format(rrr.getAddedOn()) + "//" + r.getFy() + "//" + r.getQuarter()
						+ "//" + rrr.getRoCode() + "//RegularReturn//" + r.getForm();
				File files = new File(filepath.toString());
				if (!files.exists()) {
					files.mkdirs();
				}
//				File f = new File(filepath + "//" + file.getOriginalFilename());
				InputStream inputStream = file.getInputStream();
				logger.info(filepath);
				Path fp = Paths.get(filepath, file.getOriginalFilename());
				logger.info(fp.toString());
				try {
					Files.copy(inputStream, fp);
				} catch (Exception e) {
					e.printStackTrace();
				}
//				file.transferTo(f);
			} else {
				throw new CustomException("Docment Save Path Not Found!");
			}
		} catch (IOException e) {
			e.printStackTrace();
			rrr.setSupportingDocName("");
		}
	}

	public void downloadDocument(Long id, HttpServletResponse response) {
		try {
			RegularReturnRemark rr = dao.getByKey(id);
			RegularReturn r = rDao.getByKey(rr.getRegularReturnId());
			String path = StaticData.documentSave;
			SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");

			String filePath = null;
			String[] docArray = rr.getSupportingDocName().split(Pattern.quote(","), -1);

			if (docArray.length > 1) {
				response.setStatus(HttpServletResponse.SC_OK);
				response.addHeader("Content-Disposition",
						"attachment; filename=" + r.getTan().split(Pattern.quote("-"), -1)[0] + ".zip");
				response.addHeader("Content-Type", "application/zip");

				try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
					for (String doc : docArray) {
						filePath = path + "/" + date.format(rr.getAddedOn()) + "/" + r.fy + "/" + r.quarter + "/"
								+ rr.getRoCode() + "/RegularReturn/" + r.form + "/" + doc;

						File file = new File(filePath);
						if (file.exists()) {
							try (FileInputStream fileInputStream = new FileInputStream(file)) {
								ZipEntry entry = new ZipEntry(doc);
								zipOutputStream.putNextEntry(entry);

								IOUtils.copy(fileInputStream, zipOutputStream);
								zipOutputStream.closeEntry();
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				filePath = path + "/" + date.format(rr.getAddedOn()) + "/" + r.fy + "/" + r.quarter + "/"
						+ rr.getRoCode() + "/RegularReturn/" + r.form + "/" + rr.getSupportingDocName();

				File file = new File(filePath);
				FileInputStream fileInputStream = new FileInputStream(file);
				response.setStatus(HttpServletResponse.SC_OK);
				response.setHeader("Content-Disposition", " attachment; filename=" + rr.getSupportingDocName());
				IOUtils.copy(fileInputStream, response.getOutputStream());
				fileInputStream.close();
				response.getOutputStream().close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveBulkRemark(LinkedHashMap<String, Object> entity, String principal) {
		try {
			String jsonElement = mapper.writeValueAsString(entity.get("regularReturns"));
			List<?> list = mapper.readValue(jsonElement, ArrayList.class);
			for (Object l : list) {
				String json = mapper.writeValueAsString(l);
				RegularReturn rr = mapper.readValue(json, RegularReturn.class);
				RegularReturnRemark rrr = new RegularReturnRemark();
				if (entity.containsKey("Status")) {
					rr.setStatus(entity.get("Status").toString());
					rrr.setRemarkStatus(entity.get("Status").toString());
				}
				if (entity.containsKey("returnFilingDate")) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date = dateFormat.parse(entity.get("returnFilingDate").toString());
					rr.setReturnFilingDate(date);
				}
				if (entity.containsKey("remark")) {
					rr.setLatestRemark(entity.get("remark").toString());
					rrr.setRemark(entity.get("remark").toString());
				}
				rDao.update(rr);
				rrr.setAddedBy(principal);
				rrr.setAddedOn(new Date());
				rrr.setRegularReturnId(rr.getId());
				rrr.setRoCode(rr.getBranchCode());
				dao.persist(rrr);
				if (entity.containsKey("Status")) {
//					if (entity.get("Status").toString().equalsIgnoreCase("Request for data from RO")) {
//						HashMap<String, String> map = new HashMap<String, String>();
//						String arr[] = l.toString().split(Pattern.quote(","), -1);
//						for (String e : arr) {
//							String a[] = e.split(Pattern.quote("="));
//							map.put(a[0].trim().replace("{", ""), a[1].trim().replace("}", ""));
//						}
//						String branchCode = map.get("branchCode").toString();
//						Branch branchModel = getBranch(Long.parseLong(branchCode));
//						String emailTo = branchModel.getBranchEmail();
//						map.put("emailTo", emailTo);
//						map.put("subject", "Request for " + map.get("quarter") + ", " + map.get("form")
//								+ " Return Data Files for F.Y. " + map.get("fy") + ", " + map.get("form"));
//						reminderMail.sendEmail(map);
//					}
					if (entity.get("Status").toString().equalsIgnoreCase("Waiting for Query Reply")) {
						HashMap<String, String> map = new HashMap<String, String>();
						String arr[] = l.toString().split(Pattern.quote(","), -1);
						for (String e : arr) {
							String a[] = e.split(Pattern.quote("="));
							map.put(a[0].trim().replace("{", ""), a[1].trim().replace("}", ""));
						}
						String branchCode = map.get("branchCode").toString();
						Branch branchModel = getBranch(Long.parseLong(branchCode));
						String emailTo = branchModel.getBranchEmail();
						map.put("emailTo", emailTo);
						map.put("subject", "Immediate Action Required: TDS/TCS Regular Return Query for FY : "
								+ map.get("fy") + ", " + map.get("quarter") + ", " + map.get("form"));
						logger.info("Waiting for Query Reply Mail Details send to Abstract mail");
						queryMail.sendEmail(map);
					}
					if (entity.get("Status").toString().equalsIgnoreCase("Certificate Generated")) {
						HashMap<String, String> map = new HashMap<String, String>();
						String arr[] = l.toString().split(Pattern.quote(","), -1);
						for (String e : arr) {
							String a[] = e.split(Pattern.quote("="));
							map.put(a[0].trim().replace("{", ""), a[1].trim().replace("}", ""));
						}
						String branchCode = map.get("branchCode").toString();
						Branch branchModel = getBranch(Long.parseLong(branchCode));
						String emailTo = branchModel.getBranchEmail();
						map.put("emailTo", emailTo);
						map.put("subject",
								"Issuance of TDS/TCS Certificate for Regular Return of TAN : "
										+ map.get("tan").split(Pattern.quote("-"), -1)[0] + " for FY : " + map.get("fy")
										+ ", " + map.get("quarter") + ", " + map.get("form"));
						logger.info("Certificate generated Mail Details send to Abstract mail");
						returnFiledMail.sendEmail(map);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Branch getBranch(long branchCode) {
		return bDao.getByKey(branchCode);
	}

	@Override
	public void saveRegularReturnRO(List<MultipartFile> listdocs,
			MultipartFile tdsfileblob, /* MultipartFile reportfile, */
			String entity, String userName) {
		
		try {

		RegularReturnRemark rrr = mapper.readValue(entity, RegularReturnRemark.class);
		RegularReturn r = rDao.getByKey(rrr.getRegularReturnId());
		r.setStatus("Data added from RO");
		r.setLatestRemark(rrr.getRemark());

		rrr.setRoCode(r.getBranchCode());
		rrr.setAddedBy(userName);
		rrr.setAddedOn(new Date());
		rrr.setRemarkStatus("Data added from RO");
		String supportingDocuments = "";

		if (listdocs == null)
			listdocs = new ArrayList<MultipartFile>();
		listdocs.add(tdsfileblob);
		//listdocs.add(reportfile);

		for (int i = 0; i < listdocs.size(); ++i) {
			if (i < listdocs.size() - 1) {
				supportingDocuments = supportingDocuments + listdocs.get(i).getOriginalFilename() + ",";
			} else {
				supportingDocuments = supportingDocuments + listdocs.get(i).getOriginalFilename();
			}
			saveROFile(listdocs.get(i), rrr, r);
		}

		rrr.setSupportingDocName(supportingDocuments);
		dao.persist(rrr);
		rDao.update(r);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void saveROFile(MultipartFile file, RegularReturnRemark rrr, RegularReturn r) {
		try {
			String path = StaticData.documentSave;
			SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
			if (path != null) {
				String filepath = path + "//" + date.format(rrr.getAddedOn()) + "//" + r.getFy() + "//" + r.getQuarter()
						+ "//" + rrr.getRoCode() + "//RegularReturn//" + r.getForm();
				File files = new File(filepath.toString());
				if (!files.exists()) {
					files.mkdirs();
				}
//				File f = new File(filepath + "//" + file.getOriginalFilename());
				InputStream inputStream = file.getInputStream();
				logger.info(filepath);
				Path fp = Paths.get(filepath, file.getOriginalFilename());
				logger.info(fp.toString());
				try {
					Files.copy(inputStream, fp, StandardCopyOption.REPLACE_EXISTING);
				} catch (Exception e) {
					e.printStackTrace();
				}
//				file.transferTo(f);
			} else {
				throw new CustomException("Docment Save Path Not Found!");
			}
		} catch (IOException e) {
			e.printStackTrace();
			rrr.setSupportingDocName("");
		}
	}

}
