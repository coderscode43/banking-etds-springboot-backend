package domain.in.rjsa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular26QDeducteeDao;
import domain.in.rjsa.model.fy.Regular26QDeductee;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.Regular26QDeducteeService;

@Transactional("transactionManager")
@Service("regular26QDeducteeService")
public class Regular26QDeducteeServiceImpl extends AbstractServiceFY<Long, Regular26QDeductee, Regular26QDeducteeDao> implements Regular26QDeducteeService{

	@Autowired 
	Regular26QDeducteeDao dao;
	@Override
	public Regular26QDeducteeDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Regular26QDeductee getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
