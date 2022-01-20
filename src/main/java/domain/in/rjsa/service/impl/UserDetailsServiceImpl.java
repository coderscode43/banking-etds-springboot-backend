package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.UserDetailsDao;
import domain.in.rjsa.model.form.UserDetails;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.UserDetailsService;

@Transactional
@Service("UserDetailsService")
public class UserDetailsServiceImpl extends AbstractService<Long, UserDetails, UserDetailsDao> implements UserDetailsService {

	
	@Autowired
	UserDetailsDao dao;
	
	@Override
	public UserDetailsDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	

	@Override
	public UserDetails getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
}
