package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.BranchDao;
import domain.in.rjsa.dao.LoginDao;
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
	public void saveNewUser(String userName, String password) {
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

}
