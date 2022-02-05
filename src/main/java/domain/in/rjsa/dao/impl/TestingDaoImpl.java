package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.TestingDao;
import domain.in.rjsa.model.fy.Testing;
@Repository("testingDao")
public class TestingDaoImpl extends AbstractDaoFY<Long, Testing> implements TestingDao{

}
