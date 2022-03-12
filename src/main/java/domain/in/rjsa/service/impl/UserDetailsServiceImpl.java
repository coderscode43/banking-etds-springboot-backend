package domain.in.rjsa.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.LoginDao;
import domain.in.rjsa.dao.UserDetailsDao;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.UserDetails;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.UserDetailsService;

@Transactional
@Service("UserDetailsService")
public class UserDetailsServiceImpl extends AbstractServiceForm<Long, UserDetails, UserDetailsDao> implements UserDetailsService {

	
	@Autowired
	UserDetailsDao dao;
	@Autowired
	LoginDao lDao;
	
	@Override
	public UserDetailsDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public UserDetails getUserByUserName(String userName) {
		return dao.getByuserName(userName);
	}
	

	@Override
	public UserDetails getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
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
}
