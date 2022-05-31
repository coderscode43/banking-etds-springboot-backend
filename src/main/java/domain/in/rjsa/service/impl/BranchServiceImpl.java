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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.BranchDao;
import domain.in.rjsa.dao.LoginDao;
import domain.in.rjsa.excel.BranchExcel;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.BranchService;

@Transactional("transactionManager")
@Service("branchService")
public class BranchServiceImpl extends AbstractServiceForm<Long, Branch, BranchDao> implements BranchService{
	@Autowired
	BranchDao dao;
	@Autowired
	LoginDao lDao;
	
	BranchExcel branchExcel;
	public static String path;
	public String ExcelFile;
	
	@Override
	public BranchDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Branch getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
	
	@Override
	public List<String> ajax(String name, String term) {
		// TODO Auto-generated method stub
		return dao.ajax(name, term);
	}
	
	@Override
	public void saveNewUser(String userName, String password,Long branchCode) {
		Login newUser = new Login();
		newUser.setUserName(userName);
		String hash = generateHash(password);
		newUser.setPassword(hash);
		newUser.setAccessLevel(4);
		newUser.setPasswordReset(false);
		newUser.setEnabled(true);
		newUser.setType("NA");
		newUser.setRefId(1);
		newUser.setClientId(1);
		newUser.setBranchCode(branchCode);
		lDao.persist(newUser);;
	}
	public String generateHash(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);

	}

	/* pranay */
	@Override
	public Long findSearchCount(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findSearchCount(map);
	}
	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<Branch> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		branchExcel = new BranchExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		branchExcel.initialise(path + "static/report/TDS-" + timestamp + "-Branch.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-Branch.xlsx";

		int row = 1;

		for (Branch brnch : listUsers) {
			Workbook wb = branchExcel.getWorkbook();
			Sheet branch = wb.getSheet("branch");
			Row details = branch.createRow(row);
			details.createCell(0).setCellValue(row);

			if (brnch.getBranchCode() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(brnch.getBranchCode());
			}
			if (brnch.getRoCode() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(brnch.getRoCode());
			}
			if (brnch.getBranchName() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(brnch.getBranchName());
			}
			if (brnch.getBranchEmail() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(brnch.getBranchEmail());
			}
			if (brnch.getBranchContactNo() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(brnch.getBranchContactNo());
			}
			if (brnch.getBranchAddress() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(brnch.getBranchAddress());
			}
			if (brnch.getBranchPinCode() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7).setCellValue(brnch.getBranchPinCode());
			}
			if (brnch.getBranchState() == null) {
				details.createCell(8).setCellValue(" ");
			} else {
				details.createCell(8).setCellValue(brnch.getBranchState());
			}
			row++;

		}
		branchExcel.close();
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
