package domain.in.rjsa.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.TicketDao;
import domain.in.rjsa.excel.TicketExcel;
import domain.in.rjsa.model.fy.Ticket;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.TicketService;

@Transactional("transactionManager")
@Service("ticketService")
public class TicketServiceImpl extends AbstractServiceForm<Long, Ticket, TicketDao> implements TicketService{
@Autowired
TicketDao dao;


TicketExcel ticketExcel;
public static String path;
public String ExcelFile;
	@Override
	public TicketDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Ticket getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
	
	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<Ticket> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		ticketExcel = new TicketExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		ticketExcel.initialise(path + "static/report/TDS-" + timestamp + "-Ticket.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-Ticket.xlsx";

		int row = 1;
		int part = 1;
		
		Workbook wb = ticketExcel.getWorkbook();
		Sheet ticket = wb.getSheet("ticket-" + part);
		for (Ticket tiket: listUsers) {
			
			Row details = ticket.createRow(row);
			details.createCell(0).setCellValue(row);

			if (tiket.getFy() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(tiket.getFy());
			}
			if (tiket.getQuarter() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(tiket.getQuarter());
			}
			if (tiket.getBranchCode() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(tiket.getBranchCode());
			}
			if (tiket.getForm() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(tiket.getForm());
			}
			if (tiket.getCustVendId() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(tiket.getCustVendId());
			}
			if (tiket.getPan() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(tiket.getPan());
			}
			if (tiket.getDateOfOpening() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(tiket.getDateOfOpening()));
			}
			if (tiket.getStatus() == null) {
				details.createCell(8).setCellValue(" ");
			} else {
				details.createCell(8).setCellValue(tiket.getStatus());
			}
			if (tiket.getDescription() == null) {
				details.createCell(9).setCellValue(" ");
			} else {
				details.createCell(9).setCellValue(tiket.getDescription());
			}
			if (tiket.getUserName() == null) {
				details.createCell(10).setCellValue(" ");
			} else {
				details.createCell(10).setCellValue(tiket.getUserName());
			}
			
			if (row > 1000000) {
				part++;
				wb = ticketExcel.getWorkbook();
				ticket = ticketExcel.initializeSheet("ticket-" + part);
				row =0;
			}
			row++;

		}
		ticketExcel.close();
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
	public Map<String, Long> getStatusDetails(Long branchCode, boolean isAdmin) {
		// TODO Auto-generated method stub
		return dao.getStatusCounts(branchCode, isAdmin);
	}
}
