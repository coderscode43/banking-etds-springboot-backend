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

import domain.in.rjsa.dao.LogsDao;
import domain.in.rjsa.excel.LogsExcel;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.LogsService;

@Transactional("transactionManager")
@Service("logsService")
public class LogsServiceImpl extends AbstractServiceForm<Long, Logs, LogsDao> implements LogsService{
@Autowired
LogsDao dao;

LogsExcel logsExcel;
public static String path;
public String ExcelFile;
	@Override
	public LogsDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Logs getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
	@Override
	public List<String> ajax(String name, String term) {
		// TODO Auto-generated method stub
		return dao.ajax(name, term);
	}
	@Override
	public Long findSearchCount(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findSearchCount(map);
	}
	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<Logs> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		logsExcel = new LogsExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		logsExcel.initialise(path + "static/report/TDS-" + timestamp + "-Logs.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-Logs.xlsx";

		int row = 1;
		int part =1;

		for (Logs log : listUsers) {
			Workbook wb = logsExcel.getWorkbook();
			Sheet logs = wb.getSheet("logs");
			Row details = logs.createRow(row);
			details.createCell(0).setCellValue(row);

			if (log.getUsername() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(log.getUsername());
			}
			if (log.getIpaddrs() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(log.getIpaddrs());
			}
			if (log.getEntity() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(log.getEntity());
			}
			if (log.getDate() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(log.getDate()));
			}
			if (log.getDetails() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(log.getDetails());
			}
			if (log.getAction() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(log.getAction());
			}
			
			if (row > 1000000) {
				part++;
				wb = logsExcel.getWorkbook();
				logs = logsExcel.initializeSheet("Logs-" + part);
				row =0;
			}
			row++;

		}
		logsExcel.close();
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
