package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.PANITR2324Dao;
import domain.in.rjsa.model.tds.PANITR2324;
import domain.in.rjsa.service.PANITR2324Service;

@Repository("PANITR2324Dao")
public class PANITR2324DaoImpl extends AbstractDaoFY<Long, PANITR2324> implements PANITR2324Dao {
	@Autowired
	PANITR2324Dao dao;

	public PANITR2324 getByKey(Long key) {
		// TODO Auto-generated method stub
		return dao.getByKey(key);
	}

	@Override
	public List<PANITR2324> findallWithConstrain(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		// TODO Auto-generated method stub
		return dao.findallWithConstrain(constrains, pageNo, noOfResult);
	}

	@Override
	public List<String> ajax(String name, String term) {
		// TODO Auto-generated method stub
		return dao.ajax(name, term);
	}

	@Override
	public List<PANITR2324> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return dao.searchExcel(map);
	}
	
}
