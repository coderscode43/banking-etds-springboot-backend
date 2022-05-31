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

import domain.in.rjsa.dao.Regular27QDeducteeDao;
import domain.in.rjsa.excel.Form27QDeducteeExcel;
import domain.in.rjsa.model.fy.Regular27QDeductee;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.Regular27QDeducteeService;
@Transactional("transactionManager")
@Service("regular27QDeducteeService")
public class Regular27QDeducteeServiceImpl extends AbstractServiceFY<Long, Regular27QDeductee, Regular27QDeducteeDao> implements Regular27QDeducteeService{

	@Autowired 
	Regular27QDeducteeDao dao;
	
	Form27QDeducteeExcel form27QDeduceteeExcel;
	public static String path;
	public String ExcelFile;
	
	@Override
	public Regular27QDeducteeDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Regular27QDeductee getByKey(Long id) {
		return dao.getByKey(id);
	}

	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<Regular27QDeductee> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		form27QDeduceteeExcel = new Form27QDeducteeExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		form27QDeduceteeExcel.initialise(path + "static/report/TDS-" + timestamp + "-27QDeductee.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-27QDeductee.xlsx";

		int row = 1;

		for (Regular27QDeductee form27Q : listUsers) {
			Workbook wb = form27QDeduceteeExcel.getWorkbook();
			Sheet form27QDeductee = wb.getSheet("form27QDeductee");
			Row details = form27QDeductee.createRow(row);
			details.createCell(0).setCellValue(row);

			if (form27Q.getQuarter() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(form27Q.getQuarter());
			}
			if (form27Q.getMonth() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(form27Q.getMonth());
			}
			if (form27Q.getRoCode() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(form27Q.getRoCode());
			}
			if (form27Q.getBranchCode() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(form27Q.getBranchCode());
			}
			if (form27Q.getCustVendId() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(form27Q.getCustVendId());
			}
			if (form27Q.getUniqueRefNo() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(form27Q.getUniqueRefNo());
			}
			if (form27Q.getAccNo() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(form27Q.getAccNo());
			}
			if (form27Q.getChallanHeading() == null) {
				details.createCell(8).setCellValue(" ");
			} else {
				details.createCell(8).setCellValue(form27Q.getChallanHeading());
			}
			if (form27Q.getDeducteeRefNo() == null) {
				details.createCell(9).setCellValue(" ");
			} else {
				details.createCell(9).setCellValue(form27Q.getDeducteeRefNo());
			}
			if (form27Q.getDeducteeCode() == null) {
				details.createCell(10).setCellValue(" ");
			} else {
				details.createCell(10).setCellValue(form27Q.getDeducteeCode());
			}
			if (form27Q.getPan() == null) {
				details.createCell(11).setCellValue(" ");
			} else {
				details.createCell(11).setCellValue(form27Q.getPan());
			}
			if (form27Q.getName() == null) {
				details.createCell(12).setCellValue(" ");
			} else {
				details.createCell(12).setCellValue(form27Q.getName());
			}
			if (form27Q.getSectionCode() == null) {
				details.createCell(13).setCellValue(" ");
			} else {
				details.createCell(13).setCellValue(form27Q.getSectionCode());
			}
			if (form27Q.getDate() == null) {
				details.createCell(14).setCellValue(" ");
			} else {
				details.createCell(14)
						.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(form27Q.getDate()));
			}
			if (form27Q.getAmountPaid() == null) {
				details.createCell(15).setCellValue(" ");
			} else {
				details.createCell(15).setCellValue(form27Q.getAmountPaid());
			}
			if (form27Q.getTds() == null) {
				details.createCell(16).setCellValue(" ");
			} else {
				details.createCell(16).setCellValue(form27Q.getTds());
			}
			if (form27Q.getSurcharge() == null) {
				details.createCell(17).setCellValue(" ");
			} else {
				details.createCell(17).setCellValue(form27Q.getSurcharge());
			}
			if (form27Q.getEducationCess() == null) {
				details.createCell(18).setCellValue(" ");
			} else {
				details.createCell(18).setCellValue(form27Q.getEducationCess());
			}
			if (form27Q.getTotalTaxDeducted() == null) {
				details.createCell(19).setCellValue(" ");
			} else {
				details.createCell(19).setCellValue(form27Q.getTotalTaxDeducted());
			}
			if (form27Q.getTotalTaxDeposited() == null) {
				details.createCell(20).setCellValue(" ");
			} else {
				details.createCell(20).setCellValue(form27Q.getTotalTaxDeposited());
			}
			if (form27Q.getDateOfDeduction() == null) {
				details.createCell(21).setCellValue(" ");
			} else {
				details.createCell(21).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(form27Q.getDateOfDeduction()));
			}
			if (form27Q.getRateAtWhichTaxDeducted() == null) {
				details.createCell(22).setCellValue(" ");
			} else {
				details.createCell(22).setCellValue(form27Q.getRateAtWhichTaxDeducted());
			}
			if (form27Q.getReasonForNonDeduction() == null) {
				details.createCell(23).setCellValue(" ");
			} else {
				details.createCell(23).setCellValue(form27Q.getReasonForNonDeduction());
			}
			if (form27Q.getGrossingUpIndicator() == null) {
				details.createCell(24).setCellValue(" ");
			} else {
				details.createCell(24).setCellValue(form27Q.getGrossingUpIndicator());
			}
			if (form27Q.getNoOfCertificateUnderSection() == null) {
				details.createCell(25).setCellValue(" ");
			} else {
				details.createCell(25).setCellValue(form27Q.getNoOfCertificateUnderSection());
			}
			if (form27Q.getTdsRateAsPerItActs() == null) {
				details.createCell(26).setCellValue(" ");
			} else {
				details.createCell(26).setCellValue(form27Q.getTdsRateAsPerItActs());
			}
			if (form27Q.getNatureOfRemittance() == null) {
				details.createCell(27).setCellValue(" ");
			} else {
				details.createCell(27).setCellValue(form27Q.getNatureOfRemittance());
			}
			if (form27Q.getUniqueAcknowledgeNo() == null) {
				details.createCell(28).setCellValue(" ");
			} else {
				details.createCell(28).setCellValue(form27Q.getUniqueAcknowledgeNo());
			}
			if (form27Q.getCountryOfResidence() == null) {
				details.createCell(29).setCellValue(" ");
			} else {
				details.createCell(29).setCellValue(form27Q.getCountryOfResidence());
			}
			if (form27Q.getEmailId() == null) {
				details.createCell(30).setCellValue(" ");
			} else {
				details.createCell(30).setCellValue(form27Q.getEmailId());
			}
			if (form27Q.getContactNoOfDeductee() == null) {
				details.createCell(31).setCellValue(" ");
			} else {
				details.createCell(31).setCellValue(form27Q.getContactNoOfDeductee());
			}
			if (form27Q.getAddressOfDeductee() == null) {
				details.createCell(32).setCellValue(" ");
			} else {
				details.createCell(32).setCellValue(form27Q.getAddressOfDeductee());
			}
			if (form27Q.getTaxIdentificationNo() == null) {
				details.createCell(33).setCellValue(" ");
			} else {
				details.createCell(33).setCellValue(form27Q.getTaxIdentificationNo());
			}
			if (form27Q.getCashWithdrawal194N() == null) {
				details.createCell(34).setCellValue(" ");
			} else {
				details.createCell(34).setCellValue(form27Q.getCashWithdrawal194N());
			}
			if (form27Q.getCashWithdrawal194N20Lto1Cr() == null) {
				details.createCell(35).setCellValue(" ");
			} else {
				details.createCell(35).setCellValue(form27Q.getCashWithdrawal194N20Lto1Cr());
			}
			if (form27Q.getCashWithdrawal194N1Cr() == null) {
				details.createCell(36).setCellValue(" ");
			} else {
				details.createCell(36).setCellValue(form27Q.getCashWithdrawal194N1Cr()
						);
			}
			if (form27Q.getErrorDescription() == null) {
				details.createCell(37).setCellValue(" ");
			} else {
				details.createCell(37).setCellValue(form27Q.getErrorDescription());
			}
			if (form27Q.getWarningDescription() == null) {
				details.createCell(38).setCellValue(" ");
			} else {
				details.createCell(38).setCellValue(form27Q.getWarningDescription());
			}
			if (form27Q.getShortDeduction() == null) {
				details.createCell(39).setCellValue(" ");
			} else {
				details.createCell(39).setCellValue(form27Q.getShortDeduction());
			}
			if (form27Q.getInterestOnLatePayment() == null) {
				details.createCell(40).setCellValue(" ");
			} else {
				details.createCell(40).setCellValue(form27Q.getInterestOnLatePayment());
			}
			if (form27Q.getInterestOnLateDeduction() == null) {
				details.createCell(41).setCellValue(" ");
			} else {
				details.createCell(41).setCellValue(form27Q.getInterestOnLateDeduction());
			}
			if (form27Q.getTAN() == null) {
				details.createCell(42).setCellValue(" ");
			} else {
				details.createCell(42).setCellValue(form27Q.getTAN());
			}
			if (form27Q.getComments() == null) {
				details.createCell(43).setCellValue(" ");
			} else {
				details.createCell(43).setCellValue(form27Q.getComments());
			}
			if (form27Q.isResolved()) {
				details.createCell(44).setCellValue("Not Resolved");
			} else {
				details.createCell(44).setCellValue("Resolved");
			}

			row++;

		}
		form27QDeduceteeExcel.close();
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

}
