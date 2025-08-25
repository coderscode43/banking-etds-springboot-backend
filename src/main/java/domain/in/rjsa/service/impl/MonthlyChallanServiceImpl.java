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
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.MonthlyChallanDao;
import domain.in.rjsa.excel.MonthlyChallanExcel;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.fy.MonthlyChallan;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.MonthlyChallanService;

@Transactional("transactionManager")
@Service("monthlyChallanService")
public class MonthlyChallanServiceImpl extends AbstractServiceFY<Long, MonthlyChallan, MonthlyChallanDao>
		implements MonthlyChallanService {
	@Autowired
	MonthlyChallanDao dao;

	MonthlyChallanExcel monthlyChallanExcel;
	public String path;
	public String ExcelFile;

	@Override
	public MonthlyChallanDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	// @Override
	// public MonthlyChallan getByKey(Long id) {
	// TODO Auto-generated method stub
	// return dao.getByKey(id);
	// }
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<MonthlyChallan> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		monthlyChallanExcel = new MonthlyChallanExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		monthlyChallanExcel.initialise(path + "static/report/TDS-" + timestamp + "-MonthlyChallan.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-MonthlyChallan.xlsx";

		int row = 1;
		int part = 1;

		Workbook wb = monthlyChallanExcel.getWorkbook();
		Sheet monthlyChallan = wb.getSheet("MonthlyChallan-" + part);
		for (MonthlyChallan monthChallan : listUsers) {

			Row details = monthlyChallan.createRow(row);
			details.createCell(0).setCellValue(row);

			if (monthChallan.getBranchCode() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(monthChallan.getBranchCode());
			}
			if (monthChallan.getMonth() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(monthChallan.getMonth());
			}
			if (monthChallan.getChallanHeading() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(monthChallan.getChallanHeading());
			}
			if (monthChallan.getAmtAsPerFinacle() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(monthChallan.getAmtAsPerFinacle());
			}
			if (monthChallan.getAmtAsPerTaxCalculation() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(monthChallan.getAmtAsPerTaxCalculation());
			}
			if (monthChallan.getFy() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(monthChallan.getFy());
			}
			if (monthChallan.getRemarks() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(monthChallan.getRemarks());
			}
			if (row > 1000000) {
				part++;
				wb = monthlyChallanExcel.getWorkbook();
				monthlyChallan = monthlyChallanExcel.initializeSheet("MonthlyChallan-" + part);
				row = 0;
			}
			row++;

		}
		monthlyChallanExcel.close();
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
	public void addData(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<JSONObject> jsonObject = mapper.readValue(json, new TypeReference<List<JSONObject>>() {
			});

			for (JSONObject object : jsonObject) {
				MonthlyChallan monthlyChallan = new MonthlyChallan();
				monthlyChallan.setData(object);
				dao.persist(monthlyChallan);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}
	}

}
