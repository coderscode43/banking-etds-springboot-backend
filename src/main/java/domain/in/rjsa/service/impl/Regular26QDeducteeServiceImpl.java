package domain.in.rjsa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular26QDeducteeDao;
import domain.in.rjsa.model.form.Regular26QDeductee;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular26QDeducteeService;

@Transactional("transactionManager")
@Service("regular26QDeducteeService")
public class Regular26QDeducteeServiceImpl extends AbstractService<Long, Regular26QDeductee, Regular26QDeducteeDao> implements Regular26QDeducteeService{

	@Autowired 
	Regular26QDeducteeDao dao;
	@Override
	public Regular26QDeducteeDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
