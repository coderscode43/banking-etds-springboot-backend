package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.GH15RETURNSTATUSDao;
import domain.in.rjsa.model.tds.GH15RETURNSTATUS;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.GH15RETURNSTATUSService;
@Transactional("tdsTxManager")
@Service("GH15RETURNSTATUSService")
public class GH15RETURNSTATUSServiceImpl extends AbstractServiceTaxo<Long, GH15RETURNSTATUS, GH15RETURNSTATUSDao> implements GH15RETURNSTATUSService {

	
	@Autowired 
	GH15RETURNSTATUSDao dao;
	@Override
	public GH15RETURNSTATUSDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
