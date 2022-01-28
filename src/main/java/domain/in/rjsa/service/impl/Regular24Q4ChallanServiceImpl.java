package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular24Q4ChallanDao;
import domain.in.rjsa.model.fy.Regular24Q4Challan;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular24Q4ChallanService;
@Transactional("transactionManager")
@Service("regular24Q4ChallanService")
public class Regular24Q4ChallanServiceImpl extends AbstractService<Long, Regular24Q4Challan, Regular24Q4ChallanDao>
implements Regular24Q4ChallanService{

	@Autowired
	Regular24Q4ChallanDao dao;

	@Override
	public Regular24Q4ChallanDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
