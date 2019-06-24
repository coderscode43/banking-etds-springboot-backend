package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.PensionerDao;
import domain.in.rjsa.model.form.Pensioner;

@Repository("pensionerDao")
public class PensionerDaoImpl extends AbstractNewDao<Long, Pensioner> implements PensionerDao{

}
