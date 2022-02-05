	package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.DEFAULTSUMMARYDao;
import domain.in.rjsa.model.tds.DEFAULTSUMMARY;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.DEFAULTSUMMARYService;

@Transactional("tdsTxManager")
@Service("DEFAULTSUMMARYService")
public class DEFAULTSUMMARYServiceImpl extends AbstractServiceTaxo<Long, DEFAULTSUMMARY, DEFAULTSUMMARYDao> implements DEFAULTSUMMARYService{
@Autowired
DEFAULTSUMMARYDao dao;
	@Override
	public DEFAULTSUMMARYDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
