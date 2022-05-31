package domain.in.rjsa.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.H15Dao;
import domain.in.rjsa.excel.H15Excel;
import domain.in.rjsa.model.fy.H15;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.H15Service;

@Transactional("transactionManager")
@Service("H15Service")
public class H15ServiceImpl extends AbstractServiceFY<Long, H15, H15Dao>
implements H15Service {

	@Autowired
	H15Dao dao;
	
	H15Excel h15Excel;
	public static String path;
	public String ExcelFile;

	@Override
	public H15Dao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public H15 getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<H15> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		h15Excel = new H15Excel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		h15Excel.initialise(path + "static/report/TDS-" + timestamp + "-h15.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-h15.xlsx";

		int row = 1;

		for (H15 h15 : listUsers) {
			Workbook wb = h15Excel.getWorkbook();
			Sheet H15 = wb.getSheet("H15");
			Row details = H15.createRow(row);
			details.createCell(0).setCellValue(row);

			if (h15.getQuarter() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(h15.getQuarter());
			}
			if (h15.getMonth() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(h15.getMonth());
			}
			if (h15.getCustVendId() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(h15.getCustVendId());
			}
			if (h15.getAccNo() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(h15.getAccNo());
			}
			if (h15.getBranchCode() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(h15.getBranchCode());
			}
			if (h15.getTan() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(h15.getTan());
			}
			if (h15.getUniqueRefNo() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(h15.getUniqueRefNo());
			}
			if (h15.getUniqueIdentificationNo() == null) {
				details.createCell(8).setCellValue(" ");
			} else {
				details.createCell(8).setCellValue(h15.getUniqueIdentificationNo());
			}
			if (h15.getIdentificationNumberofRelevantInvestmentAccount() == null) {
				details.createCell(9).setCellValue(" ");
			} else {
				details.createCell(9).setCellValue(h15.getIdentificationNumberofRelevantInvestmentAccount());
			}
			if (h15.getNatureofIncome() == null) {
				details.createCell(10).setCellValue(" ");
			} else {
				details.createCell(10).setCellValue(h15.getNatureofIncome());
			}
			if (h15.getSectionUnderWhichTaxisDeductible() == null) {
				details.createCell(11).setCellValue(" ");
			} else {
				details.createCell(11).setCellValue(h15.getSectionUnderWhichTaxisDeductible());
			}
			if (h15.getAmountofIncome() == null) {
				details.createCell(12).setCellValue(" ");
			} else {
				details.createCell(12).setCellValue(h15.getAmountofIncome());
			}
			if (h15.getNameofAssesseeDeclarant() == null) {
				details.createCell(13).setCellValue(" ");
			} else {
				details.createCell(13).setCellValue(h15.getNameofAssesseeDeclarant());
			}
			if (h15.getPanoftheAssessee() == null) {
				details.createCell(14).setCellValue(" ");
			} else {
				details.createCell(14).setCellValue(h15.getPanoftheAssessee());
			}
			if (h15.getAadhaarNumberoftheAssessee() == null) {
				details.createCell(15).setCellValue(" ");
			} else {
				details.createCell(15).setCellValue(h15.getAadhaarNumberoftheAssessee());
			}
			if (h15.getDateofBirth() == null) {
				details.createCell(16).setCellValue(" ");
			} else {
				details.createCell(16).setCellValue(h15.getDateofBirth());
			}
			if (h15.getPreviousYearforwhichdeclarationisbeingmade() == null) {
				details.createCell(17).setCellValue(" ");
			} else {
				details.createCell(17).setCellValue(h15.getPreviousYearforwhichdeclarationisbeingmade());
			}
			if (h15.getCountry() == null) {
				details.createCell(18).setCellValue(" ");
			} else {
				details.createCell(18).setCellValue(h15.getCountry());
			}
			if (h15.getFlatDoorBuilding() == null) {
				details.createCell(19).setCellValue(" ");
			} else {
				details.createCell(19).setCellValue(h15.getFlatDoorBuilding());
			}
			if (h15.getRoadStreetBlockSector() == null) {
				details.createCell(20).setCellValue(" ");
			} else {
				details.createCell(20).setCellValue(h15.getRoadStreetBlockSector());
			}
			if (h15.getPincode() == null) {
				details.createCell(21).setCellValue(" ");
			} else {
				details.createCell(21).setCellValue(h15.getPincode());
			}
			if (h15.getPostOffice() == null) {
				details.createCell(22).setCellValue(" ");
			} else {
				details.createCell(22).setCellValue(h15.getPostOffice());
			}
			if (h15.getDistrict() == null) {
				details.createCell(23).setCellValue(" ");
			} else {
				details.createCell(23).setCellValue(h15.getDistrict());
			}
			if (h15.getState() == null) {
				details.createCell(24).setCellValue(" ");
			} else {
				details.createCell(24).setCellValue(h15.getState());
			}
			if (h15.getEmail() == null) {
				details.createCell(25).setCellValue(" ");
			} else {
				details.createCell(25).setCellValue(h15.getEmail());
			}
			if (h15.getSTDCode() == null) {
				details.createCell(26).setCellValue(" ");
			} else {
				details.createCell(26).setCellValue(h15.getSTDCode());
			}
			if (h15.getTelephoneNo() == null) {
				details.createCell(27).setCellValue(" ");
			} else {
				details.createCell(27).setCellValue(h15.getTelephoneNo());
			}
			if (h15.getMobileNo() == null) {
				details.createCell(28).setCellValue(" ");
			} else {
				details.createCell(28).setCellValue(h15.getMobileNo());
			}
			if (h15.getWhetherAssessedtotax() == null) {
				details.createCell(29).setCellValue(" ");
			} else {
				details.createCell(29).setCellValue(h15.getWhetherAssessedtotax());
			}
			if (h15.getLatestassessmentyearforwhichassessed() == null) {
				details.createCell(30).setCellValue(" ");
			} else {
				details.createCell(30).setCellValue(h15.getLatestassessmentyearforwhichassessed());
			}
			if (h15.getEstimatedtotalincomeofthePY() == null) {
				details.createCell(31).setCellValue(" ");
			} else {
				details.createCell(31).setCellValue(h15.getEstimatedtotalincomeofthePY());
			}
			if (h15.getEstimatedIncomeforwhichthisdeclarationismade() == null) {
				details.createCell(32).setCellValue(" ");
			} else {
				details.createCell(32).setCellValue(h15.getEstimatedIncomeforwhichthisdeclarationismade());
			}
			if (h15.getTotalNoofFormNo15Hfiled() == null) {
				details.createCell(33).setCellValue(" ");
			} else {
				details.createCell(33).setCellValue(h15.getTotalNoofFormNo15Hfiled());
			}
			if (h15.getAggregateAmountofincomeforwhichFormNo15Hfiled() == null) {
				details.createCell(34).setCellValue(" ");
			} else {
				details.createCell(34).setCellValue(h15.getAggregateAmountofincomeforwhichFormNo15Hfiled());
			}if (h15.getAmountofincomepaid() == null) {
				details.createCell(35).setCellValue(" ");
			} else {
				details.createCell(35).setCellValue(h15.getAmountofIncome());
			}if (h15.getDateonwhichDeclarationisreceived() == null) {
				details.createCell(36).setCellValue(" ");
			} else {
				details.createCell(36).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(h15.getDateonwhichDeclarationisreceived()));
			}if (h15.getAmountofincomepaid() == null) {
				details.createCell(37).setCellValue(" ");
			} else {
				details.createCell(37).setCellValue(h15.getAmountofincomepaid());
			}if (h15.getDateonwhichtheincomehasbeenpaidcredited() == null) {
				details.createCell(38).setCellValue(" ");
			} else {
				details.createCell(38).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(h15.getDateonwhichtheincomehasbeenpaidcredited()));
			}if (h15.getFy() == null) {
				details.createCell(39).setCellValue(" ");
			} else {
				details.createCell(39).setCellValue(h15.getFy());
			}
			if (h15.getWarning() == null) {
				details.createCell(40).setCellValue(" ");
			} else {
				details.createCell(40).setCellValue(h15.getWarning());
			}
			if (h15.getError() == null) {
				details.createCell(41).setCellValue(" ");
			} else {
				details.createCell(41).setCellValue(h15.getError());
			}
			




			
			row++;

		}
		h15Excel.close();
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
