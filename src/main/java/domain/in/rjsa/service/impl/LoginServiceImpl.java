package domain.in.rjsa.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.LoginDao;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.LoginService;
import domain.in.rjsa.web.ApplicationCache;

@Transactional("transactionManager")
@Service("loginService")
public class LoginServiceImpl extends AbstractServiceForm<Long, Login, LoginDao> implements LoginService {

	@Autowired
	LoginDao dao;
	ApplicationCache applicationCache;
	@Autowired
	public void setApplicationCache(ApplicationCache ap){
		this.applicationCache= ap;
		}

	@Override
	public void updateLogin(Login login) {
		// TODO Auto-generated method stub
		dao.update(login);
	}

	@Override
	public Login getLogin(String userName) {
		// TODO Auto-generated method stub
		return dao.getByuserName(userName);
	}

	@Override
	public void persist(Login login) {
		dao.persist(login);

	}

	@Override
	public HashMap<String, Login> getUserNameLogin() {
		// TODO Auto-generated method stub
		List<Login> all = dao.findall();
		HashMap<String, Login> map = new HashMap<String, Login>();
		for (Login l : all) {
			map.put(l.getUserName(), l);
		}
		return map;
	}

	public void updatePassword(Login login, String password) {
		String hash = generateHash(password);
		login.setPassword(hash);
		login.setPasswordReset(false);
		updateLogin(login);
	}

	public String generateHash(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);

	}

	@Override
	public LoginDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login getUserByName(String userName) {
		// TODO Auto-generated method stub
		return dao.getByuserName(userName);
	}

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Login getByAuth(String auth) {
		// TODO Auto-generated method stub
		return dao.getByAuth(auth);
	}
}
