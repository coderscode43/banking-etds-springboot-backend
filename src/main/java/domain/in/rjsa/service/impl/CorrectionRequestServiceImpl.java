package domain.in.rjsa.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.dao.CorrectionRemarksDao;
import domain.in.rjsa.dao.CorrectionRequestAmountDetailsDao;
import domain.in.rjsa.dao.CorrectionRequestDao;
import domain.in.rjsa.dao.UserDetailsDao;
import domain.in.rjsa.excel.CorrectionRequestExcel;
import domain.in.rjsa.model.form.CorrectionRemarks;
import domain.in.rjsa.model.form.CorrectionRequest;
import domain.in.rjsa.model.form.CorrectionRequestAmountDetails;
import domain.in.rjsa.model.form.UserDetails;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.CorrectionRequestService;

@Transactional("transactionManager")
@Service("correctionRequestService")
public class CorrectionRequestServiceImpl extends AbstractServiceForm<Long, CorrectionRequest, CorrectionRequestDao>
		implements CorrectionRequestService {

	@Autowired
	CorrectionRequestDao dao;
	CorrectionRequestExcel correctionRequestExcel;

	@Autowired
	CorrectionRequestAmountDetailsDao cradDao;

	@Autowired
	CorrectionRemarksDao crDao;

	@Autowired
	UserDetailsDao uDao;

	public static String path;
	public String ExcelFile;

	@Override
	public CorrectionRequestDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		List<Object> updatedList = new ArrayList<>();
		List<?> list = dao.search(map, pageNo, resultPerPage);
		for (Object l : list) {
			Gson gson = new Gson();
			JsonElement jsonElement = gson.toJsonTree(l);
			HashMap<String, Object> cr = gson.fromJson(jsonElement, HashMap.class);
			if (cr.get("rejectStatus").toString() == "true") {
				cr.put("color", "orangered");
				JsonElement jsonEle = gson.toJsonTree(cr);
				Object obj = gson.fromJson(jsonEle, Object.class);
				updatedList.add(obj);
			} else {
				if (cr.get("checkerApprovedBy") == null && cr.get("taxTeamApprovedBy") == null
						&& cr.get("correctionBy") == null) {
					cr.put("color", "yellow");
					JsonElement jsonEle = gson.toJsonTree(cr);
					Object obj = gson.fromJson(jsonEle, Object.class);
					updatedList.add(obj);
				} else if (cr.get("checkerApprovedBy") != null && cr.get("taxTeamApprovedBy") == null
						&& cr.get("correctionBy") == null) {
					cr.put("color", "orange");
					JsonElement jsonEle = gson.toJsonTree(cr);
					Object obj = gson.fromJson(jsonEle, Object.class);
					updatedList.add(obj);
				} else if (cr.get("checkerApprovedBy") != null && cr.get("taxTeamApprovedBy") != null
						&& cr.get("correctionBy") == null) {
					cr.put("color", "lightblue");
					JsonElement jsonEle = gson.toJsonTree(cr);
					Object obj = gson.fromJson(jsonEle, Object.class);
					updatedList.add(obj);
				} else if (cr.get("checkerApprovedBy") != null && cr.get("taxTeamApprovedBy") != null
						&& cr.get("correctionBy") != null) {
					cr.put("color", "lightgreen");
					JsonElement jsonEle = gson.toJsonTree(cr);
					Object obj = gson.fromJson(jsonEle, Object.class);
					updatedList.add(obj);
				}
			}
		}
		return updatedList;
	}

	@Override
	public Map<String, Long> getStatusDetails(Long branchCode, boolean isAdmin) {
		// TODO Auto-generated method stub
		return dao.getStatusCounts(branchCode, isAdmin);
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<CorrectionRequest> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		correctionRequestExcel = new CorrectionRequestExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		correctionRequestExcel.initialise(path + "static/report/TDS-" + timestamp + "-CorrectionRequest.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-CorrectionRequest.xlsx";

		int row = 1;
		int part = 1;

		Workbook wb = correctionRequestExcel.getWorkbook();
		Sheet correctionRequest = wb.getSheet("correctionRequest-" + part);
		for (CorrectionRequest CorrectionRequest1 : listUsers) {

			Row details = correctionRequest.createRow(row);
			details.createCell(0).setCellValue(row);

			if (CorrectionRequest1.getId() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(CorrectionRequest1.getId());
			}
			if (CorrectionRequest1.getDate() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2)
						.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(CorrectionRequest1.getDate()));
			}
			if (CorrectionRequest1.getCustId() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(CorrectionRequest1.getCustId());
			}
			if (CorrectionRequest1.getNameOfCust() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(CorrectionRequest1.getNameOfCust());
			}
			if (CorrectionRequest1.getPanOfCust() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(CorrectionRequest1.getPanOfCust());
			}
			if (CorrectionRequest1.getNameOfRequest() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(CorrectionRequest1.getNameOfRequest());
			}

			if (CorrectionRequest1.getBranchCode() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(CorrectionRequest1.getBranchCode());
			}
			if (CorrectionRequest1.getEmpNo() == null) {
				details.createCell(8).setCellValue(" ");
			} else {
				details.createCell(8).setCellValue(CorrectionRequest1.getEmpNo());
			}
			if (CorrectionRequest1.getTicketNumber() == null) {
				details.createCell(9).setCellValue(" ");
			} else {
				details.createCell(9).setCellValue(CorrectionRequest1.getTicketNumber());
			}
			if (CorrectionRequest1.getTypeOfCorrection() == null) {
				details.createCell(10).setCellValue(" ");
			} else {
				details.createCell(10).setCellValue(CorrectionRequest1.getTypeOfCorrection());
			}
			if (CorrectionRequest1.getRemark() == null) {
				details.createCell(11).setCellValue(" ");
			} else {
				details.createCell(11).setCellValue(CorrectionRequest1.getRemark());
			}
			if (CorrectionRequest1.getCheckerApprovedBy() == null) {
				details.createCell(12).setCellValue(" ");
			} else {
				details.createCell(12).setCellValue(CorrectionRequest1.getCheckerApprovedBy());
			}
			if (CorrectionRequest1.getCheckerApprovedOn() == null) {
				details.createCell(13).setCellValue(" ");
			} else {
				details.createCell(13).setCellValue(CorrectionRequest1.getCheckerApprovedOn());
			}
			if (CorrectionRequest1.getTaxTeamApprovedBy() == null) {
				details.createCell(12).setCellValue(" ");
			} else {
				details.createCell(12).setCellValue(CorrectionRequest1.getTaxTeamApprovedBy());
			}
			if (CorrectionRequest1.getTaxTeamApprovedOn() == null) {
				details.createCell(13).setCellValue(" ");
			} else {
				details.createCell(13).setCellValue(CorrectionRequest1.getTaxTeamApprovedOn());
			}
			if (CorrectionRequest1.getCorrectionBy() == null) {
				details.createCell(14).setCellValue(" ");
			} else {
				details.createCell(14).setCellValue(CorrectionRequest1.getCorrectionBy());
			}
			if (CorrectionRequest1.getCorrectionOn() == null) {
				details.createCell(15).setCellValue(" ");
			} else {
				details.createCell(15).setCellValue(CorrectionRequest1.getCorrectionOn());
			}
			if (CorrectionRequest1.getStatus() == null) {
				details.createCell(16).setCellValue(" ");
			} else {
				details.createCell(16).setCellValue(CorrectionRequest1.getStatus());
			}
			if (CorrectionRequest1.getFy() == null) {
				details.createCell(16).setCellValue(" ");
			} else {
				details.createCell(16).setCellValue(CorrectionRequest1.getFy());
			}
			if (CorrectionRequest1.getQuarter() == null) {
				details.createCell(16).setCellValue(" ");
			} else {
				details.createCell(16).setCellValue(CorrectionRequest1.getQuarter());
			}

			if (row > 1000000) {
				part++;
				wb = correctionRequestExcel.getWorkbook();
				correctionRequest = correctionRequestExcel.initializeSheet("correctionRequest-" + part);
				row = 0;
			}
			row++;

		}
		correctionRequestExcel.close();
		return ExcelFile;

	}

	public void setPath() {
		File myClass = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());

		try {
			path = myClass.getCanonicalPath().split("WEB-INF")[0];
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public void saveAmount(LinkedHashMap<String, Object> entity) {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> propertyNameValues = new HashMap<String, Object>();
			Long count = 0L;
			count = getPrimaryDao().getMaxValue("id", propertyNameValues);
			CorrectionRequest cr = dao.getByKey(count);
			CorrectionRequestAmountDetails cra = new CorrectionRequestAmountDetails();
			SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");
			cra.setDateOfPayment(Date.parse(entity.get("dateOfPayment").toString()));
			cra.setAmountPaid(Long.valueOf(entity.get("amountPaid").toString()));
			cra.setAmountPaid26as(Long.valueOf(entity.get("amountPaid26as").toString()));
			cra.setTdsDeducted(Long.valueOf(entity.get("tds").toString()));
			cra.setTds26as(Long.valueOf(entity.get("tds26as").toString()));
			cra.setRemark(entity.get("amountRemark").toString());
			cra.setCorrectionRequestId(cr.getId());
			cradDao.persist(cra);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void autoGenerateTicketNumber(LinkedHashMap<String, Object> entity) {
		// TODO Auto-generated method stub
		HashMap<String, Object> propertyNameValues = new HashMap<String, Object>();
		Long count = 0L;
		try {
			List<CorrectionRequest> cr = getPrimaryDao().search(propertyNameValues);
			if (!cr.isEmpty()) {
				count = getPrimaryDao().getMaxValue("ticketNumber", propertyNameValues);
			} else {
				count = 0L;
			}
		} catch (Exception e) {
			count = 0L;
		}
		count++;
		entity.put("ticketNumber", count);
	}

	@Override
	public List<?> getList(HashMap<String, Object> constrains, int pageNo, int resultPerPage) {
		List<Object> updatedList = new ArrayList<>();
		List<?> list = dao.findall(constrains, pageNo, resultPerPage);
		for (Object l : list) {
			Gson gson = new Gson();
			JsonElement jsonElement = gson.toJsonTree(l);
			HashMap<String, Object> cr = gson.fromJson(jsonElement, HashMap.class);
			if (cr.get("rejectStatus").toString() == "true") {
				cr.put("color", "orangered");
				JsonElement jsonEle = gson.toJsonTree(cr);
				Object obj = gson.fromJson(jsonEle, Object.class);
				updatedList.add(obj);
			} else {
				if (cr.get("checkerApprovedBy") == null && cr.get("taxTeamApprovedBy") == null
						&& cr.get("correctionBy") == null) {
					cr.put("color", "yellow");
					JsonElement jsonEle = gson.toJsonTree(cr);
					Object obj = gson.fromJson(jsonEle, Object.class);
					updatedList.add(obj);
				} else if (cr.get("checkerApprovedBy") != null && cr.get("taxTeamApprovedBy") == null
						&& cr.get("correctionBy") == null) {
					cr.put("color", "orange");
					JsonElement jsonEle = gson.toJsonTree(cr);
					Object obj = gson.fromJson(jsonEle, Object.class);
					updatedList.add(obj);
				} else if (cr.get("checkerApprovedBy") != null && cr.get("taxTeamApprovedBy") != null
						&& cr.get("correctionBy") == null) {
					cr.put("color", "lightblue");
					JsonElement jsonEle = gson.toJsonTree(cr);
					Object obj = gson.fromJson(jsonEle, Object.class);
					updatedList.add(obj);
				} else if (cr.get("checkerApprovedBy") != null && cr.get("taxTeamApprovedBy") != null
						&& cr.get("correctionBy") != null) {
					cr.put("color", "lightgreen");
					JsonElement jsonEle = gson.toJsonTree(cr);
					Object obj = gson.fromJson(jsonEle, Object.class);
					updatedList.add(obj);
				}
			}
		}
		return updatedList;
	}

	@Override
	public Object getDetail(HashMap<String, Object> constrains, Long id, String principal, Long branchCode) {
		constrains.put("id", id);
		HashMap<String, Object> map = new HashMap<>();
		CorrectionRequest cr = dao.uniqueSearch(constrains);
		map.put("details", cr);
		constrains.remove("id");
		constrains.remove("branchCode");
		constrains.put("correctionRequestId", id);
		List<CorrectionRemarks> remark = crDao.findForm(constrains);
		map.put("remark", remark);
		CorrectionRequestAmountDetails amountDetails = cradDao.uniqueSearch(constrains);
		UserDetails user = uDao.getByKey(principal);

		map.put("amountDetails", amountDetails);
		if (!cr.getMakerBy().equalsIgnoreCase(principal)) {
			if (user != null) {
				if (cr.getCorrectionBy() != null && cr.getCorrectionBy().equalsIgnoreCase(principal)) {
					map.put("disable", false);
				} else if (cr.getTaxTeamApprovedBy() != null && cr.getTaxTeamApprovedBy().equalsIgnoreCase(principal)) {
					map.put("disable", false);
				} else {
					if (cr.getCorrectionBy() != null) {
						map.put("disable", false);
					} else {
						map.put("disable", true);
					}
				}
			} else {
				if (cr.getCheckerApprovedBy() != null && cr.getCheckerApprovedBy().equalsIgnoreCase(principal)) {
					map.put("disable", false);
				} else {
					map.put("disable", true);
				}
			}
		} else {
			map.put("disable", false);
		}

		return map;
	}

}
