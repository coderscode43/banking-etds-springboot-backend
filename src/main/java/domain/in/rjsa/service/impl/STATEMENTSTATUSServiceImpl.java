package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.STATEMENTSTATUSDao;
import domain.in.rjsa.model.tds.STATEMENTSTATUS;
import domain.in.rjsa.service.AbstractTDSService;
import domain.in.rjsa.service.STATEMENTSTATUSService;

@Transactional("tdsTxManager")
@Service("STATEMENTSTATUSService")
public class STATEMENTSTATUSServiceImpl extends AbstractTDSService<Long, STATEMENTSTATUS, STATEMENTSTATUSDao> implements STATEMENTSTATUSService{
@Autowired 
STATEMENTSTATUSDao dao;
	@Override
	public STATEMENTSTATUSDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
