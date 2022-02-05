package domain.in.rjsa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular27QDeducteeDefualtDao;
import domain.in.rjsa.model.fy.Regular27QDeducteeDefualt;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.Regular27QDeducteeDefualtService;
@Transactional("transactionManager")
@Service("regular27QDeducteeDefualtService")
public class Regular27QDeducteeDefualtServiceImpl extends AbstractServiceFY<Long, Regular27QDeducteeDefualt, Regular27QDeducteeDefualtDao> implements Regular27QDeducteeDefualtService{

	@Autowired 
	Regular27QDeducteeDefualtDao dao;
	
	@Override
	public Regular27QDeducteeDefualtDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Regular27QDeducteeDefualt getByKey(Long id) {
		return dao.getByKey(id);
	}

}
