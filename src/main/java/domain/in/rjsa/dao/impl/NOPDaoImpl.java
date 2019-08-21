package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.NOPDao;
import domain.in.rjsa.model.form.NOP;
@Repository("nopDao")
public class NOPDaoImpl extends AbstractNewDao<Long, NOP> implements NOPDao{

}
