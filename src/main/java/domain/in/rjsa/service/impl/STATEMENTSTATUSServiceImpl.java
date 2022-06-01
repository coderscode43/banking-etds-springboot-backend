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

import domain.in.rjsa.dao.STATEMENTSTATUSDao;
import domain.in.rjsa.excel.StatementStatusExcel;
import domain.in.rjsa.model.tds.STATEMENTSTATUS;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.STATEMENTSTATUSService;

@Transactional("transactionManager")
@Service("STATEMENTSTATUSService")
public class STATEMENTSTATUSServiceImpl extends AbstractServiceTaxo<Long, STATEMENTSTATUS, STATEMENTSTATUSDao> implements STATEMENTSTATUSService{
	@Autowired 
	STATEMENTSTATUSDao dao;
	
	StatementStatusExcel statementStatusExcel;
	public static String path;
	public String ExcelFile;
	
	@Override
	public STATEMENTSTATUSDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
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
	@Override
	public STATEMENTSTATUS getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public List<String> ajax(String name, String term) {
		// TODO Auto-generated method stub
		return dao.ajax(name, term);
	}
	
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<STATEMENTSTATUS> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		statementStatusExcel = new StatementStatusExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		statementStatusExcel.initialise(path + "static/report/TDS-" + timestamp + "-StatementStatusExcel.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-StatementStatusExcel.xlsx";

		int row = 1;
		int part =1;
		
		Workbook wb = statementStatusExcel.getWorkbook();
		Sheet statementStatus = wb.getSheet("statementStatus-"+ part);
		for (STATEMENTSTATUS stmtStatus : listUsers) {
			
			Row details = statementStatus.createRow(row);
			details.createCell(0).setCellValue(row);

			if (stmtStatus.getTAN() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(stmtStatus.getTAN());
			}
			if (stmtStatus.getFORM() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(stmtStatus.getFORM());
			}
			if (stmtStatus.getQUARTER() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(stmtStatus.getQUARTER());
			}
			if (stmtStatus.getAS_ON_DATE() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(stmtStatus.getAS_ON_DATE());
			}
			if (stmtStatus.getFY() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(stmtStatus.getFY());
			}
			if (stmtStatus.getSTATUS() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(stmtStatus.getSTATUS());
			}
			if (stmtStatus.getRT() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(stmtStatus.getRT());
			}
			
			if (row > 1000000) {
				part++;
				wb = statementStatusExcel.getWorkbook();
				statementStatus = statementStatusExcel.initializeSheet("statementStatus-" + part);
				row =0;
			}
			row++;

		}
		statementStatusExcel.close();
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
