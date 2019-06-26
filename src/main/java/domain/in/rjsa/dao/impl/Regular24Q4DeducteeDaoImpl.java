package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.Regular24Q4DeducteeDao;
import domain.in.rjsa.model.form.Regular24Q4Deductee;

@Repository("regular24Q4DeducteeDao")
public class Regular24Q4DeducteeDaoImpl  extends AbstractNewDao<Long, Regular24Q4Deductee> implements Regular24Q4DeducteeDao{

}
