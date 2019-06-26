package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.Regular27QDeducteeDao;
import domain.in.rjsa.model.form.Regular27QDeductee;

@Repository("regular27QDeducteeDao")
public class Regular27QDeducteeDaoImpl extends AbstractNewDao<Long, Regular27QDeductee> implements Regular27QDeducteeDao {

}