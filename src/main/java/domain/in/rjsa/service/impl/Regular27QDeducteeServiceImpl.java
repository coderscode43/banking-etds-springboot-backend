package domain.in.rjsa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular27QDeducteeDao;
import domain.in.rjsa.model.fy.Regular27QDeductee;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular27QDeducteeService;
@Transactional("transactionManager")
@Service("regular27QDeducteeService")
public class Regular27QDeducteeServiceImpl extends AbstractService<Long, Regular27QDeductee, Regular27QDeducteeDao> implements Regular27QDeducteeService{

	@Autowired 
	Regular27QDeducteeDao dao;
	
	@Override
	public Regular27QDeducteeDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Regular27QDeductee getByKey(Long id) {
		return dao.getByKey(id);
	}

}
