package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.Form15GHDao;
import domain.in.rjsa.model.form.Form15GH;

@Repository("form15GHDao")
public class Form15GHDaoImpl extends AbstractNewDao<Long, Form15GH> implements Form15GHDao{

}
