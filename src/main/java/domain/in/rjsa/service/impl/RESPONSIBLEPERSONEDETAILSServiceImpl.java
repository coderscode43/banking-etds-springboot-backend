package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.RESPONSIBLEPERSONEDETAILSDao;
import domain.in.rjsa.model.tds.RESPONSIBLEPERSONEDETAILS;
import domain.in.rjsa.service.AbstractTDSService;
import domain.in.rjsa.service.RESPONSIBLEPERSONEDETAILSService;

@Transactional("tdsTxManager")
@Service("RESPONSIBLEPERSONEDETAILSService")
public class RESPONSIBLEPERSONEDETAILSServiceImpl extends AbstractTDSService<String, RESPONSIBLEPERSONEDETAILS, RESPONSIBLEPERSONEDETAILSDao> implements RESPONSIBLEPERSONEDETAILSService{
	@Autowired
	RESPONSIBLEPERSONEDETAILSDao dao;
	@Override
	public RESPONSIBLEPERSONEDETAILSDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	@Override
	public RESPONSIBLEPERSONEDETAILS getByKey(String tan) {
		// TODO Auto-generated method stub
		return dao.getByKey(tan);
	}


}
