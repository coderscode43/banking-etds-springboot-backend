package domain.in.rjsa.service;

import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.tds.STATEMENTSTATUS;

public interface STATEMENTSTATUSService extends ServiceInterfaceTaxo<Long,STATEMENTSTATUS>{
	public STATEMENTSTATUS getByKey(Long id);
	
	public Long findSearchCount(LinkedHashMap<String, Object> map);

	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage);

	
}
