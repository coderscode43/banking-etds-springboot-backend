package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular26QDeducteeUpdateRequestDetailDao;
import domain.in.rjsa.model.form.Regular24QDeductee;
import domain.in.rjsa.model.form.Regular26QDeducteeUpdateRequestDetail;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular26QDeducteeUpdateRequestDetailService;

@Transactional("transactionManager")
@Service("regular26QDeducteeUpdateRequestDetailService")
public class Regular26QDeducteeUpdateRequestDetailServiceImpl extends AbstractService<Long, Regular26QDeducteeUpdateRequestDetail, Regular26QDeducteeUpdateRequestDetailDao> implements Regular26QDeducteeUpdateRequestDetailService {

	@Autowired
	Regular26QDeducteeUpdateRequestDetailDao dao;
	
	@Override
	public Regular26QDeducteeUpdateRequestDetailDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	@Override
	public Regular26QDeducteeUpdateRequestDetail getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}