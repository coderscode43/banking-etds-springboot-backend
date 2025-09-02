package domain.in.rjsa.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.dao.TicketDao;
import domain.in.rjsa.excel.TicketExcel;
import domain.in.rjsa.model.form.StaticDataModel;
import domain.in.rjsa.model.fy.Tickets;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.TicketService;
import jakarta.servlet.http.HttpServletResponse;

@Transactional("transactionManager")
@Service("ticketService")
public class TicketServiceImpl extends AbstractServiceForm<Long, Tickets, TicketDao> implements TicketService {
	@Autowired
	TicketDao dao;

	@Autowired
	StaticDataDao sDao;

	TicketExcel ticketExcel;
	public static String path;
	public String ExcelFile;

	@Override
	public TicketDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Tickets getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}

	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<Tickets> listUsers = searchExcel(map);

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
		for (Tickets tiket : listUsers) {

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
				row = 0;
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

	@Override
	public void addTicketWithFile(MultipartFile file, LinkedHashMap<String, Object> entity) {
		try {
			Tickets tickets = new Tickets();
			String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
			tickets.setBranchCode(Long.valueOf(entity.get("branchCode").toString()));
			tickets.setCustVendId((entity.get("custVendId").equals("undefined"))?"":entity.get("custVendId").toString());
			tickets.setDateOfChange((Date) entity.get("dateOfChange"));
			tickets.setDateOfOpening((Date) entity.get("dateOfOpening"));
			tickets.setDescription(entity.get("description").toString());
			tickets.setForm(entity.get("form").toString());
			tickets.setFy(entity.get("fy").toString());
			tickets.setPan((entity.get("pan").equals("undefined"))?"":entity.get("pan").toString());
			tickets.setQuarter(entity.get("quarter").toString());
			tickets.setResolved(Boolean.parseBoolean(entity.get("resolved").toString()));
			tickets.setStatus(entity.get("status").toString());
			tickets.setUserName(entity.get("userName").toString());
			tickets.setAttachment(file.getOriginalFilename());
			dao.persist(tickets);
			String fy = entity.get("fy").toString();
			String quarter = entity.get("quarter").toString();
			String form = entity.get("form").toString();
			String branchCode = entity.get("branchCode").toString();
			HashMap<String, Object> sd = new HashMap<String, Object>();
			List<StaticDataModel> list = sDao.findall(sd, 0, 100);
			for (StaticDataModel l : list) {
				if (l.getKey().equalsIgnoreCase("documentSave")) {
					String path = l.getValue();
					String filepath = path + "//" + date + "//" + fy + "//" + quarter + "//" + branchCode + "//Ticket//"
							+ form + "//" + file.getOriginalFilename();
					File files = new File(filepath.toString());
					if (!files.exists()) {
						files.mkdirs();
					}
					try {
						file.transferTo(files);
					} catch (IllegalStateException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void downloadDocument(Long id, HttpServletResponse response) {
		try {
			Tickets t = dao.getByKey(id);
			HashMap<String, Object> sd = new HashMap<String, Object>();
			List<StaticDataModel> list = sDao.findall(sd, 0, 100);
			for (StaticDataModel l : list) {
				if (l.getKey().equalsIgnoreCase("documentSave")) {

					String date = t.getDateOfOpening().toString();

					String path = l.getValue();
					String date1 = LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
					String filePath = path + "//" + date1 + "//" + t.fy + "//" + t.quarter + "//" + t.branchCode
							+ "//Ticket//" + t.form + "//" + t.getAttachment();
					File file = new File(filePath);
					byte[] fileContent;
					fileContent = Files.readAllBytes(file.toPath());
					String mimeType = URLConnection.guessContentTypeFromName(t.getAttachment());
					response.setContentType(mimeType);
					response.setStatus(HttpServletResponse.SC_OK);
					response.setHeader("Content-Disposition", " attachment; filename=" + t.getAttachment());
					response.getOutputStream().write(fileContent);
					response.getOutputStream().close();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
