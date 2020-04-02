package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.in.rjsa.dao.UserSolDao;
import domain.in.rjsa.model.form.UserSol;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.UserSolService;

@Service("userSolService")
public class UserSolServiceImpl extends AbstractService<Long, UserSol, UserSolDao> implements UserSolService{

	@Autowired
	UserSolDao dao;
	
	@Override
	public UserSolDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
