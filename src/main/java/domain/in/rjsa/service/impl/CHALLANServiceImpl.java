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

import domain.in.rjsa.dao.CHALLANDao;
import domain.in.rjsa.dao.PanUpdateListDao;
import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.excel.CHALLANExcel;
import domain.in.rjsa.model.tds.CHALLAN;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.CHALLANService;

@Transactional("transactionManager")
@Service("CHALLANService")
public class CHALLANServiceImpl extends AbstractServiceTaxo<String, CHALLAN, CHALLANDao> implements CHALLANService {
	@Autowired
	CHALLANDao dao;
	
	@Autowired
	private StaticDataDao sDao;
	
	@Autowired
	PanUpdateListDao pulDao;

	CHALLANExcel challanExcel;
	public static String path;
	public String ExcelFile;

	@Override
	public CHALLANDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}

	@Override
	public CHALLAN getByKey(String CIN) {
		// TODO Auto-generated method stub
		return dao.getByKey(CIN);
	}

	@Override
	public Long findallCount(HashMap<String, Object> constrains) {
		// TODO Auto-generated method stub
		return getPrimaryDao().findallCount(constrains);
	}

	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<CHALLAN> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		challanExcel = new CHALLANExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		challanExcel.initialise(path + "static/report/TDS-" + timestamp + "-Challan.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-Challan.xlsx";

		int row = 1;
		int part = 1;
		
		Workbook wb = challanExcel.getWorkbook();
		Sheet chln = wb.getSheet("challan-" +part);
		
		for (CHALLAN challan : listUsers) {
			
			Row details = chln.createRow(row);
			details.createCell(0).setCellValue(row);

			if (challan.getCIN() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(challan.getCIN());
			}
			if (challan.getTAN() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(challan.getTAN());
			}
			if (challan.getAMOUNT_OF_CLALLAN() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(challan.getAMOUNT_OF_CLALLAN());
			}
			if (challan.getDATE_OF_DEPOSITION() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4)
						.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(challan.getDATE_OF_DEPOSITION()));
			}
			if (challan.getAS_ON_DATE() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(challan.getAS_ON_DATE()));
			}
			if (challan.getCHALLAN_MISMATCH() == null) {
				details.createCell(6).setCellValue("-");
			}else if(challan.getCHALLAN_MISMATCH().toString().equalsIgnoreCase("0")) {
				details.createCell(6).setCellValue("False");
				}
			else {
				details.createCell(6).setCellValue("True");
				}

			if (row > 1000000) {
				part++;
				wb = challanExcel.getWorkbook();
				chln = challanExcel.initializeSheet("challan-" + part);
				row =0;
			}
			row++;

		}
		challanExcel.close();
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
