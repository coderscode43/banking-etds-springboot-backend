package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractTDSDao;
import domain.in.rjsa.dao.STATEMENTSTATUSDao;
import domain.in.rjsa.model.tds.STATEMENTSTATUS;

@Repository("STATEMENTSTATUSDao")
public class STATEMENTSTATUSDaoImpl extends AbstractTDSDao<Long, STATEMENTSTATUS> implements STATEMENTSTATUSDao{

	@Override
	public List<STATEMENTSTATUS> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long findallCount(HashMap<String, Object> constrains) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> ajax(String name, String term, HashMap<String, Object> constrain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getMaxValue(String name, Map<String, Object> propertyNameValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeValue(Map<String, Object> propertyNameValuesToUpdate, String operator,
			Map<String, Object> propertyNameValues) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<STATEMENTSTATUS> searchIn(HashSet set, String property) {
		// TODO Auto-generated method stub
		return null;
	}

}
