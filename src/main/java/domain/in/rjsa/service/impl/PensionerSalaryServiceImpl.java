package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.PensionerSalaryDao;
import domain.in.rjsa.model.fy.PensionerSalary;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.PensionerSalaryService;


@Transactional("transactionManager")
@Service("pensionsalaryService")
public class PensionerSalaryServiceImpl extends AbstractServiceFY<Long, PensionerSalary, PensionerSalaryDao> implements PensionerSalaryService {
	@Autowired
	PensionerSalaryDao dao;
	@Override
	public PensionerSalaryDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public PensionerSalary getByKey(Long pensionerId) {
		// TODO Auto-generated method stub
		return dao.getByKey(pensionerId);
	}

}
