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

import domain.in.rjsa.dao.LDCDao;
import domain.in.rjsa.excel.LDCExcel;
import domain.in.rjsa.model.tds.LDC;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.LDCService;

@Transactional()
@Service("LDCService")
public class LDCServiceImpl extends AbstractServiceTaxo<String, LDC, LDCDao> implements LDCService{
	@Autowired
	LDCDao dao;
	
	LDCExcel LDCExcel;
	public static String path;
	public String ExcelFile;
		@Override
		public LDCDao getPrimaryDao() {
			// TODO Auto-generated method stub
			return dao;
		}
		@Override
		public LDC getByKey(String tan) {
			// TODO Auto-generated method stub
			return dao.getByKey(tan);
		}
		public String createUserExcel(LinkedHashMap<String, Object> map) {
			List<LDC> listUsers = searchExcel(map);

			if (path == null || path.isEmpty()) {
				setPath();
			}

			LDCExcel = new LDCExcel();

			File file = new File(path + "static/report/");
			if (!file.exists()) {
				file.mkdirs();
			}

			String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
			LDCExcel.initialise(path + "static/report/TDS-" + timestamp + "-ldc.xlsx");
			this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-ldc.xlsx";

			int row = 1;

			for (LDC ldc : listUsers) {
				Workbook wb = LDCExcel.getWorkbook();
				Sheet Ldc = wb.getSheet("Ldc");
				Row details = Ldc.createRow(row);
				details.createCell(0).setCellValue(row);
				/*(new SimpleDateFormat("dd-MM-yyyy").format(ldc.getRoCode()))*/
				if (ldc.getLDC_NUMBER() == null) {
					details.createCell(1).setCellValue(" ");
				} else {
					details.createCell(1).setCellValue(ldc.getLDC_NUMBER());
				}
				if (ldc.getNAME() == null) {
					details.createCell(2).setCellValue(" ");
				} else {
					details.createCell(2).setCellValue(ldc.getNAME());
				}
				if (ldc.getTAN() == null) {
					details.createCell(3).setCellValue(" ");
				} else {
					details.createCell(3).setCellValue(ldc.getTAN());
				}
				if (ldc.getPAN() == null) {
					details.createCell(4).setCellValue(" ");
				} else {
					details.createCell(4).setCellValue(ldc.getPAN());
				}
				if (ldc.getFY() == null) {
					details.createCell(5).setCellValue(" ");
				} else {
					details.createCell(5).setCellValue(ldc.getFY());
				}
				if (ldc.getVALID_FROM() == null) {
					details.createCell(6).setCellValue(" ");
				} else {
					details.createCell(6).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(ldc.getVALID_FROM()));
				}
				if (ldc.getVALID_TO() == null) {
					details.createCell(7).setCellValue(" ");
				} else {
					details.createCell(7).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(ldc.getVALID_TO()));
				}
				if (ldc.getSECTION_CODE() == null) {
					details.createCell(8).setCellValue(" ");
				} else {
					details.createCell(8).setCellValue(ldc.getSECTION_CODE());
				}
				if (ldc.getNATURE_OF_PAYMENT() == null) {
					details.createCell(9).setCellValue(" ");
				} else {
					details.createCell(9).setCellValue(ldc.getNATURE_OF_PAYMENT());
				}
				if (ldc.getLDC_RATE() == null) {
					details.createCell(10).setCellValue(" ");
				} else {
					details.createCell(10).setCellValue(ldc.getLDC_RATE());
				}
				if (ldc.getCERTIFICATE_LIMIT() == null) {
					details.createCell(11).setCellValue(" ");
				} else {
					details.createCell(11).setCellValue(ldc.getCERTIFICATE_LIMIT());
				}
				if (ldc.getAMOUNT_CONSUMED() == null) {
					details.createCell(12).setCellValue(" ");
				} else {
					details.createCell(12).setCellValue(ldc.getAMOUNT_CONSUMED());
				}
				if (ldc.getISSUE_DATE() == null) {
					details.createCell(13).setCellValue(" ");
				} else {
					details.createCell(13).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(ldc.getISSUE_DATE()));
				}
				if (ldc.getCANCEL_DATE() == null) {
					details.createCell(14).setCellValue(" ");
				} else {
					details.createCell(14).setCellValue(ldc.getCANCEL_DATE());
				}
				if (ldc.getAS_ON_DATE() == null) {
					details.createCell(15).setCellValue(" ");
				} else {
					details.createCell(15).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(ldc.getAS_ON_DATE()));
				}
				
				row++;

			}
			LDCExcel.close();
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
