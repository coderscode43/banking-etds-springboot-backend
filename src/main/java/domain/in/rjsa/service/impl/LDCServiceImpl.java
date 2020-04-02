package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.LDCDao;
import domain.in.rjsa.model.tds.LDC;
import domain.in.rjsa.service.AbstractTDSService;
import domain.in.rjsa.service.LDCService;

@Transactional("tdsTxManager")
@Service("lDCService")
public class LDCServiceImpl extends AbstractTDSService<Long, LDC, LDCDao> implements LDCService{

	@Autowired
	LDCDao dao;
	
	@Override
	public LDCDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
