package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.STATEMENTSTATUSDao;
import domain.in.rjsa.model.STATEMENTSTATUS;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.STATEMENTSTATUSService;

@Transactional
@Service("STATEMENTSTATUSService")
public class STATEMENTSTATUSServiceImpl extends AbstractService<Long, STATEMENTSTATUS, STATEMENTSTATUSDao> implements STATEMENTSTATUSService{
@Autowired 
STATEMENTSTATUSDao dao;
	@Override
	public STATEMENTSTATUSDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
