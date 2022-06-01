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

import domain.in.rjsa.dao.DEDUCTORDETAILSDao;
import domain.in.rjsa.excel.DeductorDetailsExcel;
import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.DEDUCTORDETAILSService;

@Transactional("transactionManager")
@Service("DEDUCTORDETAILSService")
public class DEDUCTORDETAILSServiceImpl extends AbstractServiceTaxo<Long, DEDUCTORDETAILS, DEDUCTORDETAILSDao> implements DEDUCTORDETAILSService{
@Autowired
DEDUCTORDETAILSDao dao;

DeductorDetailsExcel deductorDetailsExcel;
public static String path;
public String ExcelFile;
	@Override
	public DEDUCTORDETAILSDao getPrimaryDao() {
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
	public DEDUCTORDETAILS getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
	@Override
	public List<String> ajax(String name, String term) {
		// TODO Auto-generated method stub
		return dao.ajax(name, term);
	}
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<DEDUCTORDETAILS> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		deductorDetailsExcel = new DeductorDetailsExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		deductorDetailsExcel.initialise(path + "static/report/TDS-" + timestamp + "-DeductorDetailsExcel.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-DeductorDetailsExcel.xlsx";

		int row = 1;
		int part=1;

		Workbook wb = deductorDetailsExcel.getWorkbook();
		Sheet DeductorDetails = wb.getSheet("DeductorDetails-"+ part);
		for (DEDUCTORDETAILS deductorDet : listUsers) {
			
			Row details = DeductorDetails.createRow(row);
			details.createCell(0).setCellValue(row);

			if (deductorDet.getTAN() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(deductorDet.getTAN());
			}
			if (deductorDet.getSTATE() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(deductorDet.getSTATE());
			}
			if (deductorDet.getCITY() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(deductorDet.getCITY());
			}
			
			if (row > 1000000) {
				part++;
				wb = deductorDetailsExcel.getWorkbook();
				DeductorDetails = deductorDetailsExcel.initializeSheet("DeductorDetails-" + part);
				row =0;
			}
			row++;
		}
		deductorDetailsExcel.close();
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
