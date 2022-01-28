package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular27EQDeducteeUpdateRequestDetailDao;
import domain.in.rjsa.model.fy.Regular27EQDeducteeUpdateRequestDetail;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular27EQDeducteeUpdateRequestDetailService;
@Transactional("transactionManager")
@Service("regular27EQDeducteeUpdateRequestDetailService")
public class Regular27EQDeducteeUpdateRequestDetailServiceImpl extends AbstractService<Long, Regular27EQDeducteeUpdateRequestDetail, Regular27EQDeducteeUpdateRequestDetailDao>
implements Regular27EQDeducteeUpdateRequestDetailService{

	@Autowired
	Regular27EQDeducteeUpdateRequestDetailDao dao;
	
	@Override
	public Regular27EQDeducteeUpdateRequestDetail getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public Regular27EQDeducteeUpdateRequestDetailDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
