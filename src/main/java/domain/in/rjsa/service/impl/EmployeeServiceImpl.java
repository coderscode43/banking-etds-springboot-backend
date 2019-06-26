package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.EmployeeDao;
import domain.in.rjsa.model.form.Employee;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.EmployeeService;
@Transactional("transactionManager")
@Service("employeeService")
public class EmployeeServiceImpl extends AbstractService<Long, Employee, EmployeeDao> implements EmployeeService 
{
@Autowired
EmployeeDao dao;
	@Override
	public EmployeeDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Employee getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
	}
