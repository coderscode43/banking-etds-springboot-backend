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

import domain.in.rjsa.dao.TotalAmountDao;
import domain.in.rjsa.excel.TotalAmountExcel;
import domain.in.rjsa.model.fy.TotalAmount;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.TotalAmountSerivce;

@Transactional("transactionManager")
@Service("totalAmountService")
public class TotalAmountServiceImpl extends AbstractServiceFY<Long, TotalAmount, TotalAmountDao> implements TotalAmountSerivce{

	@Autowired
	TotalAmountDao dao;
	
	public static String path;
	public String ExcelFile;
	TotalAmountExcel totalAmountExcel;
	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		List<TotalAmount> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		totalAmountExcel = new TotalAmountExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		totalAmountExcel.initialise(path + "static/report/TDS-" + timestamp + "-TotalAmount.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-TotalAmount.xlsx";

		int row = 1;
		int part = 1;

		Workbook wb = totalAmountExcel.getWorkbook();
		Sheet TotalAmount = wb.getSheet("TotalAmount-" + part);
		for (TotalAmount totalAmount : listUsers) {
			
			Row details = TotalAmount.createRow(row);
			details.createCell(0).setCellValue(row);

			if (totalAmount.getCustVendId() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(totalAmount.getCustVendId());
			}
			if (totalAmount.getPan() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(totalAmount.getPan());
			}
			if (totalAmount.getSectionCode() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(totalAmount.getSectionCode());
			}
			if (totalAmount.getChallanHeading() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(totalAmount.getChallanHeading());
			}
			if (totalAmount.getMonth() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(totalAmount.getMonth());
			}
			if (totalAmount.getFy() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(totalAmount.getFy());
			}
			if (totalAmount.getTotalAmountPaidRaw() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(totalAmount.getTotalAmountPaidRaw());
			}
			if (totalAmount.getTotalAmountPaidUpload() == null) {
				details.createCell(8).setCellValue(" ");
			} else {
				details.createCell(8).setCellValue(totalAmount.getTotalAmountPaidUpload());
			}
			if (totalAmount.getTotaltaxRaw() == null) {
				details.createCell(9).setCellValue(" ");
			} else {
				details.createCell(9).setCellValue(totalAmount.getTotaltaxRaw());
			}
			if (totalAmount.getTotalTaxUploaded() == null) {
				details.createCell(10).setCellValue(" ");
			} else {
				details.createCell(10).setCellValue(totalAmount.getTotalTaxUploaded());
			}
			if (totalAmount.getRemark() == null) {
				details.createCell(11).setCellValue(" ");
			} else {
				details.createCell(11).setCellValue(totalAmount.getRemark());
			}
			if (totalAmount.getSource() == null) {
				details.createCell(12).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(totalAmount.getSource());
			}
			
			if (row > 1000000) {
				part++;
				wb = totalAmountExcel.getWorkbook();
				TotalAmount = totalAmountExcel.initializeSheet("TotalAmount-" + part);
				row =0;
			}
			row++;

		}
		totalAmountExcel.close();
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
	public TotalAmountDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}

}
