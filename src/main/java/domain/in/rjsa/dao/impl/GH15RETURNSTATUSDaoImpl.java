package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractTDSDao;
import domain.in.rjsa.dao.GH15RETURNSTATUSDao;
import domain.in.rjsa.model.tds.GH15RETURNSTATUS;

@Repository("GH15RETURNSTATUSDao")
public class GH15RETURNSTATUSDaoImpl extends AbstractTDSDao<Long, GH15RETURNSTATUS> implements GH15RETURNSTATUSDao {

}
