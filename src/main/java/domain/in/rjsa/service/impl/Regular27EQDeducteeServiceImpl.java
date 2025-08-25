package domain.in.rjsa.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.Regular27EQDeducteeDao;
import domain.in.rjsa.dao.RemarkDao;
import domain.in.rjsa.excel.Form27EQDeducteeExcel;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.fy.Regular27EQDeductee;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.Regular27EQDeducteeService;

@Transactional("transactionManager")
@Service("regular27EQDeducteeService")
public class Regular27EQDeducteeServiceImpl extends AbstractServiceFY<Long, Regular27EQDeductee, Regular27EQDeducteeDao>
		implements Regular27EQDeducteeService {

	@Autowired
	Regular27EQDeducteeDao dao;
	@Autowired
	RemarkDao rDao;
	Form27EQDeducteeExcel form27EQDeduceteeExcel;
	public static String path;
	public String ExcelFile;
	
	public void update(Regular27EQDeductee entity) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", entity.getId());
		Regular27EQDeductee regular27EQ = (Regular27EQDeductee) dao.uniqueSearch(map);
		regular27EQ.updateAllowedFields(entity);
		getPrimaryDao().update(regular27EQ);
		
	}

	@Override
	public Regular27EQDeductee getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public Regular27EQDeducteeDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	@Override
	public Long findallCount(HashMap<String, Object> constrains) {
		// TODO Auto-generated method stub
		return getPrimaryDao().findallCount(constrains);
	}

	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<Regular27EQDeductee> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		form27EQDeduceteeExcel = new Form27EQDeducteeExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		form27EQDeduceteeExcel.initialise(path + "static/report/TDS-" + timestamp + "-27EQDeductee.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-27EQDeductee.xlsx";

		int row = 1;
		int part = 1;

		Workbook wb = form27EQDeduceteeExcel.getWorkbook();
		Sheet form27EQDeductee = wb.getSheet("form27EQDeductee-" + part);
		for (Regular27EQDeductee form27EQ : listUsers) {
			Row details = form27EQDeductee.createRow(row);

			details.createCell(0).setCellValue(row);

			if (form27EQ.getQuarter() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(form27EQ.getQuarter());
			}
			if (form27EQ.getMonth() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(form27EQ.getMonth());
			}
			if (form27EQ.getRoCode() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(form27EQ.getRoCode());
			}
			if (form27EQ.getBranchCode() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(form27EQ.getBranchCode());
			}
			if (form27EQ.getChallanHeading() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(form27EQ.getChallanHeading());
			}
			if (form27EQ.getPan() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(form27EQ.getPan());
			}
			if (form27EQ.getName() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(form27EQ.getName());
			}
			if (form27EQ.getSectionCode() == null) {
				details.createCell(8).setCellValue(" ");
			} else {
				details.createCell(8).setCellValue(form27EQ.getSectionCode());
			}
			if (form27EQ.getTAN() == null) {
				details.createCell(9).setCellValue(" ");
			} else {
				details.createCell(9).setCellValue(form27EQ.getTAN());
			}
			if (form27EQ.getUniqueRefNo() == null) {
				details.createCell(10).setCellValue(" ");
			} else {
				details.createCell(10).setCellValue(form27EQ.getUniqueRefNo());
			}
			if (form27EQ.getAccNo() == null) {
				details.createCell(11).setCellValue(" ");
			} else {
				details.createCell(11).setCellValue(form27EQ.getAccNo());
			}
			if (form27EQ.getDateOfPayment() == null) {
				details.createCell(12).setCellValue(" ");
			} else {
				details.createCell(12)
						.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(form27EQ.getDateOfPayment()));
			}
			if (form27EQ.getDateOfDeduction() == null) {
				details.createCell(13).setCellValue(" ");
			} else {
				details.createCell(13)
						.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(form27EQ.getDateOfDeduction()));
			}
			if (form27EQ.getAmountPaid() == null) {
				details.createCell(14).setCellValue(" ");
			} else {
				details.createCell(14).setCellValue(form27EQ.getAmountPaid());
			}
			if (form27EQ.getTds() == null) {
				details.createCell(15).setCellValue(" ");
			} else {
				details.createCell(15).setCellValue(form27EQ.getTds());
			}
			if (form27EQ.getSurcharge() == null) {
				details.createCell(16).setCellValue(" ");
			} else {
				details.createCell(16).setCellValue(form27EQ.getSurcharge());
			}
			if (form27EQ.getEduCess() == null) {
				details.createCell(17).setCellValue(" ");
			} else {
				details.createCell(17).setCellValue(form27EQ.getEduCess());
			}
			if (form27EQ.getTotalTaxDeducted() == null) {
				details.createCell(18).setCellValue(" ");
			} else {
				details.createCell(18).setCellValue(form27EQ.getTotalTaxDeducted());
			}
			if (form27EQ.getTotalTaxDeposited() == null) {
				details.createCell(19).setCellValue(" ");
			} else {
				details.createCell(19).setCellValue(form27EQ.getTotalTaxDeposited());
			}
			if (form27EQ.getCertificateNumber() == null) {
				details.createCell(20).setCellValue(" ");
			} else {
				details.createCell(20).setCellValue(form27EQ.getCertificateNumber());
			}
			if (form27EQ.getRemarksReason() == null) {
				details.createCell(21).setCellValue(" ");
			} else {
				details.createCell(21).setCellValue(form27EQ.getRemarksReason());
			}
			if (form27EQ.getDeducteeCode() == null) {
				details.createCell(22).setCellValue(" ");
			} else {
				details.createCell(22).setCellValue(form27EQ.getDeducteeCode());
			}
			if (form27EQ.getRateatwhichTaxCollected() == null) {
				details.createCell(23).setCellValue(" ");
			} else {
				details.createCell(23).setCellValue(form27EQ.getRateatwhichTaxCollected());
			}
			if (form27EQ.getTotalValueofPurchase() == null) {
				details.createCell(24).setCellValue(" ");
			} else {
				details.createCell(24).setCellValue(form27EQ.getTotalValueofPurchase());
			}
			if (form27EQ.getDeducteeisNonResident() == null) {
				details.createCell(25).setCellValue(" ");
			} else {
				details.createCell(25).setCellValue(form27EQ.getDeducteeisNonResident());
			}
			if (form27EQ.getPermanentEstablishment() == null) {
				details.createCell(26).setCellValue(" ");
			} else {
				details.createCell(26).setCellValue(form27EQ.getPermanentEstablishment());
			}
			if (form27EQ.getReasonForNonCollectionForG() == null) {
				details.createCell(27).setCellValue(" ");
			} else {
				details.createCell(27).setCellValue(form27EQ.getReasonForNonCollectionForG());
			}
			if (form27EQ.getIfAnswerTo681AisyesthenChallanNumber() == null) {
				details.createCell(28).setCellValue(" ");
			} else {
				details.createCell(28).setCellValue(form27EQ.getIfAnswerTo681AisyesthenChallanNumber());
			}
			if (form27EQ.getIfAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment() == null) {
				details.createCell(29).setCellValue(" ");
			} else {
				details.createCell(29)
						.setCellValue(form27EQ.getIfAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment());
			}
			if (form27EQ.getErrorDescription() == null) {
				details.createCell(30).setCellValue(" ");
			} else {
				details.createCell(30).setCellValue(form27EQ.getErrorDescription());
			}
			if (form27EQ.getWarningDescription() == null) {
				details.createCell(31).setCellValue(" ");
			} else {
				details.createCell(31).setCellValue(form27EQ.getWarningDescription());
			}
			if (form27EQ.getShortDeduction() == null) {
				details.createCell(32).setCellValue(" ");
			} else {
				details.createCell(32).setCellValue(form27EQ.getShortDeduction());
			}
			if (form27EQ.getInterestOnShortDeduction() == null) {
				details.createCell(33).setCellValue(" ");
			} else {
				details.createCell(33).setCellValue(form27EQ.getInterestOnShortDeduction());
			}
			if (form27EQ.getInterestOnLatePayment() == null) {
				details.createCell(34).setCellValue(" ");
			} else {
				details.createCell(34).setCellValue(form27EQ.getInterestOnLatePayment());
			}
			if (form27EQ.getInterestOnLateDeduction() == null) {
				details.createCell(35).setCellValue(" ");
			} else {
				details.createCell(35).setCellValue(form27EQ.getInterestOnLateDeduction());
			}
			if (form27EQ.getComments() == null) {
				details.createCell(36).setCellValue(" ");
			} else {
				details.createCell(36).setCellValue(form27EQ.getComments());
			}
			if (form27EQ.isResolved()) {
				details.createCell(37).setCellValue("Not Resolved");
			} else {
				details.createCell(37).setCellValue("Resolved");
			}
			if (row > 1000000) {
				part++;
				wb = form27EQDeduceteeExcel.getWorkbook();
				form27EQDeductee = form27EQDeduceteeExcel.initializeSheet("form27EQDeductee-" + part);
				row =0;
			}
			row++;

		}
		form27EQDeduceteeExcel.close();
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
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}

	public void updateAllowed(Regular27EQDeductee entity) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		Object id = entity.getId();
//		Regular27EQDeductee regular27EQWeb = new Regular27EQDeductee();//from Gson //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		map.put("id",id);
		Regular27EQDeductee regular27EQ =  (Regular27EQDeductee) dao.uniqueSearch(map);
		regular27EQ.updateAllowedFields(entity);
		getPrimaryDao().update(regular27EQ);
	}

	@Override
	public List<String> getPanList(String q, String fy, Long branchCode) {
		// TODO Auto-generated method stub
		return dao.getPanList(q, fy, branchCode);
	}

	@Override
	public void addData(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<JSONObject> jsonObject =  mapper.readValue(json, new TypeReference <List<JSONObject>>() {
			});
			
			for (JSONObject object : jsonObject) {
				Regular27EQDeductee deductee = new Regular27EQDeductee();
				deductee.setData(object);
				dao.persist(deductee);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}
	}

}
