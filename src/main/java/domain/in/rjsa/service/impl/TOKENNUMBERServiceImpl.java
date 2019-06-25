package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.TOKENNUMBERDao;
import domain.in.rjsa.model.tds.TOKENNUMBER;
import domain.in.rjsa.service.AbstractTDSService;
import domain.in.rjsa.service.TOKENNUMBERService;

@Transactional("tdsTxManager")
@Service("TOKENNUMBERService")
public class TOKENNUMBERServiceImpl extends AbstractTDSService<Long, TOKENNUMBER, TOKENNUMBERDao> implements TOKENNUMBERService {

	@Autowired 
	TOKENNUMBERDao dao;
	public TOKENNUMBERDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
