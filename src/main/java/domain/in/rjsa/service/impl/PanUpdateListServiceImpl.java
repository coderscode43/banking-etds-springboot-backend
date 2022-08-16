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

import domain.in.rjsa.dao.PanUpdateListDao;
import domain.in.rjsa.dao.Regular24QDeducteeDao;
import domain.in.rjsa.excel.PanUpdateListExcel;
import domain.in.rjsa.excel.TotalAmountExcel;
import domain.in.rjsa.model.fy.PanUpdateList;
import domain.in.rjsa.model.fy.Regular24QDeductee;
import domain.in.rjsa.model.fy.PanUpdateList;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.PanUpdateListService;
import domain.in.rjsa.service.Regular24QDeducteeService;


@Transactional("transactionManager")
@Service("panUpdateListService")
public class PanUpdateListServiceImpl extends AbstractServiceFY<Long, PanUpdateList, PanUpdateListDao>
implements PanUpdateListService{
	
	@Autowired
	PanUpdateListDao dao;


	@Override
	public PanUpdateListDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}
	
	public static String path;
	public String ExcelFile;
	PanUpdateListExcel panUpdateListExcel;
	
	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		List<PanUpdateList> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		panUpdateListExcel = new PanUpdateListExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		panUpdateListExcel.initialise(path + "static/report/TDS-" + timestamp + "-PanUpdateList.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-PanUpdateList.xlsx";

		int row = 1;
		int part = 1;

		Workbook wb = panUpdateListExcel.getWorkbook();
		Sheet PanUpdateList = wb.getSheet("PanUpdateList-" + part);
		for (PanUpdateList panUpdateList : listUsers) {
			
			Row details = PanUpdateList.createRow(row);
			details.createCell(0).setCellValue(row);

			if (panUpdateList.getFy() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(panUpdateList.getFy());
			}
			if (panUpdateList.getMonth() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(panUpdateList.getMonth());
			}
			if (panUpdateList.getChallanHeading() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(panUpdateList.getChallanHeading());
			}
			if (panUpdateList.getCustomerVendorID() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(panUpdateList.getCustomerVendorID());
			}
			if (panUpdateList.getPreviousPAN() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(panUpdateList.getPreviousPAN());
			}
			if (panUpdateList.getNewPAN() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(panUpdateList.getNewPAN());
			}
			if (panUpdateList.getRemark() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(panUpdateList.getRemark());
			}
			
			if (row > 1000000) {
				part++;
				wb = panUpdateListExcel.getWorkbook();
				PanUpdateList = panUpdateListExcel.initializeSheet("PanUpdateList-" + part);
				row =0;
			}
			row++;

		}
		panUpdateListExcel.close();
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
