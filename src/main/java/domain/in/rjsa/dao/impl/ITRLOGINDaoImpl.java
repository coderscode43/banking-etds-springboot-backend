package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.ITRLOGINDao;
import domain.in.rjsa.model.tds.ITRLOGIN;

@Repository("itrLoginDao")
public class ITRLOGINDaoImpl extends AbstractDaoFY<Long, ITRLOGIN> implements ITRLOGINDao {
	
	@Autowired 
	ITRLOGINDao dao;

	public ITRLOGIN getByKey(Long key) {
		// TODO Auto-generated method stub
		return dao.getByKey(key);
	}

	@Override
	public List<ITRLOGIN> findallWithConstrain(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		// TODO Auto-generated method stub
		return dao.findallWithConstrain(constrains, pageNo, noOfResult);
	}

	@Override
	public List<String> ajax(String name, String term) {
		// TODO Auto-generated method stub
		return dao.ajax(name, term);
	}

	@Override
	public List<ITRLOGIN> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return dao.searchExcel(map);
	}


	
}
