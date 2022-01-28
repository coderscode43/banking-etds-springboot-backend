package domain.in.rjsa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular26QDeducteeDefualtDao;
import domain.in.rjsa.model.fy.Regular26QDeducteeDefualt;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular26QDeducteeDefualtService;

@Transactional("transactionManager")
@Service("regular26QDeducteeDefualtService")
public class Regular26QDeducteeDefualtServiceImpl extends AbstractService<Long, Regular26QDeducteeDefualt, Regular26QDeducteeDefualtDao> implements Regular26QDeducteeDefualtService{

	@Autowired 
	Regular26QDeducteeDefualtDao dao;
	@Override
	public Regular26QDeducteeDefualtDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Regular26QDeducteeDefualt getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
