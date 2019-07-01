package domain.in.rjsa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular26QChallanDao;
import domain.in.rjsa.model.form.Regular26QChallan;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular26QChallanService;

@Transactional("transactionManager")
@Service("regular26QChallanService")
public class Regular26QChallanServiceImpl extends AbstractService<Long, Regular26QChallan, Regular26QChallanDao> implements Regular26QChallanService{
@Autowired
Regular26QChallanDao dao;
	@Override
	public Regular26QChallanDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
