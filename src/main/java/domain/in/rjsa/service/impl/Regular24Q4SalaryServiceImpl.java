package domain.in.rjsa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular24Q4SalaryDao;
import domain.in.rjsa.model.form.Regular24Q4Salary;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular24Q4SalaryService;

@Transactional("transactionManager")
@Service("regular24Q4SalaryService")
public class Regular24Q4SalaryServiceImpl extends AbstractService<Long, Regular24Q4Salary, Regular24Q4SalaryDao> implements Regular24Q4SalaryService {

	@Autowired 
	Regular24Q4SalaryDao dao;
	@Override
	public Regular24Q4SalaryDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Regular24Q4Salary getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
