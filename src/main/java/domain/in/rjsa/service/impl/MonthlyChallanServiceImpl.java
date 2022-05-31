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

import domain.in.rjsa.dao.MonthlyChallanDao;
import domain.in.rjsa.excel.MonthlyChallanExcel;
import domain.in.rjsa.model.fy.MonthlyChallan;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.MonthlyChallanService;

@Transactional("transactionManager")
@Service("monthlyChallanService")
public class MonthlyChallanServiceImpl extends AbstractServiceFY<Long, MonthlyChallan, MonthlyChallanDao> implements MonthlyChallanService{
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
	//	@Override
	//	public MonthlyChallan getByKey(Long id) {
			// TODO Auto-generated method stub
		//	return dao.getByKey(id);
		//}
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

			for (MonthlyChallan monthChallan : listUsers) {
				Workbook wb = monthlyChallanExcel.getWorkbook();
				Sheet monthlyChallan = wb.getSheet("MonthlyChallan");
				Row details = monthlyChallan.createRow(row);
				details.createCell(0).setCellValue(row);

				if (monthChallan.getBranchCode() == null) {
					details.createCell(1).setCellValue(" ");
				} else {
					details.createCell(1).setCellValue(monthChallan.getBranchCode());
				}
				if (monthChallan.getMonthFY() == null) {
					details.createCell(2).setCellValue(" ");
				} else {
					details.createCell(2).setCellValue(monthChallan.getMonthFY());
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
		
	}
