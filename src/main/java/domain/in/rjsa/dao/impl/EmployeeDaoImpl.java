package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.EmployeeDao;
import domain.in.rjsa.model.form.Employee;
@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractNewDao<Long, Employee> implements EmployeeDao  
{
	}
