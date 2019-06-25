package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.SalaryDao;
import domain.in.rjsa.model.form.Salary;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.SalaryService;

@Transactional("transactionManager")
@Service("salaryService")
public class SalaryServiceImpl extends AbstractService<Long, Salary, SalaryDao> implements SalaryService{
@Autowired
SalaryDao dao;
	@Override
	public SalaryDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
