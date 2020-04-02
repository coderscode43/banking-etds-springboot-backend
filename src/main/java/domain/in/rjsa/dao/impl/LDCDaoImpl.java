package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractTDSDao;
import domain.in.rjsa.dao.LDCDao;
import domain.in.rjsa.model.tds.LDC;

@Repository("lDCDao")
public class LDCDaoImpl extends AbstractTDSDao<Long, LDC> implements LDCDao{

}
