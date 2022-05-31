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

import domain.in.rjsa.dao.Regular27EQDeducteeDao;
import domain.in.rjsa.excel.Form27EQDeducteeExcel;
import domain.in.rjsa.model.fy.Regular27EQDeductee;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.Regular27EQDeducteeService;
@Transactional("transactionManager")
@Service("regular27EQDeducteeService")
public class Regular27EQDeducteeServiceImpl extends AbstractServiceFY<Long, Regular27EQDeductee, Regular27EQDeducteeDao> implements Regular27EQDeducteeService{

	@Autowired 
	Regular27EQDeducteeDao dao;
	
	Form27EQDeducteeExcel form27EQDeduceteeExcel;
	public static String path;
	public String ExcelFile;
	
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

		for (Regular27EQDeductee form27EQ : listUsers) {
			Workbook wb = form27EQDeduceteeExcel.getWorkbook();
			Sheet form27EQDeductee = wb.getSheet("form27EQDeductee");
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
			if (form27EQ.getCustVendId() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(form27EQ.getCustVendId());
			}
			if (form27EQ.getUniqueRefNo() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(form27EQ.getUniqueRefNo());
			}
			if (form27EQ.getAccNo() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(form27EQ.getAccNo());
			}
			if (form27EQ.getChallanHeading() == null) {
				details.createCell(8).setCellValue(" ");
			} else {
				details.createCell(8).setCellValue(form27EQ.getChallanHeading());
			}
			if (form27EQ.getDeducteeReferenceNo() == null) {
				details.createCell(9).setCellValue(" ");
			} else {
				details.createCell(9).setCellValue(form27EQ.getDeducteeReferenceNo());
			}
			if (form27EQ.getDeducteeCode() == null) {
				details.createCell(10).setCellValue(" ");
			} else {
				details.createCell(10).setCellValue(form27EQ.getDeducteeCode());
			}
			if (form27EQ.getPanofthedeductee() == null) {
				details.createCell(11).setCellValue(" ");
			} else {
				details.createCell(11).setCellValue(form27EQ.getPanofthedeductee());
			}
			if (form27EQ.getNameoftheDeductee() == null) {
				details.createCell(12).setCellValue(" ");
			} else {
				details.createCell(12).setCellValue(form27EQ.getNameoftheDeductee());
			}
			if (form27EQ.getAmountReceiptDebited() == null) {
				details.createCell(13).setCellValue(" ");
			} else {
				details.createCell(13).setCellValue(form27EQ.getAmountReceiptDebited());
			}
			if (form27EQ.getdateofReceivedDebited() == null) {
				details.createCell(14).setCellValue(" ");
			} else {
				details.createCell(14)
						.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(form27EQ.getdateofReceivedDebited()));
			}
			if (form27EQ.getTcs() == null) {
				details.createCell(15).setCellValue(" ");
			} else {
				details.createCell(15).setCellValue(form27EQ.getTcs());
			}
			if (form27EQ.getSurcharge() == null) {
				details.createCell(16).setCellValue(" ");
			} else {
				details.createCell(16).setCellValue(form27EQ.getSurcharge());
			}
			if (form27EQ.getEducationCess() == null) {
				details.createCell(17).setCellValue(" ");
			} else {
				details.createCell(17).setCellValue(form27EQ.getEducationCess());
			}
			if (form27EQ.getTotalTaxCollected() == null) {
				details.createCell(18).setCellValue(" ");
			} else {
				details.createCell(18).setCellValue(form27EQ.getTotalTaxCollected());
			}
			if (form27EQ.getTotalTaxDeposited() == null) {
				details.createCell(19).setCellValue(" ");
			} else {
				details.createCell(19).setCellValue(form27EQ.getTotalTaxDeposited());
			}
			if (form27EQ.getdateofCollected() == null) {
				details.createCell(20).setCellValue(" ");
			} else {
				details.createCell(20).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(form27EQ.getdateofCollected()));
			}
			if (form27EQ.getTotalValueofPurchase() == null) {
				details.createCell(21).setCellValue(" ");
			} else {
				details.createCell(21).setCellValue(form27EQ.getTotalValueofPurchase());
			}
			if (form27EQ.getRateatwhichTaxCollected() == null) {
				details.createCell(22).setCellValue(" ");
			} else {
				details.createCell(22).setCellValue(form27EQ.getRateatwhichTaxCollected());
			}
			if (form27EQ.getReasonforNonCollection() == null) {
				details.createCell(23).setCellValue(" ");
			} else {
				details.createCell(23).setCellValue(form27EQ.getReasonforNonCollection());
			}
			if (form27EQ.getCollectionCode() == null) {
				details.createCell(24).setCellValue(" ");
			} else {
				details.createCell(24).setCellValue(form27EQ.getCollectionCode());
			}
			if (form27EQ.getCertificatenumber() == null) {
				details.createCell(25).setCellValue(" ");
			} else {
				details.createCell(25).setCellValue(form27EQ.getCertificatenumber());
			}
			if (form27EQ.getDeducteeisNonResident() == null) {
				details.createCell(26).setCellValue(" ");
			} else {
				details.createCell(26).setCellValue(form27EQ.getDeducteeisNonResident());
			}
			if (form27EQ.getPermanentEstablishment() == null) {
				details.createCell(27).setCellValue(" ");
			} else {
				details.createCell(27).setCellValue(form27EQ.getPermanentEstablishment());
			}
			if (form27EQ.getReasonForNonCollectionForG() == null) {
				details.createCell(28).setCellValue(" ");
			} else {
				details.createCell(28).setCellValue(form27EQ.getReasonForNonCollectionForG());
			}
			if (form27EQ.getIfAnswerTo681AisyesthenChallanNumber() == null) {
				details.createCell(29).setCellValue(" ");
			} else {
				details.createCell(29).setCellValue(form27EQ.getIfAnswerTo681AisyesthenChallanNumber());
			}
			if (form27EQ.getIfAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment() == null) {
				details.createCell(30).setCellValue(" ");
			} else {
				details.createCell(30).setCellValue(form27EQ.getIfAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment());
			}
			if (form27EQ.getErrorDescription() == null) {
				details.createCell(31).setCellValue(" ");
			} else {
				details.createCell(31).setCellValue(form27EQ.getErrorDescription());
			}
			if (form27EQ.getWarningDescription() == null) {
				details.createCell(32).setCellValue(" ");
			} else {
				details.createCell(32).setCellValue(form27EQ.getWarningDescription());
			}
			if (form27EQ.getShortDeduction() == null) {
				details.createCell(33).setCellValue(" ");
			} else {
				details.createCell(33).setCellValue(form27EQ.getShortDeduction());
			}
			if (form27EQ.getInterestOnShortDeduction() == null) {
				details.createCell(34).setCellValue(" ");
			} else {
				details.createCell(34).setCellValue(form27EQ.getInterestOnShortDeduction());
			}
			if (form27EQ.getInterestOnLatePayment() == null) {
				details.createCell(35).setCellValue(" ");
			} else {
				details.createCell(35).setCellValue(form27EQ.getInterestOnLatePayment());
			}
			if (form27EQ.getInterestOnLateDeduction() == null) {
				details.createCell(36).setCellValue(" ");
			} else {
				details.createCell(36).setCellValue(form27EQ.getInterestOnLateDeduction());
			}
			if (form27EQ.getTAN() == null) {
				details.createCell(37).setCellValue(" ");
			} else {
				details.createCell(37).setCellValue(form27EQ.getTAN());
			}
			if (form27EQ.getComments() == null) {
				details.createCell(38).setCellValue(" ");
			} else {
				details.createCell(38).setCellValue(form27EQ.getComments());
			}
			if (form27EQ.isResolved()) {
				details.createCell(39).setCellValue("Not Resolved");
			} else {
				details.createCell(39).setCellValue("Resolved");
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
	

}
