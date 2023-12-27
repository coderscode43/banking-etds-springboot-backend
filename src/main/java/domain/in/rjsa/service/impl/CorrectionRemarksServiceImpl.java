package domain.in.rjsa.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.dao.CorrectionRemarksDao;
import domain.in.rjsa.dao.CorrectionRequestDao;
import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.dao.UserDetailsDao;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.form.CorrectionRemarks;
import domain.in.rjsa.model.form.CorrectionRequest;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.StaticDataModel;
import domain.in.rjsa.model.form.UserDetails;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.CorrectionRemarksService;
import domain.in.rjsa.web.ApplicationCache;

@Transactional("transactionManager")
@Service("correctionRemarkService")
public class CorrectionRemarksServiceImpl extends AbstractServiceForm<Long, CorrectionRemarks, CorrectionRemarksDao>
		implements CorrectionRemarksService {

	@Autowired
	CorrectionRemarksDao dao;

	@Autowired
	CorrectionRequestDao crDao;

	@Autowired
	UserDetailsDao uDao;

	@Autowired
	StaticDataDao sDao;

	@Autowired
	ApplicationCache applicationCache;

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CorrectionRemarksDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public List<CorrectionRemarks> findForm(HashMap<String, Object> constrains) {
		// TODO Auto-generated method stub
		return dao.findForm(constrains);
	}

	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}

	@Override
	public void SaveRemarkWithDocument(MultipartFile downloadFile, Long branchCode, Long crId, String remark,
			String principal, String status) {
		// TODO Auto-generated method stub
		try {
			CorrectionRemarks cr = new CorrectionRemarks();
			String timeStamp = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss").format(Calendar.getInstance().getTime());
			String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
			cr.setBranchCode(branchCode);
			cr.setCorrectionRequestId(crId);
			cr.setCorrectionRemark(remark);
			cr.setSupportingDocName(downloadFile.getOriginalFilename());
			cr.setDateTime(timeStamp);
			cr.setRemarkStatus(status);
			cr.setAddedBy(principal);
			dao.persist(cr);
			HashMap<String, Object> sd = new HashMap<String, Object>();
			List<StaticDataModel> list = sDao.findall(sd, 0, 100);
			for (StaticDataModel l : list) {
				if (l.getKey().equalsIgnoreCase("documentSave")) {
					String path = l.getValue();
					String filepath = path + "//" + date + "//" + branchCode + "//" + crId + "//"
							+ downloadFile.getOriginalFilename();
					File file = new File(filepath.toString());
					if (!file.exists()) {
						file.mkdirs();
					}
					downloadFile.transferTo(file);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void saveRemark(CorrectionRemarks entity, String principal) {
		CorrectionRequest cr = crDao.getByKey(entity.getCorrectionRequestId());
		String makerBy = cr.getMakerBy();
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss").format(Calendar.getInstance().getTime());
		String status = entity.getRemarkStatus();
		if (!makerBy.equalsIgnoreCase(principal)) {
			if (status.equalsIgnoreCase("Approved")) {
				if (cr.getCheckerApprovedBy() == null) {
					entity.setDateTime(timeStamp);
					entity.setAddedBy(principal);
					dao.persist(entity);
					cr.setStatus("Pending Tax Team Approval");
					cr.setCheckerApprovedBy(principal);
					cr.setCheckerApprovedOn(new Date());
					crDao.update(cr);
				} else if (cr.getTaxTeamApprovedBy() == null) {
					entity.setDateTime(timeStamp);
					entity.setAddedBy(principal);
					dao.persist(entity);
					cr.setStatus("Pending for Correction");
					cr.setTaxTeamApprovedBy(principal);
					cr.setTaxTeamApprovedOn(new Date());
					crDao.update(cr);
				} else if (cr.getCorrectionBy() == null) {
					entity.setDateTime(timeStamp);
					entity.setAddedBy(principal);
					dao.persist(entity);
					cr.setStatus("Resolved");
					cr.setCorrectionBy(principal);
					cr.setCorrectionOn(new Date());
					crDao.update(cr);
				}
			} else if (status.equalsIgnoreCase("Rejected")) {
				entity.setDateTime(timeStamp);
				entity.setAddedBy(principal);
				dao.persist(entity);
				cr.setStatus("Rejected");
				cr.setCheckerApprovedBy(principal);
				cr.setCheckerApprovedOn(new Date());
				cr.setRejectStatus(true);
				crDao.update(cr);
			} else if (status.equalsIgnoreCase("Remark")) {
				entity.setDateTime(timeStamp);
				entity.setAddedBy(principal);
				dao.persist(entity);
			}
		} else {
			if (status.equalsIgnoreCase("Remark")) {
				entity.setDateTime(timeStamp);
				entity.setAddedBy(principal);
				dao.persist(entity);
			} else {
				throw new CustomException("Branch can not approved/Reject own correction request.");
			}
		}
	}

	@Override
	public void downloadDocument(Long id, HttpServletResponse response) {
		try {
			CorrectionRemarks cr = dao.getByKey(id);
			HashMap<String, Object> sd = new HashMap<String, Object>();
			List<StaticDataModel> list = sDao.findall(sd, 0, 100);
			for (StaticDataModel l : list) {
				if (l.getKey().equalsIgnoreCase("documentSave")) {
					String path = l.getValue();
					String date = cr.getDateTime().split(Pattern.quote("T"), -1)[0];
					String filePath = path + "//" + date + "//" + cr.branchCode + "//" + cr.getCorrectionRequestId()
							+ "//" + cr.getSupportingDocName();
					File file = new File(filePath);
					byte[] fileContent;
					fileContent = Files.readAllBytes(file.toPath());
					String mimeType = URLConnection.guessContentTypeFromName(cr.getSupportingDocName());
					response.setContentType(mimeType);
					response.setStatus(HttpServletResponse.SC_OK);
					response.setHeader("Content-Disposition", " attachment; filename=" + cr.getSupportingDocName());
					response.getOutputStream().write(fileContent);
					response.getOutputStream().close();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
