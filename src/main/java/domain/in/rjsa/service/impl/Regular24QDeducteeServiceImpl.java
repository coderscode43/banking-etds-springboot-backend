package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular24QDeducteeDao;
import domain.in.rjsa.model.form.Regular24QDeductee;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular24QDeducteeService;

@Transactional("transactionManager")
@Service("regular24QDeducteeService")
public class Regular24QDeducteeServiceImpl extends AbstractService<Long, Regular24QDeductee, Regular24QDeducteeDao>
		implements Regular24QDeducteeService {

	@Autowired
	Regular24QDeducteeDao dao;

	@Override
	public Regular24QDeducteeDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Regular24QDeductee getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
