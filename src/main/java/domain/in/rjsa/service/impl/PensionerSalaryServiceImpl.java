package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.PensionerSalaryDao;
import domain.in.rjsa.model.form.PensionerSalary;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.PensionerSalaryService;


@Transactional("transactionManager")
@Service("pensionsalaryService")
public class PensionerSalaryServiceImpl extends AbstractService<Long, PensionerSalary, PensionerSalaryDao> implements PensionerSalaryService {
	@Autowired
	PensionerSalaryDao dao;
	@Override
	public PensionerSalaryDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
