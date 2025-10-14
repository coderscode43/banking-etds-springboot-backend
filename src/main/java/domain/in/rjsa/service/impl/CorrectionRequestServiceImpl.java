package domain.in.rjsa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import domain.in.rjsa.dao.AddChallanDao;
import domain.in.rjsa.dao.BranchDao;
import domain.in.rjsa.dao.CorrectionRemarksDao;
import domain.in.rjsa.dao.CorrectionRequestAmountDetailsDao;
import domain.in.rjsa.dao.CorrectionRequestDao;
import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.dao.UserDetailsDao;
import domain.in.rjsa.email.CorrectionRequestMail;
import domain.in.rjsa.excel.CorrectionRequestExcel;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.form.AddChallan;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.CorrectionRemarks;
import domain.in.rjsa.model.form.CorrectionRequest;
import domain.in.rjsa.model.form.CorrectionRequestAmountDetails;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.CorrectionRequestService;
import domain.in.rjsa.util.StaticData;
import jakarta.servlet.http.HttpServletResponse;

@Transactional("transactionManager")
@Service("correctionRequestService")
public class CorrectionRequestServiceImpl extends AbstractServiceForm<Long, CorrectionRequest, CorrectionRequestDao>
		implements CorrectionRequestService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CorrectionRequestDao dao;
	CorrectionRequestExcel correctionRequestExcel;

	@Autowired
	CorrectionRequestAmountDetailsDao cradDao;

	@Autowired
	CorrectionRemarksDao crDao;

	@Autowired
	AddChallanDao acDao;

	@Autowired
	UserDetailsDao uDao;

	@Autowired
	BranchDao bDao;

	public static String path;
	public String ExcelFile;

	@Override
	public CorrectionRequestDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Autowired
	StaticDataDao sDao;

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		List<Object> updatedList = new ArrayList<>();
		try {
		List<?> list = dao.search(map, pageNo, resultPerPage);
		for (Object l : list) {
			String jsonElement = mapper.writeValueAsString(l);
			HashMap<String, Object> cr = mapper.readValue(jsonElement, HashMap.class);
			if (cr.get("rejectStatus").toString() == "true") {
				cr.put("color", "orangered");
				String jsonEle = mapper.writeValueAsString(cr);
				Object obj = mapper.readValue(jsonEle, Object.class);
				updatedList.add(obj);
			} else if (cr.get("correctionStatus").toString() == "true") {
				cr.put("color", "lightgreen");
				String jsonEle = mapper.writeValueAsString(cr);
				Object obj = mapper.readValue(jsonEle, Object.class);
				updatedList.add(obj);
			} else {
				if (cr.get("checkerApprovedBy") == null && cr.get("taxTeamApprovedBy") == null
						&& cr.get("correctionBy") == null) {
					if (cr.get("status").toString().equalsIgnoreCase("Sent for Clarification")) {
						cr.put("color", "lightblue");
					} else {
						cr.put("color", "white");
					}
					String jsonEle = mapper.writeValueAsString(cr);
					Object obj = mapper.readValue(jsonEle, Object.class);
					updatedList.add(obj);
				} else if (cr.get("checkerApprovedBy") != null && cr.get("taxTeamApprovedBy") == null
						&& cr.get("correctionBy") == null) {
					cr.put("color", "yellow");
					String jsonEle = mapper.writeValueAsString(cr);
					Object obj = mapper.readValue(jsonEle, Object.class);
					updatedList.add(obj);
				} else if (cr.get("checkerApprovedBy") != null && cr.get("taxTeamApprovedBy") != null
						&& cr.get("correctionBy") == null) {
					cr.put("color", "yellow");
					String jsonEle = mapper.writeValueAsString(cr);
					Object obj = mapper.readValue(jsonEle, Object.class);
					updatedList.add(obj);
				} else if (cr.get("checkerApprovedBy") != null && cr.get("taxTeamApprovedBy") != null
						&& cr.get("correctionBy") != null) {
					cr.put("color", "orange");
					String jsonEle = mapper.writeValueAsString(cr);
					Object obj = mapper.readValue(jsonEle, Object.class);
					updatedList.add(obj);
				}
			}
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return updatedList;
	}

	@Override
	public Map<String, Long> getStatusDetails(Long branchCode, boolean isAdmin) {
		// TODO Auto-generated method stub
		return dao.getStatusCounts(branchCode, isAdmin);
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<CorrectionRequest> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		correctionRequestExcel = new CorrectionRequestExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		correctionRequestExcel.initialise(path + "static/report/TDS-" + timestamp + "-CorrectionRequest.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-CorrectionRequest.xlsx";

		int row = 1;
		int part = 1;

		Workbook wb = correctionRequestExcel.getWorkbook();
		Sheet correctionRequest = wb.getSheet("correctionRequest-" + part);
		for (CorrectionRequest CorrectionRequest1 : listUsers) {
			map.remove("branchCode");
			map.remove("fy");
			map.remove("status");
			map.remove("ticketnumber");
			map.remove("typeOfCorrection");
			map.remove("correctionOn");
			map.remove("fromDate");
			map.remove("toDate");
			map.put("correctionRequestId", CorrectionRequest1.getId());
			List<CorrectionRequestAmountDetails> correctionRequestAmountDetails = cradDao.searchExcel(map);
			LinkedHashMap<String, Object> addChallanMap = new LinkedHashMap<String, Object>();
			addChallanMap.put("correctionRequestId", map.get("correctionRequestId"));
			AddChallan ac = acDao.uniqueSearch(addChallanMap);
			if (!correctionRequestAmountDetails.isEmpty()) {
				for (CorrectionRequestAmountDetails crad : correctionRequestAmountDetails) {
					Row details = correctionRequest.createRow(row);
					details.createCell(0).setCellValue(row);
					if (CorrectionRequest1.getTicketNumber() == null) {
						details.createCell(1).setCellValue(" ");
					} else {
						details.createCell(1).setCellValue(Long.valueOf(CorrectionRequest1.getTicketNumber()));
					}
					if (CorrectionRequest1.getFy() == null) {
						details.createCell(2).setCellValue(" ");
					} else {
						details.createCell(2).setCellValue(CorrectionRequest1.getFy());
					}
					if (CorrectionRequest1.getQuarter() == null) {
						details.createCell(3).setCellValue(" ");
					} else {
						details.createCell(3).setCellValue(CorrectionRequest1.getQuarter());
					}
					if (CorrectionRequest1.getBranchCode() == null) {
						details.createCell(4).setCellValue(" ");
					} else {
						details.createCell(4).setCellValue(CorrectionRequest1.getBranchCode());
					}
					if (CorrectionRequest1.getTypeOfForm() == null) {
						details.createCell(5).setCellValue(" ");
					} else {
						details.createCell(5).setCellValue(CorrectionRequest1.getTypeOfForm());
					}
					if (CorrectionRequest1.getTypeOfCorrection() == null) {
						details.createCell(6).setCellValue(" ");
					} else {
						details.createCell(6).setCellValue(CorrectionRequest1.getTypeOfCorrection());
					}
					if (CorrectionRequest1.getName() == null) {
						details.createCell(7).setCellValue(" ");
					} else {
						details.createCell(7).setCellValue(CorrectionRequest1.getName());
					}
					if (CorrectionRequest1.getPan() == null) {
						details.createCell(8).setCellValue(" ");
					} else {
						details.createCell(8).setCellValue(CorrectionRequest1.getPan());
					}
					if (CorrectionRequest1.getEmpNo() == null) {
						details.createCell(9).setCellValue(" ");
					} else {
						details.createCell(9).setCellValue(CorrectionRequest1.getEmpNo());
					}
					if (CorrectionRequest1.getRemark() == null) {
						details.createCell(10).setCellValue(" ");
					} else {
						details.createCell(10).setCellValue(CorrectionRequest1.getRemark());
					}
					if (CorrectionRequest1.getFileName() == null) {
						details.createCell(11).setCellValue(" ");
					} else {
						details.createCell(11).setCellValue(CorrectionRequest1.getFileName());
					}

					if (crad.getDateOfPayment() == null) {
						details.createCell(12).setCellValue(" ");
					} else {
						details.createCell(12)
								.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(crad.getDateOfPayment()));
					}
					if (crad.getPan() == null) {
						details.createCell(13).setCellValue(" ");
					} else {
						details.createCell(13).setCellValue(crad.getPan());
					}
					if (crad.getCorrectPan() == null) {
						details.createCell(14).setCellValue(" ");
					} else {
						details.createCell(14).setCellValue(crad.getCorrectPan());
					}
					if (crad.getAmountPaid() == null) {
						details.createCell(15).setCellValue(" ");
					} else {
						details.createCell(15).setCellValue(crad.getAmountPaid());
					}
					if (crad.getCorrectAmountPaid() == null) {
						details.createCell(16).setCellValue(" ");
					} else {
						details.createCell(16).setCellValue(crad.getCorrectAmountPaid());
					}
					if (crad.getTds() == null) {
						details.createCell(17).setCellValue(" ");
					} else {
						details.createCell(17).setCellValue(crad.getTds());
					}
					if (crad.getCorrectTds() == null) {
						details.createCell(18).setCellValue(" ");
					} else {
						details.createCell(18).setCellValue(crad.getCorrectTds());
					}
					if (crad.getSectionCode() == null) {
						details.createCell(19).setCellValue(" ");
					} else {
						details.createCell(19).setCellValue(crad.getSectionCode());
					}
					if (crad.getCorrectSection() == null) {
						details.createCell(20).setCellValue(" ");
					} else {
						details.createCell(20).setCellValue(crad.getCorrectSection());
					}
					if (crad.getCorrectRemark() == null) {
						details.createCell(21).setCellValue(" ");
					} else {
						details.createCell(21).setCellValue(crad.getCorrectRemark());
					}

					if (ac != null) {
						if (ac.getChallanSrNo() == null) {
							details.createCell(22).setCellValue(" ");
						} else {
							details.createCell(22).setCellValue(ac.getChallanSrNo());
						}
						if (ac.getChallanBsrCode() == null) {
							details.createCell(23).setCellValue(" ");
						} else {
							details.createCell(23).setCellValue(ac.getChallanBsrCode());
						}
						if (ac.getChallanSection() == null) {
							details.createCell(24).setCellValue(" ");
						} else {
							details.createCell(24).setCellValue(ac.getChallanSection());
						}
						if (ac.getChallanAmount() == null) {
							details.createCell(25).setCellValue(" ");
						} else {
							details.createCell(25).setCellValue(ac.getChallanAmount());
						}
						if (ac.getChallanDate() == null) {
							details.createCell(26).setCellValue(" ");
						} else {
							details.createCell(26)
									.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(ac.getChallanDate()));
						}
						if (ac.getChallanSupportingDocument() == null) {
							details.createCell(27).setCellValue(" ");
						} else {
							details.createCell(27).setCellValue(ac.getChallanSupportingDocument());
						}
					}

					if (CorrectionRequest1.getStatus() == null) {
						details.createCell(28).setCellValue(" ");
					} else {
						details.createCell(28).setCellValue(CorrectionRequest1.getStatus());
					}
					if (CorrectionRequest1.getMakerBy() == null) {
						details.createCell(29).setCellValue(" ");
					} else {
						details.createCell(29).setCellValue(CorrectionRequest1.getMakerBy());
					}
					if (CorrectionRequest1.getCorrectionRequestDate() == null) {
						details.createCell(30).setCellValue(" ");
					} else {
						details.createCell(30).setCellValue(new SimpleDateFormat("dd-MM-yyyy")
								.format(CorrectionRequest1.getCorrectionRequestDate()));
					}
					if (CorrectionRequest1.getCheckerApprovedBy() == null) {
						details.createCell(31).setCellValue(" ");
					} else {
						details.createCell(31).setCellValue(CorrectionRequest1.getCheckerApprovedBy());
					}
					if (CorrectionRequest1.getCheckerApprovedOn() == null) {
						details.createCell(32).setCellValue(" ");
					} else {
						details.createCell(32).setCellValue(
								new SimpleDateFormat("dd-MM-yyyy").format(CorrectionRequest1.getCheckerApprovedOn()));
					}
					if (CorrectionRequest1.getCorrectionBy() == null) {
						details.createCell(33).setCellValue(" ");
					} else {
						details.createCell(33).setCellValue(CorrectionRequest1.getCorrectionBy());
					}
					if (CorrectionRequest1.getCorrectionOn() == null) {
						details.createCell(34).setCellValue(" ");
					} else {
						details.createCell(34).setCellValue(
								new SimpleDateFormat("dd-MM-yyyy").format(CorrectionRequest1.getCorrectionOn()));
					}

					if (row > 1000000) {
						part++;
						wb = correctionRequestExcel.getWorkbook();
						correctionRequest = correctionRequestExcel.initializeSheet("correctionRequest-" + part);
						row = 0;
					}
					row++;

				}
			} else {
				Row details = correctionRequest.createRow(row);
				details.createCell(0).setCellValue(row);
				if (CorrectionRequest1.getTicketNumber() == null) {
					details.createCell(1).setCellValue(" ");
				} else {
					details.createCell(1).setCellValue(Long.valueOf(CorrectionRequest1.getTicketNumber()));
				}
				if (CorrectionRequest1.getFy() == null) {
					details.createCell(2).setCellValue(" ");
				} else {
					details.createCell(2).setCellValue(CorrectionRequest1.getFy());
				}
				if (CorrectionRequest1.getQuarter() == null) {
					details.createCell(3).setCellValue(" ");
				} else {
					details.createCell(3).setCellValue(CorrectionRequest1.getQuarter());
				}
				if (CorrectionRequest1.getBranchCode() == null) {
					details.createCell(4).setCellValue(" ");
				} else {
					details.createCell(4).setCellValue(CorrectionRequest1.getBranchCode());
				}
				if (CorrectionRequest1.getTypeOfForm() == null) {
					details.createCell(5).setCellValue(" ");
				} else {
					details.createCell(5).setCellValue(CorrectionRequest1.getTypeOfForm());
				}
				if (CorrectionRequest1.getTypeOfCorrection() == null) {
					details.createCell(6).setCellValue(" ");
				} else {
					details.createCell(6).setCellValue(CorrectionRequest1.getTypeOfCorrection());
				}
				if (CorrectionRequest1.getName() == null) {
					details.createCell(7).setCellValue(" ");
				} else {
					details.createCell(7).setCellValue(CorrectionRequest1.getName());
				}
				if (CorrectionRequest1.getPan() == null) {
					details.createCell(8).setCellValue(" ");
				} else {
					details.createCell(8).setCellValue(CorrectionRequest1.getPan());
				}
				if (CorrectionRequest1.getEmpNo() == null) {
					details.createCell(9).setCellValue(" ");
				} else {
					details.createCell(9).setCellValue(CorrectionRequest1.getEmpNo());
				}
				if (CorrectionRequest1.getRemark() == null) {
					details.createCell(10).setCellValue(" ");
				} else {
					details.createCell(10).setCellValue(CorrectionRequest1.getRemark());
				}
				if (ac != null) {
					if (ac.getChallanSrNo() == null) {
						details.createCell(22).setCellValue(" ");
					} else {
						details.createCell(22).setCellValue(ac.getChallanSrNo());
					}
					if (ac.getChallanBsrCode() == null) {
						details.createCell(23).setCellValue(" ");
					} else {
						details.createCell(23).setCellValue(ac.getChallanBsrCode());
					}
					if (ac.getChallanSection() == null) {
						details.createCell(24).setCellValue(" ");
					} else {
						details.createCell(24).setCellValue(ac.getChallanSection());
					}
					if (ac.getChallanAmount() == null) {
						details.createCell(25).setCellValue(" ");
					} else {
						details.createCell(25).setCellValue(ac.getChallanAmount());
					}
					if (ac.getChallanDate() == null) {
						details.createCell(26).setCellValue(" ");
					} else {
						details.createCell(26)
								.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(ac.getChallanDate()));
					}
					if (ac.getChallanSupportingDocument() == null) {
						details.createCell(27).setCellValue(" ");
					} else {
						details.createCell(27).setCellValue(ac.getChallanSupportingDocument());
					}
				}
				if (CorrectionRequest1.getStatus() == null) {
					details.createCell(28).setCellValue(" ");
				} else {
					details.createCell(28).setCellValue(CorrectionRequest1.getStatus());
				}
				if (CorrectionRequest1.getMakerBy() == null) {
					details.createCell(29).setCellValue(" ");
				} else {
					details.createCell(29).setCellValue(CorrectionRequest1.getMakerBy());
				}
				if (CorrectionRequest1.getCorrectionRequestDate() == null) {
					details.createCell(30).setCellValue(" ");
				} else {
					details.createCell(30).setCellValue(
							new SimpleDateFormat("dd-MM-yyyy").format(CorrectionRequest1.getCorrectionRequestDate()));
				}
				if (CorrectionRequest1.getCheckerApprovedBy() == null) {
					details.createCell(31).setCellValue(" ");
				} else {
					details.createCell(31).setCellValue(CorrectionRequest1.getCheckerApprovedBy());
				}
				if (CorrectionRequest1.getCheckerApprovedOn() == null) {
					details.createCell(32).setCellValue(" ");
				} else {
					details.createCell(32).setCellValue(
							new SimpleDateFormat("dd-MM-yyyy").format(CorrectionRequest1.getCheckerApprovedOn()));
				}
				if (CorrectionRequest1.getCorrectionBy() == null) {
					details.createCell(33).setCellValue(" ");
				} else {
					details.createCell(33).setCellValue(CorrectionRequest1.getCorrectionBy());
				}
				if (CorrectionRequest1.getCorrectionOn() == null) {
					details.createCell(34).setCellValue(" ");
				} else {
					details.createCell(34).setCellValue(
							new SimpleDateFormat("dd-MM-yyyy").format(CorrectionRequest1.getCorrectionOn()));
				}
				if (row > 1000000) {
					part++;
					wb = correctionRequestExcel.getWorkbook();
					correctionRequest = correctionRequestExcel.initializeSheet("correctionRequest-" + part);
					row = 0;
				}
				row++;

			}

		}
		correctionRequestExcel.close();
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
	public void saveAmount(LinkedHashMap<String, Object> entity) {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> propertyNameValues = new HashMap<String, Object>();
			Long count = 0L;
			count = getPrimaryDao().getMaxValue("id", propertyNameValues);
			CorrectionRequest cr = dao.getByKey(count);
			CorrectionRequestAmountDetails cra = new CorrectionRequestAmountDetails();
			SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");
			cra.setDateOfPayment(Date.parse(entity.get("dateOfPayment").toString()));
			cra.setAmountPaid(Long.valueOf(entity.get("amountPaid").toString()));
			cra.setCorrectAmountPaid(Long.valueOf(entity.get("amountPaid26as").toString()));
			cra.setTds(Long.valueOf(entity.get("tds").toString()));
			cra.setCorrectTds(Long.valueOf(entity.get("tds26as").toString()));
			cra.setCorrectRemark(entity.get("amountRemark").toString());
			cra.setCorrectionRequestId(cr.getId());
			cradDao.persist(cra);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public Long autoGenerateTicketNumber() {
		try {
			// TODO Auto-generated method stub
			HashMap<String, Object> propertyNameValues = new HashMap<String, Object>();
			Long ticketNumber = 0L;
			Long count = 1L;
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			long currentDateInMillis = System.currentTimeMillis();
			System.out.println(currentDateInMillis);
			try {
				List<CorrectionRequest> cr = getPrimaryDao().search(propertyNameValues);

				if (!cr.isEmpty()) {
					count = getPrimaryDao().getMaxValue("ticketNumber", propertyNameValues);
					logger.info("ticketNumber - " + count.toString());
					String d1 = count.toString().substring(0, 8);
					logger.info(d1);
					if (formatter.format(date).equalsIgnoreCase(d1)) {
						ticketNumber = count + 1;
						logger.info(ticketNumber.toString());
					} else {
						String d = formatter.format(date) + String.format("%04d", 1).substring(0, 4);
						logger.info("Generated ticketNumber - " + d);
						ticketNumber = Long.parseLong(d);
					}
				} else {
					String d = formatter.format(date) + String.format("%04d", 1).substring(0, 4);
					logger.info("Generated ticketNumber - " + d);
					ticketNumber = Long.parseLong(d);
				}
			} catch (Exception e) {
				String d = formatter.format(date) + String.format("%04d", 1).substring(0, 4);
				logger.info("Generated ticketNumber - " + d);
				ticketNumber = Long.parseLong(d);
			}
			System.out.println(ticketNumber.SIZE);

			return ticketNumber;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new CustomException("Error in Auto Generate Ticket Number.");
		}
//		entity.setTicketNumber(count);
	}

	@Override
	public List<?> getList(HashMap<String, Object> constrains, int pageNo, int resultPerPage) {
		List<Object> updatedList = new ArrayList<>();
		try {
			List<?> list = dao.findall(constrains, pageNo, resultPerPage);
			for (Object l : list) {
				String jsonElement = mapper.writeValueAsString(l);
				HashMap<String, Object> cr = mapper.readValue(jsonElement, HashMap.class);
				if (cr.get("rejectStatus").toString() == "true") {
					cr.put("color", "orangered");
					String jsonEle = mapper.writeValueAsString(cr);
					Object obj = mapper.readValue(jsonEle, Object.class);
					updatedList.add(obj);
				} else if (cr.get("correctionStatus").toString() == "true") {
					cr.put("color", "lightgreen");
					String jsonEle = mapper.writeValueAsString(cr);
					Object obj = mapper.readValue(jsonEle, Object.class);
					updatedList.add(obj);
				} else {
					if (cr.get("checkerApprovedBy") == null && cr.get("taxTeamApprovedBy") == null
							&& cr.get("correctionBy") == null) {
						if (cr.get("status").toString().equalsIgnoreCase("Sent for Clarification")) {
							cr.put("color", "lightblue");
						} else {
							cr.put("color", "white");
						}
						String jsonEle = mapper.writeValueAsString(cr);
						Object obj = mapper.readValue(jsonEle, Object.class);
						updatedList.add(obj);
					} else if (cr.get("checkerApprovedBy") != null && cr.get("taxTeamApprovedBy") == null
							&& cr.get("correctionBy") == null) {
						cr.put("color", "yellow");
						String jsonEle = mapper.writeValueAsString(cr);
						Object obj = mapper.readValue(jsonEle, Object.class);
						updatedList.add(obj);
					} else if (cr.get("checkerApprovedBy") != null && cr.get("taxTeamApprovedBy") != null
							&& cr.get("correctionBy") == null) {
						cr.put("color", "yellow");
						String jsonEle = mapper.writeValueAsString(cr);
						Object obj = mapper.readValue(jsonEle, Object.class);
						updatedList.add(obj);
					} else if (cr.get("checkerApprovedBy") != null && cr.get("taxTeamApprovedBy") != null
							&& cr.get("correctionBy") != null) {
						cr.put("color", "orange");
						String jsonEle = mapper.writeValueAsString(cr);
						Object obj = mapper.readValue(jsonEle, Object.class);
						updatedList.add(obj);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updatedList;
	}

	@Override
	public Object getDetail(HashMap<String, Object> constrains, Long id, String principal, Long branchCode) {
		constrains.put("id", id);
		HashMap<String, Object> map = new HashMap<>();
		CorrectionRequest cr = dao.uniqueSearch(constrains);
		logger.info(cr.toString());
		map.put("details", cr);
		constrains.remove("id");
		constrains.remove("branchCode");
		constrains.put("correctionRequestId", id);
		List<CorrectionRemarks> remark = crDao.findForm(constrains);
		map.put("remark", remark);
		List<CorrectionRequestAmountDetails> amountDetails = cradDao.search(constrains);
		AddChallan ac = acDao.uniqueSearch(constrains);
		map.put("amountDetails", amountDetails);
		map.put("ac", ac);
		HashMap<String, Object> button = new HashMap<>();
		if (!cr.getMakerBy().equalsIgnoreCase(principal)) {
			if (!cr.rejectStatus) {
				if (cr.getCheckerApprovedBy() != null && cr.getCheckerApprovedBy().equalsIgnoreCase(principal)) {
					if (cr.getCorrectionBy() != null && cr.getCorrectionBy().equalsIgnoreCase(principal)) {
						if (cr.correctionStatus) {
							button.put("CheckerApproved", false);
							button.put("CorrectionApproved", false);
							button.put("Resolved", false);
							button.put("Rejected", false);
						} else {
							button.put("CheckerApproved", false);
							button.put("CorrectionApproved", false);
							button.put("Resolved", true);
							button.put("Rejected", false);
						}
					} else {
						button.put("CheckerApproved", false);
						button.put("CorrectionApproved", true);
						button.put("Resolved", false);
						button.put("Rejected", false);
					}
				} else {
					button.put("CheckerApproved", true);
					button.put("CorrectionApproved", false);
					button.put("Resolved", false);
					button.put("Rejected", true);
				}
			} else {
				button.put("CheckerApproved", false);
				button.put("CorrectionApproved", false);
				button.put("Resolved", false);
				button.put("Rejected", false);
			}
		} else {
			button.put("CheckerApproved", false);
			button.put("CorrectionApproved", false);
			button.put("Resolved", false);
			button.put("Rejected", false);
		}
		if (!cr.getTypeOfCorrection().isEmpty()) {
			HashMap<String, Boolean> toc = new HashMap<>();
			HashMap<String, Object> tc = new HashMap<>();
			tc.put("PAN Updation", "PU");
			tc.put("Mismatch In Gross Amount", "MITA");
			tc.put("Mismatch In TDS Amount", "MIGA");
			tc.put("Section Correction", "SC");
			tc.put("Default Correction", "DC");
			tc.put("Add Entry/Challan", "AEC");
			tc.put("Others", "O");
			String[] typeOfCorrection = cr.getTypeOfCorrection().split(Pattern.quote(","), -1);
			for (Map.Entry<String, Object> entry : tc.entrySet()) {
				for (String t : typeOfCorrection) {
					if (t.trim().equalsIgnoreCase(entry.getKey())) {
						if (!toc.isEmpty()) {
							if (toc.containsKey(entry.getValue())) {
								if (!toc.get(entry.getValue())) {
									toc.put(entry.getValue().toString(), true);
								}
							} else {
								toc.put(entry.getValue().toString(), true);
							}
						} else {
							toc.put(entry.getValue().toString(), true);
						}
					} else {
						if (toc.containsKey(entry.getValue())) {
							if (!toc.get(entry.getValue())) {
								toc.put(entry.getValue().toString(), false);
							}
						}
					}
				}
			}
			map.put("toc", toc);
		}
		map.put("button", button);
		return map;
	}

	@Override
	public void saveFile(MultipartFile blob, String entity, Long ticketNumber) {
		try {
			String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
			String date = timeStamp;

			JsonNode rootNode = mapper.readTree(entity.toString());
			JsonNode jsonObject1 = rootNode.get("cd");
			JsonNode jsonObject2 = rootNode.get("cad");
			String quarter = "";
			if (rootNode.has("correctAmount")) {
				ArrayNode jsonArray = (ArrayNode) rootNode.get("correctAmount");
				if (jsonArray.size() != 0) {
					for (JsonNode object : jsonArray) {
						check(object);
						CorrectionRequestAmountDetails requestAmountDetails = mapper.treeToValue(object,
								CorrectionRequestAmountDetails.class);
						quarter = quarter + requestAmountDetails.getQuarter() + ",";
					}
					if (quarter != null || quarter != "") {
						quarter = quarter.substring(0, quarter.toString().length() - 1);
					}
				} else if (rootNode.has("cd")) {
					JsonNode jsonElement = rootNode.get("cd");
					if (jsonElement != null) {
//					check(dataObject);
						CorrectionRequest requestAmountDetails = mapper.treeToValue(jsonElement, CorrectionRequest.class);
						if (!quarter.contains(requestAmountDetails.getQuarter()))
							quarter = quarter + requestAmountDetails.getQuarter().replace(", ", ",");
						;
						if (quarter != null || quarter != "") {
							quarter = quarter.substring(0, quarter.toString().length() - 1);
						}
					}
				}
			}
			String fy = jsonObject1.get("fy").asText();
			String q = getQuarter(quarter);
			String branchCode = jsonObject1.get("branchCode").asText();
			String path = StaticData.documentSave;
			File file = null;
			if (jsonObject2 != null) {
				if (jsonObject2.has("challanSupportingDoc")) {
					String filepath = Paths.get(path, date, fy, q, branchCode, "CorrectionRequest",
							ticketNumber.toString(), "CorrectionSupportingDocument").toString();
//					String filepath = path + "//" + date + "//" + fy + "//" + q + "//" + branchCode
//							+ "//CorrectionRequest//" + ticketNumber + "//ChallanSupportingDocument";
					file = new File(filepath);
					if (!file.exists()) {
						file.mkdirs();
					}
//				file = new File(filepath + "//" + blob.getOriginalFilename());
					try {
						InputStream inputStream = blob.getInputStream();
						logger.info(filepath);
						Path fp = Paths.get(filepath, blob.getOriginalFilename());
						logger.info(fp.toString());
						try {
							Files.copy(inputStream, fp);
						} catch (Exception e) {
							e.printStackTrace();
						}
//					blob.transferTo(file);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else if (jsonObject1 != null) {
				if (rootNode.has("docs")) {
					String filepath = Paths.get(path, date, fy, q, branchCode, "CorrectionRequest",
							ticketNumber.toString(), "CorrectionSupportingDocument").toString();
//					String filepath = path + "//" + date + "//" + fy + "//" + q + "//" + branchCode
//							+ "//CorrectionRequest//" + ticketNumber + "//CorrectionSupportingDocument";
					file = new File(filepath);
					if (!file.exists()) {
						file.mkdirs();
					}
//				file = new File(filepath + "//" + blob.getOriginalFilename());
					try {
						InputStream inputStream = blob.getInputStream();
						logger.info(filepath);
						Path fp = Paths.get(filepath, blob.getOriginalFilename());
						logger.info(fp.toString());
						try {
							Files.copy(inputStream, fp);
						} catch (Exception e) {
							e.printStackTrace();
						}
//					blob.transferTo(file);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new CustomException(e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public void saveMultiFile(List<MultipartFile> listBlob, MultipartFile blob2, String entity, Long ticketNumber) {
		try {
			HashMap<String, Object> sd = new HashMap<String, Object>();
			String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
			String date = timeStamp;

			JsonNode rootNode = mapper.readTree(entity.toString());
			JsonNode jsonObject1 = rootNode.get("cd");

			String quarter = "";
			if (rootNode.has("correctAmount")) {
				ArrayNode jsonArray = (ArrayNode) rootNode.get("correctAmount");
				if (jsonArray.size() != 0) {
					for (JsonNode object : jsonArray) {
						check(object);
						CorrectionRequestAmountDetails requestAmountDetails = mapper.treeToValue(object,
								CorrectionRequestAmountDetails.class);
						if (!quarter.contains(requestAmountDetails.getQuarter())) {
							quarter = quarter + requestAmountDetails.getQuarter() + ",";
						}
					}
					if (quarter != null || quarter != "") {
						quarter = quarter.substring(0, quarter.toString().length() - 1);
					}
				} else if (rootNode.has("cd")) {
					JsonNode jsonElement = rootNode.get("cd");
					if (jsonElement != null) {
//					check(dataObject);
						CorrectionRequest cr = mapper.treeToValue(jsonElement, CorrectionRequest.class);
						quarter = quarter + cr.getQuarter().replace(", ", ",");
						if (quarter != null || quarter != "") {
							quarter = quarter.substring(0, quarter.toString().length() - 1);
						}
					}
				}
			}

			String fy = jsonObject1.get("fy").toString();
			String q = getQuarter(quarter);
			String branchCode = jsonObject1.get("branchCode").toString();
			String path = StaticData.documentSave;
			try {
				if (path != null) {
					for (MultipartFile blob : listBlob) {
						String filepath1 = Paths.get(path, date, fy, q, branchCode, "CorrectionRequest",ticketNumber.toString(),"CorrectionSupportingDocument").toString();
//						String filepath1 = path + "//" + date + "//" + fy + "//" + q + "//" + branchCode
//								+ "//CorrectionRequest//" + ticketNumber + "/CorrectionSupportingDocument";
						File file1 = new File(filepath1);
						if (!file1.exists()) {
							file1.mkdirs();
						}
//					file1 = new File(filepath1 + "//" + blob.getOriginalFilename());

						InputStream inputStream = blob.getInputStream();
						logger.info(filepath1);
						Path fp = Paths.get(filepath1, blob.getOriginalFilename());
						logger.info(fp.toString());
						try {
							Files.copy(inputStream, fp);
						} catch (Exception e) {
							e.printStackTrace();
						}
//					blob.transferTo(file1);
					}
					String filepath2 = path + "//" + date + "//" + fy + "//" + q + "//" + branchCode
							+ "//CorrectionRequest//" + ticketNumber + "//ChallanSupportingDocument";
					File file2 = new File(filepath2);
					if (!file2.exists()) {
						file2.mkdirs();
					}
//				file2 = new File(filepath2 + "//" + blob2.getOriginalFilename());
					InputStream inputStream = blob2.getInputStream();
					logger.info(filepath2);
					Path fp = Paths.get(filepath2, blob2.getOriginalFilename());
					logger.info(fp.toString());
					try {
						Files.copy(inputStream, fp);
					} catch (Exception e) {
						e.printStackTrace();
					}
//				blob2.transferTo(file2);
				} else {
					throw new CustomException("Document Save Folder Path Not Found");
				}

			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	private String getQuarter(String quarter) {
		if (quarter.contains(",")) {
			String[] a = quarter.split(",");
			HashSet<Object> b = new HashSet<Object>(Arrays.asList(a));
			return String.join("_", b.toArray(new String[0]));
		} else {
			return quarter;
		}
	}

	private void check(JsonNode dataObject) {
		boolean empty = true;
		String[] Obj = dataObject.toString().split(Pattern.quote(","), -1);
		for (String O : Obj) {
			if (empty) {
				String[] v = O.split(Pattern.quote(":"), -1);
				if (v[0].equals("\"correctAmountPaid\"")) {
					empty = false;
					if (v[1].trim().equals("\"\"}") || v[1].trim().equals("\"\"")
							|| v[1].trim().equalsIgnoreCase("null") || v[1].trim().equalsIgnoreCase("null}")
							|| v[1] == null) {
						((ObjectNode) dataObject).remove("correctAmountPaid");
						empty = true;
					}
				} else if (v[0].equals("\"correctPan\"")) {
					empty = false;
					if (v[1].trim().equals("\"\"}") || v[1].trim().equals("\"\"")
							|| v[1].trim().equalsIgnoreCase("null") || v[1].trim().equalsIgnoreCase("null}")
							|| v[1] == null) {
						((ObjectNode) dataObject).remove("correctPan");
						empty = true;
					}
				} else if (v[0].equals("\"correctTds\"")) {
					empty = false;
					if (v[1].trim().equals("\"\"}") || v[1].trim().equals("\"\"")
							|| v[1].trim().equalsIgnoreCase("null") || v[1].trim().equalsIgnoreCase("null}")
							|| v[1] == null) {
						((ObjectNode) dataObject).remove("correctTds");
						empty = true;
					}
				} else if (v[0].equals("\"correctSection\"")) {
					empty = false;
					if (v[1].trim().equals("\"\"}") || v[1].trim().equals("\"\"")
							|| v[1].trim().equalsIgnoreCase("null") || v[1].trim().equalsIgnoreCase("null}")
							|| v[1] == null) {
						((ObjectNode) dataObject).remove("correctSection");
						empty = true;
					}
				} else if (v[0].equals("\"correctRemark\"")) {
					empty = false;
					if (v[1].trim().equals("\"\"}") || v[1].trim().equals("\"\"")
							|| v[1].trim().equalsIgnoreCase("null") || v[1].trim().equalsIgnoreCase("null}")
							|| v[1] == null) {
						((ObjectNode) dataObject).remove("correctRemark");
						empty = true;
					}
				}
			}
		}
		if (empty) {
			throw new CustomException("Please add Data in selected row.");
		}
	}

	@Override
	public void downloadDocument(Long id, HttpServletResponse response) {
		try {
			CorrectionRequest cr = dao.getByKey(id);
			String date1 = new SimpleDateFormat("dd-MM-yyyy").format(cr.getCorrectionRequestDate());
			String path = StaticData.documentSave;

//			String date = cr.getCorrectionRequestDate();
//			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
//			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//			LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
//			String date1 = dateTime.toLocalDate().format(outputFormatter);

			String[] fileName = cr.getFileName().substring(0, cr.getFileName().length() - 1).split(Pattern.quote("^"),
					-1);

			ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
			int i = fileName.length;
			for (String f : fileName) {
				String filePath = Paths
						.get(path, date1, cr.fy, cr.getQuarter().replace(", ", "_"), cr.getBranchCode().toString(),
								"CorrectionRequest", cr.getTicketNumber().toString(), "CorrectionSupportingDocument", f)
						.toString();
//				String filePath = path + "//" + date1 + "//" + cr.fy + "//" + cr.getQuarter().replace(", ", "_") + "//"
//						+ cr.getBranchCode() + "//CorrectionRequest//" + cr.getTicketNumber()
//						+ "//CorrectionSupportingDocument//" + f;
				try {
					File file = new File(filePath);
					if (file.exists()) {
						response.setStatus(HttpServletResponse.SC_OK);
						response.addHeader("Content-Disposition",
								" attachment; filename=" + cr.getTicketNumber() + ".zip");
						response.setHeader("Content-Type", "application/zip");
						response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
						zipOutputStream.putNextEntry(new ZipEntry(file.getName().split(Pattern.quote("."), -1)[0] + "_"
								+ i + "." + file.getName().split(Pattern.quote("."), -1)[1]));
						FileInputStream fileInputStream = new FileInputStream(file);
						IOUtils.copy(fileInputStream, zipOutputStream);
						fileInputStream.close();
						zipOutputStream.closeEntry();
						i--;
						logger.info(filePath);
					} else {
						response.setStatus(HttpServletResponse.SC_OK);
						response.addHeader("Content-Disposition",
								" attachment; filename=" + cr.getTicketNumber() + ".zip");
						response.setHeader("Content-Type", "application/zip");
						response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
						filePath = Paths
								.get(path, date1, cr.fy, cr.getQuarter().replace(", ", "_"), cr.getBranchCode().toString(),
										"CorrectionRequest", cr.getTicketNumber().toString(), "CorrectionSupportingDocument", f)
								.toString();
//						filePath = path + "//" + date1 + "//" + cr.fy + "//" + cr.getBranchCode()
//								+ "//CorrectionRequest//" + cr.getTicketNumber() + "//CorrectionSupportingDocument//"
//								+ f;
						file = new File(filePath);
						if (file.exists()) {
							zipOutputStream.putNextEntry(new ZipEntry(file.getName().split(Pattern.quote("."), -1)[0]
									+ "_" + i + "." + file.getName().split(Pattern.quote("."), -1)[1]));
							FileInputStream fileInputStream = new FileInputStream(file);
							IOUtils.copy(fileInputStream, zipOutputStream);
							fileInputStream.close();
							zipOutputStream.closeEntry();
							i--;
							logger.info(filePath);
						}
					}
				} catch (Exception e) {
					response.setStatus(HttpServletResponse.SC_OK);
					response.addHeader("Content-Disposition", " attachment; filename=" + cr.getTicketNumber() + ".zip");
					response.setHeader("Content-Type", "application/zip");
					response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
					filePath = Paths
							.get(path, date1, cr.fy, cr.getQuarter().replace(", ", "_"), cr.getBranchCode().toString(),
									"CorrectionRequest", cr.getTicketNumber().toString(), "CorrectionSupportingDocument", f)
							.toString();
//					filePath = path + "//" + date1 + "//" + cr.fy + "//" + cr.getBranchCode() + "//CorrectionRequest//"
//							+ cr.getTicketNumber() + "//CorrectionSupportingDocument//" + f;
					File file = new File(filePath);
					if (file.exists()) {
						zipOutputStream.putNextEntry(new ZipEntry(file.getName().split(Pattern.quote("."), -1)[0] + "_"
								+ i + "." + file.getName().split(Pattern.quote("."), -1)[1]));
						FileInputStream fileInputStream = new FileInputStream(file);
						IOUtils.copy(fileInputStream, zipOutputStream);
						fileInputStream.close();
						zipOutputStream.closeEntry();
						i--;
						logger.info(filePath);
					}
				}
			}
//			File file = new File(filePath);
//			FileInputStream fileInputStream = new FileInputStream(file);
//			response.setStatus(HttpServletResponse.SC_OK);
//			response.setHeader("Content-Disposition", " attachment; filename=" + cr.getFileName());
//			IOUtils.copy(fileInputStream, response.getOutputStream());
//			fileInputStream.close();
//			response.getOutputStream().close();

//			for (String filePath : filesToSend) {
//				
//			}
			zipOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	@Override
	public CorrectionRequest getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public void saveCorrection(LinkedHashMap<String, Object> entity, String userName) {
		// Login l = applicationCache.getLoginDetail(getPrincipal());
		try {
			JsonNode jsonElement = mapper.valueToTree(entity.get("cd"));
			JsonNode jsonElement1 = mapper.valueToTree(entity.get("correctAmount"));
			CorrectionRequest cr = mapper.treeToValue(jsonElement, CorrectionRequest.class);
			String name = "";
			String pan = "";
			String quarter = "";
			if (!jsonElement1.isNull()) {
				if (jsonElement1.size() != 0) {
					for (JsonNode object : jsonElement1) {
						check(object);
						setCorrectionRequest(cr, userName);
						dao.persist(cr);
						CorrectionRequestAmountDetails requestAmountDetails = mapper.treeToValue(object,
								CorrectionRequestAmountDetails.class);
						requestAmountDetails.setCorrectionRequestId(cr.getId());
						cradDao.persist(requestAmountDetails);
						if (!quarter.contains(requestAmountDetails.getQuarter())) {
							quarter += requestAmountDetails.getQuarter() + ", ";
						}
						if (cr.getName() == null || cr.getName() == "") {
							name = object.get("name").toString();
						}
						if (cr.getPan() == null || cr.getPan() == "") {
							if (pan != "") {
								if (!pan.contains(object.get("pan").toString())) {
									pan += object.get("pan").toString() + ", ";
								}
							} else {
								pan += object.get("pan").toString() + ", ";
							}
						}
					}
					if (quarter != null || quarter != "") {
						cr.setQuarter(quarter.substring(0, quarter.toString().length() - 2));
					}
					if (cr.getName() == null || cr.getName() == "") {
						cr.setName(name.replace("\"", ""));
					}
					if (cr.getPan() == null || cr.getPan() == "") {
						cr.setPan(pan.substring(0, pan.toString().length() - 2).replace("\"", ""));
					}
					dao.update(cr);
					try {
						sendMail(cr);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					setCorrectionRequest(cr, userName);
					dao.persist(cr);
					try {
						sendMail(cr);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				setCorrectionRequest(cr, userName);
				dao.persist(cr);
				System.out.println("done");
				try {
					sendMail(cr);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			/*
			 * JsonElement jsonElement2 = gson.toJsonTree(entity.get("cad")); if
			 * (!jsonElement2.isJsonNull()) { getService().save(cr); AddChallan cad =
			 * gson.fromJson(jsonElement2, AddChallan.class);
			 * cad.setCorrectionRequestId(cr.getId()); challanService.save(cad); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

	}

	@Override
	public void sendMail(CorrectionRequest cr) {
		CorrectionRequestMail crm = new CorrectionRequestMail();
		HashMap<String, String> map = new HashMap<String, String>();
		String branchCode = cr.getBranchCode().toString();
		Branch branchModel = getBranch(Long.parseLong(branchCode));
		String emailTo = branchModel.getBranchEmail();
		map.put("emailTo", emailTo);
		map.put("subject", "New Correction Request [" + cr.getTicketNumber() + "]");
		map.put("message",
				"We wish to inform you that a new correction request with the following details has been added ,");
		crm.sendEmail(map, cr);

	}

	public Branch getBranch(long branchCode) {
		// TODO Auto-generated method stub
		return bDao.getByKey(branchCode);
	}

	public void setCorrectionRequest(CorrectionRequest cr, String userName) {
		cr.setCorrectionRequestDate(new Date());
		cr.setTicketNumber(autoGenerateTicketNumber());
		cr.setMakerBy(userName);
		cr.setStatus("Pending Checker Approval");
		cr.setRejectStatus(false);
		cr.setRegenarateRequest(false);
		
		if(cr.getQuarter().contains(",")) {
			String q = cr.getQuarter().substring(0, cr.getQuarter().toString().length() - 2);
			cr.setQuarter(q);
		}else {
			cr.setQuarter(cr.getQuarter());
		}
		
		if(cr.getTypeOfCorrection().contains(",")) {
			String tos = cr.getTypeOfCorrection().substring(0, cr.getTypeOfCorrection().toString().length() - 2);
			cr.setTypeOfCorrection(tos);
		}else {
			cr.setTypeOfCorrection(cr.getTypeOfCorrection());
		}
		
		cr.setLastUpdatedOn(new Date());
	}

}
