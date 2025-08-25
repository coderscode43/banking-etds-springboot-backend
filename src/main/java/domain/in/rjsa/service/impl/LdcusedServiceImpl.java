package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import domain.in.rjsa.dao.LdcusedDao;
import domain.in.rjsa.model.fy.Ldcused;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.LdcusedService;

public class LdcusedServiceImpl extends AbstractServiceFY<String, Ldcused, LdcusedDao> implements LdcusedService{

	@Autowired
	LdcusedDao dao;
	
	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addData(String json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LdcusedDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
