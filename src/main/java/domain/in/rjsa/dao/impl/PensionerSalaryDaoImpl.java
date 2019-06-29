package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.PensionerSalaryDao;
import domain.in.rjsa.model.form.PensionerSalary;
@Repository("pensionsalaryDao")
public class PensionerSalaryDaoImpl extends AbstractNewDao<Long, PensionerSalary> implements PensionerSalaryDao  {

}
