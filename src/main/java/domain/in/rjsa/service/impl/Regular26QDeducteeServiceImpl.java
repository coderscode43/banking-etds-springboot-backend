package domain.in.rjsa.service.impl;


import java.io.File;
import java.io.IOException;
import java.util.Date;
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
import domain.in.rjsa.model.fy.Regular26QDeductee;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.Regular26QDeducteeService;
@Transactional("transactionManager")
@Service("regular26QDeducteeService")
public class Regular26QDeducteeServiceImpl extends AbstractServiceFY<Long, Regular26QDeductee, Regular26QDeducteeDao> implements Regular26QDeducteeService{

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

	@Override
	public Regular26QDeductee getByKey(Long id) {
		return dao.getByKey(id);
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

		for (Regular26QDeductee form26Q : listUsers) {
			Workbook wb = form26QDeduceteeExcel.getWorkbook();
			Sheet form26QDeductee = wb.getSheet("form26QDeductee");
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
			if (form26Q.getDeducteeCode() == null) {
				details.createCell(10).setCellValue(" ");
			} else {
				details.createCell(10).setCellValue(form26Q.getDeducteeCode());
			}
			if (form26Q.getDeducteePan() == null) {
				details.createCell(11).setCellValue(" ");
			} else {
				details.createCell(11).setCellValue(form26Q.getDeducteePan());
			}
			if (form26Q.getDeducteeName() == null) {
				details.createCell(12).setCellValue(" ");
			} else {
				details.createCell(12).setCellValue(form26Q.getDeducteeName());
			}
			if (form26Q.getSectionCode() == null) {
				details.createCell(13).setCellValue(" ");
			} else {
				details.createCell(13).setCellValue(form26Q.getSectionCode());
			}
			if (form26Q.getPaymentDate() == null) {
				details.createCell(14).setCellValue(" ");
			} else {
				details.createCell(14)
						.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(form26Q.getPaymentDate()));
			}
			if (form26Q.getPaidAmt() == null) {
				details.createCell(15).setCellValue(" ");
			} else {
				details.createCell(15).setCellValue(form26Q.getPaidAmt());
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
			if (form26Q.getTotalTaxDeduct() == null) {
				details.createCell(19).setCellValue(" ");
			} else {
				details.createCell(19).setCellValue(form26Q.getTotalTaxDeduct());
			}
			if (form26Q.getTotalTaxDeposit() == null) {
				details.createCell(20).setCellValue(" ");
			} else {
				details.createCell(20).setCellValue(form26Q.getTotalTaxDeposit());
			}
			if (form26Q.getDeductDate() == null) {
				details.createCell(21).setCellValue(" ");
			} else {
				details.createCell(21).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(form26Q.getDeductDate()));
			}
			if (form26Q.getRateTaxDeduct() == null) {
				details.createCell(22).setCellValue(" ");
			} else {
				details.createCell(22).setCellValue(form26Q.getRateTaxDeduct());
			}
			if (form26Q.getRemarks() == null) {
				details.createCell(23).setCellValue(" ");
			} else {
				details.createCell(23).setCellValue(form26Q.getRemarks());
			}
			if (form26Q.getCertificateNo() == null) {
				details.createCell(24).setCellValue(" ");
			} else {
				details.createCell(24).setCellValue(form26Q.getCertificateNo());
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

}
