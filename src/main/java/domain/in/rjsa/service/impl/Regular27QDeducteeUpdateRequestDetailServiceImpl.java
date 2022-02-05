package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular27QDeducteeUpdateRequestDetailDao;
import domain.in.rjsa.model.fy.Regular27QDeducteeUpdateRequestDetail;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.Regular27QDeducteeUpdateRequestDetailService;

@Transactional("transactionManager")
@Service("regular27QDeducteeUpdateRequestDetailService")
public class Regular27QDeducteeUpdateRequestDetailServiceImpl
		extends AbstractServiceFY<Long, Regular27QDeducteeUpdateRequestDetail, Regular27QDeducteeUpdateRequestDetailDao>
		implements Regular27QDeducteeUpdateRequestDetailService {

	@Autowired
	Regular27QDeducteeUpdateRequestDetailDao dao;

	@Override
	public Regular27QDeducteeUpdateRequestDetailDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Regular27QDeducteeUpdateRequestDetail getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
