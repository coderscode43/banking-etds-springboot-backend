package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular27QChallanDao;
import domain.in.rjsa.model.fy.Regular27QChallan;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular27QChallanService;

@Transactional("transactionManager")
@Service("regular27QChallanService")
public class Regular27QChallanServiceImpl extends AbstractService<Long, Regular27QChallan, Regular27QChallanDao>
implements Regular27QChallanService{
	@Autowired
	Regular27QChallanDao dao;
	
	@Override
	public Regular27QChallanDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
