package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.TRACESSLOGINDao;
import domain.in.rjsa.model.tds.TRACESSLOGIN;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.TRACESSLOGINService;
@Transactional("tdsTxManager")
@Service("TRACESSLOGINService")
public class TRACESSLOGINServiceImpl extends AbstractServiceTaxo<String, TRACESSLOGIN, TRACESSLOGINDao> implements TRACESSLOGINService{
@Autowired 
TRACESSLOGINDao dao;
	@Override
	public TRACESSLOGINDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public TRACESSLOGIN getByKey(String tan) {
		// TODO Auto-generated method stub
		return dao.getByKey(tan);
	}
	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
