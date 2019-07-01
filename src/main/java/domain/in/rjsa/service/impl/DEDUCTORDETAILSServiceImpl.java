package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.DEDUCTORDETAILSDao;
import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
import domain.in.rjsa.service.AbstractTDSService;
import domain.in.rjsa.service.DEDUCTORDETAILSService;

@Transactional("tdsTxManager")
@Service("DEDUCTORDETAILSService")
public class DEDUCTORDETAILSServiceImpl extends AbstractTDSService<String, DEDUCTORDETAILS, DEDUCTORDETAILSDao> implements DEDUCTORDETAILSService{
@Autowired
DEDUCTORDETAILSDao dao;
	@Override
	public DEDUCTORDETAILSDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public DEDUCTORDETAILS getByKey(String tan) {
		// TODO Auto-generated method stub
		return dao.getByKey(tan);
	}

}
