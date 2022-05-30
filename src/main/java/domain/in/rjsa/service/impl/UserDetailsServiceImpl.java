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

import domain.in.rjsa.dao.LoginDao;
import domain.in.rjsa.dao.UserDetailsDao;
import domain.in.rjsa.excel.UserDetailsExcel;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.UserDetails;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.UserDetailsService;

@Transactional
@Service("UserDetailsService")
public class UserDetailsServiceImpl extends AbstractServiceForm<String, UserDetails, UserDetailsDao> implements UserDetailsService {

	
	@Autowired
	UserDetailsDao dao;
	@Autowired
	LoginDao lDao;
	
	UserDetailsExcel userDetailsExcel;
	public static String path;
	public String ExcelFile;
	
	@Override
	public UserDetailsDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public UserDetails getUserByUserName(String userName) {
		return dao.getByKey(userName);
	}
	


	@Override
	public void saveNewUser(String userName, String password) {
		Login newUser = new Login();
		newUser.setUserName(userName);
		String hash = generateHash(password);
		newUser.setPassword(hash);
		newUser.setAccessLevel(4);
		newUser.setPasswordReset(false);
		newUser.setEnabled(true);
		newUser.setType("Test");
		newUser.setRefId(1);
		
		lDao.persist(newUser);
	}
	public String generateHash(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);

	}

	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<UserDetails> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		userDetailsExcel = new UserDetailsExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		userDetailsExcel.initialise(path + "static/report/TDS-" + timestamp + "-UserDetails.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-UserDetails.xlsx";

		int row = 1;

		for (UserDetails user : listUsers) {
			Workbook wb = userDetailsExcel.getWorkbook();
			Sheet userDetails = wb.getSheet("userDetails");
			Row details = userDetails.createRow(row);
			details.createCell(0).setCellValue(row);

			if (user.getEmployeeId() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(user.getEmployeeId());
			}
			if (user.getTypeOfUser() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(user.getTypeOfUser());
			}
			
			row++;

		}
		userDetailsExcel.close();
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
