package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.STATEMENTSTATUSDao;
import domain.in.rjsa.model.STATEMENTSTATUS;

@Repository("STATEMENTSTATUSDao")
public class STATEMENTSTATUSDaoImpl extends AbstractNewDao<Long, STATEMENTSTATUS> implements STATEMENTSTATUSDao{

}
