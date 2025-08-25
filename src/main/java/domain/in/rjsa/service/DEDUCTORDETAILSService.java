package domain.in.rjsa.service;

import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.tds.DEDUCTORDETAILS;

public interface DEDUCTORDETAILSService extends ServiceInterfaceTaxo<Long, DEDUCTORDETAILS>{
public DEDUCTORDETAILS getByKey(Long id);
	
	public Long findSearchCount(LinkedHashMap<String, Object> map);

	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage);
}
