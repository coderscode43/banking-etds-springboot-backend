package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.tds.CHALLAN;

public interface CHALLANDao extends DaoInterfaceTaxo<String,  CHALLAN> {
	public CHALLAN getByKey(Long key) ;
	public Long findSearchCount(LinkedHashMap<String, Object> map);
	List<CHALLAN> search(HashMap entity, int pageNo, int noOfResult);
}
