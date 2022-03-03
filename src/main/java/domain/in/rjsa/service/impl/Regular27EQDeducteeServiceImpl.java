package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular27EQDeducteeDao;
import domain.in.rjsa.model.fy.Regular26QDeductee;
import domain.in.rjsa.model.fy.Regular27EQDeductee;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.Regular27EQDeducteeService;
@Transactional("transactionManager")
@Service("regular27EQDeducteeService")
public class Regular27EQDeducteeServiceImpl extends AbstractServiceFY<Long, Regular27EQDeductee, Regular27EQDeducteeDao> implements Regular27EQDeducteeService{

	@Autowired 
	Regular27EQDeducteeDao dao;
	
	@Override
	public Regular27EQDeductee getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public Regular27EQDeducteeDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	

}
