package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractTDSDao;
import domain.in.rjsa.dao.TOKENNUMBERDao;
import domain.in.rjsa.model.tds.TOKENNUMBER;	
@Repository("TOKENNUMBERDao")
public class TOKENNUMBERDaoImpl extends AbstractTDSDao<Long, TOKENNUMBER> implements TOKENNUMBERDao {

}
