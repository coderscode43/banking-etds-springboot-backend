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

import domain.in.rjsa.dao.MisReportDao;
import domain.in.rjsa.excel.MisReportExcel;
import domain.in.rjsa.model.fy.MisReport;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.MisReportService;
@Transactional("transactionManager")
@Service("misReportService")
public class MisReportServiceImpl extends AbstractServiceFY<Long, MisReport, MisReportDao> implements  MisReportService {
	@Autowired
	MisReportDao dao;
	
	MisReportExcel misReportExcel;
	public String path;
	public String ExcelFile;
	
	@Override
	public MisReportDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
//	@Override
//	public MisReport getByKey(Long id) {
//		// TODO Auto-generated method stub
//		return dao.getByKey(id);
//	}
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<MisReport> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		misReportExcel = new MisReportExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		misReportExcel.initialise(path + "static/report/TDS-" + timestamp + "-MisReport.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-MisReport.xlsx";

		int row = 1;
		int part = 1;
		
		Workbook wb = misReportExcel.getWorkbook();
		Sheet misReport = wb.getSheet("misReport-"+part);

		for (MisReport MIS : listUsers) {
			
			Row details = misReport.createRow(row);
			details.createCell(0).setCellValue(row);

			if (MIS.getBranchCode() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(MIS.getBranchCode());
			}
			if (MIS.getFy() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(MIS.getFy());
			}
			if (MIS.getFromDate() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(MIS.getFromDate()));
			}
			if (MIS.getToDate() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(MIS.getToDate()));
			}
			if (MIS.getReportType() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(MIS.getReportType());
			}
			if (MIS.getUserName() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(MIS.getUserName());
			}
			if (row > 1000000) {
				part++;
				wb = misReportExcel.getWorkbook();
				misReport = misReportExcel.initializeSheet("misReport-" + part);
				row =0;
			}
			row++;

		}
		misReportExcel.close();
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
