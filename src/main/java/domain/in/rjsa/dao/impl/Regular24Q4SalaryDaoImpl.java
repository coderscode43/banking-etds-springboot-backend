package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.Regular24Q4SalaryDao;
import domain.in.rjsa.model.form.Regular24Q4Salary;

@Repository("regular24Q4SalaryDao")
public class Regular24Q4SalaryDaoImpl extends AbstractNewDao<Long, Regular24Q4Salary> implements Regular24Q4SalaryDao {

}
