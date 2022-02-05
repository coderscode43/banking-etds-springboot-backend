package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular24QDeducteeUpdateRequestDetailDao;
import domain.in.rjsa.model.fy.Regular24QDeducteeUpdateRequestDetail;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.Regular24QDeducteeUpdateRequestDetailService;

@Transactional("transactionManager")
@Service("regular24QDeducteeUpdateRequestDetailService")
public class Regular24QDeducteeUpdateRequestDetailServiceImpl
		extends AbstractServiceFY<Long, Regular24QDeducteeUpdateRequestDetail, Regular24QDeducteeUpdateRequestDetailDao>
		implements Regular24QDeducteeUpdateRequestDetailService {

	@Autowired
	Regular24QDeducteeUpdateRequestDetailDao dao;

	@Override
	public Regular24QDeducteeUpdateRequestDetailDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Regular24QDeducteeUpdateRequestDetail getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
