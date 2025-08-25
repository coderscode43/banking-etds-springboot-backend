package domain.in.rjsa.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.BranchDao;
import domain.in.rjsa.dao.RegularReturnDao;
import domain.in.rjsa.dao.RegularReturnRemarkDao;
import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.email.ReminderMail;
import domain.in.rjsa.excel.RegularReturnExcel;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.RegularReturn;
import domain.in.rjsa.model.form.RegularReturnRemark;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.RegularReturnService;
import domain.in.rjsa.util.StaticData;

@Transactional("transactionManager")
@Service("regularReturnService")
public class RegularReturnServiceImpl extends AbstractServiceForm<Long, RegularReturn, RegularReturnDao>
		implements RegularReturnService {

	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	RegularReturnDao dao;

	@Autowired
	RegularReturnRemarkDao rDao;

	@Autowired
	StaticDataDao sDao;

	@Autowired
	BranchDao bDao;

	@Autowired
	ReminderMail reminderMail;

	RegularReturnExcel regularReturnExcel;
	public static String path;
	public String ExcelFile;

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}

	@Override
	public RegularReturnDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Object getDetail(HashMap<String, Object> constrains, Long id, String principal, String roCode) {
		constrains.put("id", id);
		HashMap<String, Object> map = new HashMap<>();
		RegularReturn r = dao.uniqueSearch(constrains);
		map.put("details", r);
		constrains.remove("id");
		constrains.remove("branchCode");
		constrains.put("regularReturnId", id);
		List<RegularReturnRemark> remarks = rDao.findForm(constrains);
		map.put("remarks", remarks);
		return map;
	}

	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<RegularReturn> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		regularReturnExcel = new RegularReturnExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		regularReturnExcel.initialise(path + "static/report/TDS-" + timestamp + "-RegularReturn.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-RegularReturn.xlsx";

		int row = 1;
		int part = 1;
		Workbook wb = regularReturnExcel.getWorkbook();
		Sheet regularReturnExcelSheet = wb.getSheet("RegularReturn-" + part);

		for (RegularReturn regularReturn : listUsers) {
			Row details = regularReturnExcelSheet.createRow(row);
			details.createCell(0).setCellValue(row);

			if (regularReturn.getAddedOn() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1)
						.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(regularReturn.getAddedOn()));
			}
			if (regularReturn.getFy() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(regularReturn.getFy());
			}
			if (regularReturn.getTan() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(regularReturn.getTan());
			}
			if (regularReturn.getQuarter() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(regularReturn.getQuarter());
			}
			if (regularReturn.getForm() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(regularReturn.getForm());
			}
			if (regularReturn.getAddedBy() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(regularReturn.getAddedBy());
			}
			if (regularReturn.getLatestRemark() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(regularReturn.getLatestRemark());
			}
			if (regularReturn.getStatus() == null) {
				details.createCell(8).setCellValue(" ");
			} else {
				details.createCell(8).setCellValue(regularReturn.getStatus());
			}
			if (regularReturn.getReturnFilingDate() == null) {
				details.createCell(9).setCellValue(" ");
			} else {
				details.createCell(9)
						.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(regularReturn.getReturnFilingDate()));
			}

			if (row > 1000000) {
				part++;
				wb = regularReturnExcel.getWorkbook();
				regularReturnExcelSheet = regularReturnExcel.initializeSheet("RegularReturn-" + part);
				row = 0;
			}

			row++;

		}
		regularReturnExcel.close();
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
	public List<?> getList(HashMap<String, Object> constrains, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.findall(constrains, pageNo, resultPerPage);
	}

	@Override
	public Long findallCountwithBranch(HashMap<String, Object> constrains) {
		String roCode = constrains.get("branchCode").toString();
		constrains.put("roCode", roCode);
		constrains.remove("branchCode");
		return dao.findallCount(constrains);
	}

	@Override
	public void addRegularReturn(RegularReturn rr, String userName) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("fy", rr.getFy());
		map.put("quarter", rr.getQuarter());
		map.put("form", rr.getForm());
		Branch branch = new Branch();

		if (rr.getTan().equalsIgnoreCase("selectAll")) {
			String[] tanList = StaticData.Tan;
			for (String tan : tanList) {
				map.put("tan", tan);
				HashMap<String, Object> b = new HashMap<>();
				b.put("tan", tan.split(Pattern.quote("-"))[0]);
				branch = bDao.uniqueSearch(b);
				logger.info("################# Branch details: ", branch);
				if (branch != null) {
					List<RegularReturn> lrr = dao.search(map);
					logger.info("################# RegularReturn details of tan" + map + " : ", lrr);
					if (lrr.isEmpty()) {
						RegularReturn r = new RegularReturn();
						r.setFy(rr.getFy());
						r.setForm(rr.getForm());
						r.setQuarter(rr.getQuarter());
						r.setTan(tan);
						r.setAddedBy(userName);
						r.setAddedOn(new Date());
						r.setBranchCode(branch.getBranchCode().toString());
						r.setStatus("Request for data from RO");
						dao.persist(r);
						try {
							sendMail(r, branch);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						logger.warn("################# This Combination of FY, QUARTER, TAN, FORM Already Exist.");
					}
				}
			}
			logger.info("################# Details added successfully: #################");
		} else {
			map.put("tan", rr.getTan());
			HashMap<String, Object> b = new HashMap<>();
			b.put("tan", rr.getTan().split(Pattern.quote("-"))[0]);
			branch = bDao.uniqueSearch(b);
			logger.info("################# Branch details: " + branch);
			if (branch != null) {
				List<RegularReturn> lrr = dao.search(map);
				logger.info("RegularReturn details of tan" + map + " : ", lrr);
				if (lrr.isEmpty()) {
					rr.setAddedBy(userName);
					rr.setAddedOn(new Date());
					rr.setBranchCode(branch.getBranchCode().toString());
					rr.setStatus("Request for data from RO");
					dao.persist(rr);
					logger.info("################# Details added successfully: #################");
					try {
						sendMail(rr, branch);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					logger.warn("################# This Combination of FY, QUARTER, TAN, FORM Already Exist.");
					throw new CustomException("This Combination of FY, QUARTER, TAN, FORM Already Exist.");
				}
			}
		}

	}

	private void sendMail(RegularReturn r, Branch branch) {
		HashMap<String, String> data = new HashMap<String, String>();
		String branchCode = branch.getBranchCode().toString();
		Branch branchModel = getBranch(Long.parseLong(branchCode));
		String emailTo = branchModel.getBranchEmail();
		data.put("branchCode", branch.getBranchCode().toString());
		data.put("fy", r.getFy());
		data.put("form", r.getForm());
		data.put("addedBy", r.getAddedBy());
		data.put("id", r.getId().toString());
		data.put("addedOn", r.getAddedOn().toString());
		data.put("quarter", r.getQuarter());
		data.put("emailTo", emailTo);
		data.put("tan", r.getTan());
		data.put("subject", "Request for " + r.getQuarter() + ", " + r.getForm().split(Pattern.quote("-"), -1)[0] + " Return Data Files for F.Y. "
				+ r.getFy() + ", TAN :" + r.getTan().split(Pattern.quote("-"), -1)[0]);
		data.put(branchCode, emailTo);
//		reminderMail.sendEmail(data);

	}

	public Branch getBranch(long branchCode) {
		// TODO Auto-generated method stub
		return bDao.getByKey(branchCode);
	}

}
