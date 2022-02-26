package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.tds.STATEMENTSTATUS;

public interface STATEMENTSTATUSDao extends DaoInterfaceTaxo<Long,  STATEMENTSTATUS>{
	
	public STATEMENTSTATUS getByKey(Long key) ;

	public Long findSearchCount(LinkedHashMap<String, Object> map);

	List<STATEMENTSTATUS> search(HashMap entity, int pageNo, int noOfResult);

}
