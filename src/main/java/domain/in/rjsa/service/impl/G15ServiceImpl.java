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

import domain.in.rjsa.dao.G15Dao;
import domain.in.rjsa.excel.G15Excel;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.fy.G15;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.G15Service;

@Transactional("transactionManager")
@Service("G15Service")
public class G15ServiceImpl extends AbstractServiceFY<Long, G15, G15Dao>
implements G15Service {

	@Autowired
	G15Dao dao;
	
	G15Excel g15Excel;
	public static String path;
	public String ExcelFile;

	@Override
	public G15Dao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public G15 getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<G15> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		g15Excel = new G15Excel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		g15Excel.initialise(path + "static/report/TDS-" + timestamp + "-g15.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-g15.xlsx";

		int row = 1;
		int part=1;
		
		Workbook wb = g15Excel.getWorkbook();
		Sheet G15 = wb.getSheet("G15-"+part);
		for (G15 g15 : listUsers) {
		
			Row details = G15.createRow(row);
			details.createCell(0).setCellValue(row);

			if (g15.getQuarter() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(g15.getQuarter());
			}
			if (g15.getMonth() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(g15.getMonth());
			}
	
			if (g15.getAccNo() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(g15.getAccNo());
			}
			if (g15.getBranchCode() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(g15.getBranchCode());
			}
			if (g15.getTan() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(g15.getTan());
			}
			if (g15.getNameofAssesseeDeclarant() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(g15.getNameofAssesseeDeclarant());
			}
			if (g15.getPanoftheAssessee() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(g15.getPanoftheAssessee());
			}
			if (g15.getStatus() == null) {
				details.createCell(8).setCellValue(" ");
			} else {
				details.createCell(8).setCellValue(g15.getStatus());
			}
			if (g15.getSubStatus() == null) {
				details.createCell(9).setCellValue(" ");
			} else {
				details.createCell(9).setCellValue(g15.getSubStatus());
			}
			if (g15.getFy() == null) {
				details.createCell(10).setCellValue(" ");
			} else {
				details.createCell(10).setCellValue(g15.getFy());
			}
			if (g15.getResidentialStatus() == null) {
				details.createCell(11).setCellValue(" ");
			} else {
				details.createCell(11).setCellValue(g15.getResidentialStatus());
			}
			if (g15.getAmountofIncome() == null) {
				details.createCell(12).setCellValue(" ");
			} else {
				details.createCell(12).setCellValue(g15.getAmountofIncome());
			}
			if (g15.getUniqueIdentificationNo() == null) {
				details.createCell(13).setCellValue(" ");
			} else {
				details.createCell(13).setCellValue(g15.getUniqueIdentificationNo());
			}
			if (g15.getIdentificationNumberofRelevantInvestmentAccount() == null) {
				details.createCell(14).setCellValue(" ");
			} else {
				details.createCell(14).setCellValue(g15.getIdentificationNumberofRelevantInvestmentAccount());
			}
			if (g15.getNatureofIncome() == null) {
				details.createCell(15).setCellValue(" ");
			} else {
				details.createCell(15).setCellValue(g15.getNatureofIncome());
			}
			if (g15.getSectionUnderWhichTaxisDeductible() == null) {
				details.createCell(16).setCellValue(" ");
			} else {
				details.createCell(16).setCellValue(g15.getSectionUnderWhichTaxisDeductible());
			}
			if (g15.getAadhaarNumber() == null) {
				details.createCell(17).setCellValue(" ");
			} else {
				details.createCell(17).setCellValue(g15.getAadhaarNumber());
			}
			if (g15.getCountry() == null) {
				details.createCell(18).setCellValue(" ");
			} else {
				details.createCell(18).setCellValue(g15.getCountry());
			}
			if (g15.getFlatDoorBuilding() == null) {
				details.createCell(19).setCellValue(" ");
			} else {
				details.createCell(19).setCellValue(g15.getFlatDoorBuilding());
			}
			if (g15.getRoadStreetBlockSector() == null) {
				details.createCell(20).setCellValue(" ");
			} else {
				details.createCell(20).setCellValue(g15.getRoadStreetBlockSector());
			}
			if (g15.getPincode() == null) {
				details.createCell(21).setCellValue(" ");
			} else {
				details.createCell(21).setCellValue(g15.getPincode());
			}
			if (g15.getPostOffice() == null) {
				details.createCell(22).setCellValue(" ");
			} else {
				details.createCell(22).setCellValue(g15.getPostOffice());
			}
			if (g15.getAreaLocality() == null) {
				details.createCell(23).setCellValue(" ");
			} else {
				details.createCell(23).setCellValue(g15.getAreaLocality());
			}
			if (g15.getDistrict() == null) {
				details.createCell(24).setCellValue(" ");
			} else {
				details.createCell(24).setCellValue(g15.getDistrict());
			}
			if (g15.getState() == null) {
				details.createCell(25).setCellValue(" ");
			} else {
				details.createCell(25).setCellValue(g15.getState());
			}
			if (g15.getEmail() == null) {
				details.createCell(26).setCellValue(" ");
			} else {
				details.createCell(26).setCellValue(g15.getEmail());
			}
			if (g15.getTelephoneNo() == null) {
				details.createCell(27).setCellValue(" ");
			} else {
				details.createCell(27).setCellValue(g15.getTelephoneNo());
			}
			if (g15.getMobileNo() == null) {
				details.createCell(28).setCellValue(" ");
			} else {
				details.createCell(28).setCellValue(g15.getMobileNo());
			}
			if (g15.getWhetherAssessedtotaxundertheIncometaxAct1961() == null) {
				details.createCell(29).setCellValue(" ");
			} else {
				details.createCell(29).setCellValue(g15.getWhetherAssessedtotaxundertheIncometaxAct1961());
			}
			if (g15.getIfyeslatestassessmentyearforwhichassessed() == null) {
				details.createCell(30).setCellValue(" ");
			} else {
				details.createCell(30).setCellValue(g15.getIfyeslatestassessmentyearforwhichassessed());
			}
			if (g15.getEstimatedIncomeforwhichthisdeclarationismade() == null) {
				details.createCell(31).setCellValue(" ");
			} else {
				details.createCell(31).setCellValue(g15.getEstimatedIncomeforwhichthisdeclarationismade());
			}
			if (g15.getEstimatedtotalincomeofthePY() == null) {
				details.createCell(32).setCellValue(" ");
			} else {
				details.createCell(32).setCellValue(g15.getEstimatedtotalincomeofthePY());
			}
			if (g15.getTotalNoofFormNo15Gfiled() == null) {
				details.createCell(33).setCellValue(" ");
			} else {
				details.createCell(33).setCellValue(g15.getTotalNoofFormNo15Gfiled());
			}
			if (g15.getAggregateAmountofincomeforwhichFormNo15Gfiled() == null) {
				details.createCell(34).setCellValue(" ");
			} else {
				details.createCell(34).setCellValue(g15.getAggregateAmountofincomeforwhichFormNo15Gfiled());
			}
			if (g15.getDateonwhichDeclarationisreceived() == null) {
				details.createCell(35).setCellValue(" ");
			} else {
				details.createCell(35).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(g15.getDateonwhichDeclarationisreceived()));
			}
			if (g15.getAmountofincomepaid() == null) {
				details.createCell(36).setCellValue(" ");
			} else {
				details.createCell(36).setCellValue(g15.getAmountofincomepaid());
			}
			if (g15.getDateonwhichtheincomehasbeenpaidcredited() == null) {
				details.createCell(37).setCellValue(" ");
			} else {
				details.createCell(37).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(g15.getDateonwhichtheincomehasbeenpaidcredited()));
			}
			if (g15.getPreviousYearforwhichdeclarationisbeingmade() == null) {
				details.createCell(38).setCellValue(" ");
			} else {
				details.createCell(38).setCellValue(g15.getPreviousYearforwhichdeclarationisbeingmade());
			}
			if (g15.getWarning() == null) {
				details.createCell(39).setCellValue(" ");
			} else {
				details.createCell(39).setCellValue(g15.getWarning());
			}
			if (g15.getError() == null) {
				details.createCell(40).setCellValue(" ");
			} else {
				details.createCell(40).setCellValue(g15.getError());
			}
			
			if (row > 1000000) {
				part++;
				wb = g15Excel.getWorkbook();
				G15 = g15Excel.initializeSheet("G15-" + part);
				row =0;
			}
			row++;

		}
		g15Excel.close();
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

	@Override
	public List<?> findAll(HashMap<String, Object> constrains, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return getPrimaryDao().findall(constrains, pageNo, resultPerPage);
	}

	@Override
	public void addData(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<JSONObject> jsonObject =  mapper.readValue(json, new TypeReference <List<JSONObject>>() {
			});
			
			for (JSONObject object : jsonObject) {
				G15 g15 = new G15();
				g15.setData(object);
				dao.persist(g15);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}
	}
}
