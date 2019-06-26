package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular24Q4DeducteeDao;
import domain.in.rjsa.dao.Regular26QDeducteeDao;
import domain.in.rjsa.model.form.Regular24Q4Deductee;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular24Q4DeducteeService;

@Transactional("transactionManager")
@Service("regular24Q4DeducteeService")
public class Regular24Q4DeducteeServiceImpl extends AbstractService<Long, Regular24Q4Deductee, Regular24Q4DeducteeDao> implements Regular24Q4DeducteeService {

	@Autowired 
	Regular24Q4DeducteeDao dao;
	
	@Override
	public Regular24Q4DeducteeDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
