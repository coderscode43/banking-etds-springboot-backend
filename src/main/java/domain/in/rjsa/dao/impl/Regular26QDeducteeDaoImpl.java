package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.Regular26QDeducteeDao;
import domain.in.rjsa.model.form.Regular26QDeductee;

@Repository("regular26QDeducteeDao")
public class Regular26QDeducteeDaoImpl  extends AbstractNewDao<Long, Regular26QDeductee> implements Regular26QDeducteeDao {

}
