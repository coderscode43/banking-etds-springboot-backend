package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractFYDao;
import domain.in.rjsa.dao.TestingDao;
import domain.in.rjsa.model.fy.Testing;
@Repository("testingDao")
public class TestingDaoImpl extends AbstractFYDao<Long, Testing> implements TestingDao{

}
