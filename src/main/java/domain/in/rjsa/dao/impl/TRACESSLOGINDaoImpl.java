package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractTDSDao;
import domain.in.rjsa.dao.TRACESSLOGINDao;
import domain.in.rjsa.model.tds.TRACESSLOGIN;

@Repository("TRACESSLOGINDao")
public class TRACESSLOGINDaoImpl extends AbstractTDSDao<String, TRACESSLOGIN> implements TRACESSLOGINDao{

}
