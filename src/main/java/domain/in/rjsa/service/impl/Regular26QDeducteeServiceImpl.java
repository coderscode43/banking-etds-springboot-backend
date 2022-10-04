package domain.in.rjsa.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.icu.text.SimpleDateFormat;

import domain.in.rjsa.dao.Regular26QDeducteeDao;
import domain.in.rjsa.excel.Form26QDeducteeExcel;
import domain.in.rjsa.model.fy.Regular24QDeductee;
import domain.in.rjsa.model.fy.Regular26QDeductee;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.Regular26QDeducteeService;

@Transactional("transactionManager")
@Service("regular26QDeducteeService")
public class Regular26QDeducteeServiceImpl extends AbstractServiceFY<Long, Regular26QDeductee, Regular26QDeducteeDao>
		implements Regular26QDeducteeService {

	@Autowired
	Regular26QDeducteeDao dao;

	Form26QDeducteeExcel form26QDeduceteeExcel;
	public static String path;
	public String ExcelFile;

	@Override
	public Regular26QDeducteeDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

//	@Override
	// public Regular26QDeductee getByKey(Long branchCode) {
	// TODO Auto-generated method stub
	// return dao.getByKey(branchCode);
	// }
	@Override
	public Regular26QDeductee getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public Long findallCount(HashMap<String, Object> constrains) {
		// TODO Auto-generated method stub
		return getPrimaryDao().findallCount(constrains);
	}

	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<Regular26QDeductee> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		form26QDeduceteeExcel = new Form26QDeducteeExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		form26QDeduceteeExcel.initialise(path + "static/report/TDS-" + timestamp + "-26QDeductee.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-26QDeductee.xlsx";

		int row = 1;
		int part =1;

		Workbook wb = form26QDeduceteeExcel.getWorkbook();
		Sheet form26QDeductee = wb.getSheet("form26QDeductee-" + part);
		for (Regular26QDeductee form26Q : listUsers) {
			Row details = form26QDeductee.createRow(row);
			details.createCell(0).setCellValue(row);

			if (form26Q.getQuarter() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(form26Q.getQuarter());
			}
			if (form26Q.getMonth() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(form26Q.getMonth());
			}
			if (form26Q.getRoCode() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(form26Q.getRoCode());
			}
			if (form26Q.getBranchCode() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(form26Q.getBranchCode());
			}
			if (form26Q.getCustVendId() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(form26Q.getCustVendId());
			}
			if (form26Q.getUniqueRefNo() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(form26Q.getUniqueRefNo());
			}
			if (form26Q.getAccNo() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(form26Q.getAccNo());
			}
			if (form26Q.getChallanHeading() == null) {
				details.createCell(8).setCellValue(" ");
			} else {
				details.createCell(8).setCellValue(form26Q.getChallanHeading());
			}
			if (form26Q.getDeducteeRefNo() == null) {
				details.createCell(9).setCellValue(" ");
			} else {
				details.createCell(9).setCellValue(form26Q.getDeducteeRefNo());
			}
			if (form26Q.getPan() == null) {
				details.createCell(10).setCellValue(" ");
			} else {
				details.createCell(10).setCellValue(form26Q.getPan());
			}
			if (form26Q.getName() == null) {
				details.createCell(11).setCellValue(" ");
			} else {
				details.createCell(11).setCellValue(form26Q.getName());
			}
			if (form26Q.getSectionCode() == null) {
				details.createCell(12).setCellValue(" ");
			} else {
				details.createCell(12).setCellValue(form26Q.getSectionCode());
			}
			if (form26Q.getDateOfPayment() == null) {
				details.createCell(13).setCellValue(" ");
			} else {
				details.createCell(13)
						.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(form26Q.getDateOfPayment()));
			}
			if (form26Q.getDateOfDeduction() == null) {
				details.createCell(14).setCellValue(" ");
			} else {
				details.createCell(14).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(form26Q.getDateOfDeduction()));
			}
			if (form26Q.getAmountPaid() == null) {
				details.createCell(15).setCellValue(" ");
			} else {
				details.createCell(15).setCellValue(form26Q.getAmountPaid());
			}
			if (form26Q.getTds() == null) {
				details.createCell(16).setCellValue(" ");
			} else {
				details.createCell(16).setCellValue(form26Q.getTds());
			}
			if (form26Q.getSurcharge() == null) {
				details.createCell(17).setCellValue(" ");
			} else {
				details.createCell(17).setCellValue(form26Q.getSurcharge());
			}
			if (form26Q.getEduCess() == null) {
				details.createCell(18).setCellValue(" ");
			} else {
				details.createCell(18).setCellValue(form26Q.getEduCess());
			}
			if (form26Q.getTotalTaxDeducted() == null) {
				details.createCell(19).setCellValue(" ");
			} else {
				details.createCell(19).setCellValue(form26Q.getTotalTaxDeducted());
			}
			if (form26Q.getTotalTaxDeposited() == null) {
				details.createCell(20).setCellValue(" ");
			} else {
				details.createCell(20).setCellValue(form26Q.getTotalTaxDeposited());
			}
			if (form26Q.getCertificateNumber() == null) {
				details.createCell(21).setCellValue(" ");
			} else {
				details.createCell(21).setCellValue(form26Q.getCertificateNumber());
			}
			if (form26Q.getRemarksReason() == null) {
				details.createCell(22).setCellValue(" ");
			} else {
				details.createCell(22).setCellValue(form26Q.getRemarksReason());
			}
			if (form26Q.getDeducteeCode() == null) {
				details.createCell(23).setCellValue(" ");
			} else {
				details.createCell(23).setCellValue(form26Q.getDeducteeCode());
			}
			if (form26Q.getRateAtWhichTaxCollected() == null) {
				details.createCell(24).setCellValue(" ");
			} else {
				details.createCell(24).setCellValue(form26Q.getRateAtWhichTaxCollected());
			}
			if (form26Q.getCashWithdrawal194N() == null) {
				details.createCell(25).setCellValue(" ");
			} else {
				details.createCell(25).setCellValue(form26Q.getCashWithdrawal194N());
			}
			if (form26Q.getCashWithdrawal194N20Lto1Cr() == null) {
				details.createCell(26).setCellValue(" ");
			} else {
				details.createCell(26).setCellValue(form26Q.getCashWithdrawal194N20Lto1Cr());
			}
			if (form26Q.getCashWithdrawal194N1Cr() == null) {
				details.createCell(27).setCellValue(" ");
			} else {
				details.createCell(27).setCellValue(form26Q.getCashWithdrawal194N1Cr());
			}
			if (form26Q.getErrorDescription() == null) {
				details.createCell(28).setCellValue(" ");
			} else {
				details.createCell(28).setCellValue(form26Q.getErrorDescription());
			}
			if (form26Q.getWarningDescription() == null) {
				details.createCell(29).setCellValue(" ");
			} else {
				details.createCell(29).setCellValue(form26Q.getWarningDescription());
			}
			if (form26Q.getShortDeduction() == null) {
				details.createCell(30).setCellValue(" ");
			} else {
				details.createCell(30).setCellValue(form26Q.getShortDeduction());
			}
			if (form26Q.getInterestOnShortDeduction() == null) {
				details.createCell(31).setCellValue(" ");
			} else {
				details.createCell(31).setCellValue(form26Q.getInterestOnShortDeduction());
			}
			if (form26Q.getInterestOnLatePayment() == null) {
				details.createCell(32).setCellValue(" ");
			} else {
				details.createCell(32).setCellValue(form26Q.getInterestOnLatePayment());
			}
			if (form26Q.getInterestOnLateDeduction() == null) {
				details.createCell(33).setCellValue(" ");
			} else {
				details.createCell(33).setCellValue(form26Q.getInterestOnLateDeduction());
			}
			if (form26Q.getTAN() == null) {
				details.createCell(34).setCellValue(" ");
			} else {
				details.createCell(34).setCellValue(form26Q.getTAN());
			}
			if (form26Q.getComments() == null) {
				details.createCell(35).setCellValue(" ");
			} else {
				details.createCell(35).setCellValue(form26Q.getComments());
			}
			if (form26Q.isResolved()) {
				details.createCell(36).setCellValue("Not Resolved");
			} else {
				details.createCell(36).setCellValue("Resolved");
			}
			
			if (row > 1000000) {
				part++;
				wb = form26QDeduceteeExcel.getWorkbook();
				form26QDeductee = form26QDeduceteeExcel.initializeSheet("form26QDeductee-" + part);
				row =0;
			}

			row++;

		}
		form26QDeduceteeExcel.close();
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
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}

	public void updateAllowed(Regular26QDeductee entity) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		Object id = entity.getId();
//		Regular26QDeductee regular26QWeb = new Regular26QDeductee();//from Gson //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		map.put("id",id);
		Regular26QDeductee regular26Q =  (Regular26QDeductee) dao.uniqueSearch(map);
		regular26Q.updateAllowedFields(entity);
		getPrimaryDao().update(regular26Q);
	}

	
}
