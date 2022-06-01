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

import domain.in.rjsa.dao.RODetailsDao;
import domain.in.rjsa.excel.RODetailsExcel;
import domain.in.rjsa.model.form.RODetails;
import domain.in.rjsa.model.form.UserDetails;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.RODetailsService;

@Transactional("transactionManager")
@Service("RODetailsService")
public class RODetailsServiceImpl extends AbstractServiceForm<Long, RODetails, RODetailsDao>
implements RODetailsService{
	@Autowired
	RODetailsDao dao;
	
	RODetailsExcel roDetailsExcel;
	public static String path;
	public String ExcelFile;
	@Override
	public RODetails getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public RODetailsDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<RODetails> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		roDetailsExcel = new RODetailsExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		roDetailsExcel.initialise(path + "static/report/TDS-" + timestamp + "-RODetails.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-RODetails.xlsx";

		int row = 1;
		int part = 1;
		Workbook wb = roDetailsExcel.getWorkbook();
		Sheet roDetailsExcelSheet = wb.getSheet("RODetails-"+part);
		
		for (RODetails roDetails : listUsers) {
			Row details = roDetailsExcelSheet.createRow(row);
			details.createCell(0).setCellValue(row);

			if (roDetails.getRoCode() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(roDetails.getRoCode());
			}
			if (roDetails.getRoName() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(roDetails.getRoName());
			}
			if (roDetails.getRoAddress() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(roDetails.getRoAddress());
			}
			if (roDetails.getRoEmail() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(roDetails.getRoEmail());
			}
			if (roDetails.getRoState() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(roDetails.getRoState());
			}
			if (roDetails.getRoPincode() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(roDetails.getRoPincode());
			}
			
			
			if (row > 1000000) {
				part++;
				wb = roDetailsExcel.getWorkbook();
				roDetailsExcelSheet = roDetailsExcel.initializeSheet("RODetails-" + part);
				row =0;
			}
			
			row++;

		}
		roDetailsExcel.close();
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
