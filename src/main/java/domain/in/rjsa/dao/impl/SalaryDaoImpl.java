package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.SalaryDao;
import domain.in.rjsa.model.form.Salary;
@Repository("salaryDao")
public class SalaryDaoImpl extends AbstractNewDao<Long, Salary> implements SalaryDao{

}
