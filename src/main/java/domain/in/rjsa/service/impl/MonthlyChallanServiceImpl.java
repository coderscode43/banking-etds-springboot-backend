package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.MonthlyChallanDao;
import domain.in.rjsa.model.fy.MonthlyChallan;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.MonthlyChallanService;

@Transactional("transactionManager")
@Service("monthlyChallanService")
public class MonthlyChallanServiceImpl extends AbstractServiceFY<Long, MonthlyChallan, MonthlyChallanDao> implements MonthlyChallanService{
	@Autowired
	MonthlyChallanDao dao;
		@Override
		public MonthlyChallanDao getPrimaryDao() {
			// TODO Auto-generated method stub
			return dao;
		}
	//	@Override
	//	public MonthlyChallan getByKey(Long id) {
			// TODO Auto-generated method stub
		//	return dao.getByKey(id);
		//}
		
	}
