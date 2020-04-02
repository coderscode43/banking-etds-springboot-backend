package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular24QSalaryDao;
import domain.in.rjsa.model.form.Regular24QSalary;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular24QSalaryService;

@Transactional("transactionManager")
@Service("regular24QSalaryService")
public class Regular24QSalaryServiceImpl extends AbstractService<Long, Regular24QSalary, Regular24QSalaryDao>
		implements Regular24QSalaryService {

	@Autowired
	Regular24QSalaryDao dao;

	@Override
	public Regular24QSalaryDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Regular24QSalary getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
