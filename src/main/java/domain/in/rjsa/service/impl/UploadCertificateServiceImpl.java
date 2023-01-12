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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.dao.UploadCertificateDao;
import domain.in.rjsa.excel.UploadCertificateExcel;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.fy.UploadCertificate;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.UploadCertificateService;
@Transactional("transactionManager")
@Service("uploadCertificateService")
public class UploadCertificateServiceImpl extends AbstractServiceForm<Long, UploadCertificate, UploadCertificateDao> implements UploadCertificateService{
    @Autowired
    UploadCertificateDao dao;
	
    UploadCertificateExcel uploadCertificateExcel;
    public static String path;
    public String ExcelFile;

	@Override
	public UploadCertificateDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}


	@Override
	public UploadCertificate getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
	@Override
	public void saveDocument(MultipartFile downloadFile, HashMap<String, String> lessonMap) {
		try {
			UploadCertificate uploadCertificate = new UploadCertificate();

			uploadCertificate.setZipFile(downloadFile.getBytes());
			uploadCertificate.setFileName(downloadFile.getOriginalFilename());
			uploadCertificate.setTAN(lessonMap.get("TAN"));
			uploadCertificate.setFy(lessonMap.get("fy"));
			uploadCertificate.setQuarter(lessonMap.get("quarter"));
			uploadCertificate.setForm(lessonMap.get("form"));
			
			Date date= new Date();
			uploadCertificate.setUploadedTime(date);
			
			dao.persist(uploadCertificate);

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("File Not uploaded");
		}
		
	}
	
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<UploadCertificate> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		uploadCertificateExcel = new UploadCertificateExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		uploadCertificateExcel.initialise(path + "static/report/TDS-" + timestamp + "-UploadCertificate.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-UploadCertificate.xlsx";

		int row = 1;
		int part = 1;
		
		Workbook wb = uploadCertificateExcel.getWorkbook();
		Sheet uploadCertificatesheet = wb.getSheet("uploadCertificate-" + part);
		for (UploadCertificate uploadCertificate: listUsers) {
			
			Row details = uploadCertificatesheet.createRow(row);
			details.createCell(0).setCellValue(row);

			if (uploadCertificate.getFileName() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(uploadCertificate.getFileName());
			}
			if (uploadCertificate.getTAN() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(uploadCertificate.getTAN());
			}
			if (uploadCertificate.getFy() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(uploadCertificate.getFy());
			}
			if (uploadCertificate.getQuarter() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(uploadCertificate.getQuarter());
			}
			if (uploadCertificate.getForm() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(uploadCertificate.getForm());
			}
			if (uploadCertificate.getUploadedTime() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(uploadCertificate.getUploadedTime()));
			}
			
			if (row > 1000000) {
				part++;
				wb = uploadCertificateExcel.getWorkbook();
				uploadCertificate = (UploadCertificate) uploadCertificateExcel.initializeSheet("UploadCertificate-" + part);
				row =0;
			}
			row++;

		}
		uploadCertificateExcel.close();
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
