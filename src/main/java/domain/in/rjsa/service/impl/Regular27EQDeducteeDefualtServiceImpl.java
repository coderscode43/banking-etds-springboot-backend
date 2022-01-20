package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular27EQDeducteeDefualtDao;
import domain.in.rjsa.model.form.Regular27EQDeducteeDefualt;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular27EQDeducteeDefualtService;
@Transactional("transactionManager")
@Service("regular27EQDeducteeDefualtService")
public class Regular27EQDeducteeDefualtServiceImpl extends AbstractService<Long, Regular27EQDeducteeDefualt, Regular27EQDeducteeDefualtDao> implements Regular27EQDeducteeDefualtService{

	@Autowired 
	Regular27EQDeducteeDefualtDao dao;
	
	@Override
	public Regular27EQDeducteeDefualt getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public Regular27EQDeducteeDefualtDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
