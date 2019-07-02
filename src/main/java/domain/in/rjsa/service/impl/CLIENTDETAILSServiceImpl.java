package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.CLIENTDETAILSDao;
import domain.in.rjsa.model.tds.CLIENTDETAILS;
import domain.in.rjsa.service.AbstractTDSService;
import domain.in.rjsa.service.CLIENTDETAILSService;
@Transactional("tdsTxManager")
@Service("CLIENTDETAILSService")
public class CLIENTDETAILSServiceImpl extends AbstractTDSService<String, CLIENTDETAILS, CLIENTDETAILSDao> implements CLIENTDETAILSService{
@Autowired
CLIENTDETAILSDao dao;
	
	@Override
	public CLIENTDETAILSDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public CLIENTDETAILS getByKey(String tan) {
		// TODO Auto-generated method stub
		return dao.getByKey(tan);
	}

}
