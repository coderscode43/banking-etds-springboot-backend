package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.tds.LDC;

public interface LDCDao extends DaoInterfaceTaxo<String, LDC>{
	public LDC getByKey(String key) ;
	List<LDC> search(HashMap entity, int pageNo, int noOfResult);
	public Long findallCount(HashMap<String, Object> constrains);
}
