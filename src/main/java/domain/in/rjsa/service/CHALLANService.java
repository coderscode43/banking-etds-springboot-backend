package domain.in.rjsa.service;

import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.tds.CHALLAN;

public interface CHALLANService extends ServiceInterfaceTaxo<String, CHALLAN>{
	public CHALLAN getByKey(Long id);
	
	public Long findSearchCount(LinkedHashMap<String, Object> map);

	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage);

	public	List<String> ajax(String name, String term);
	 
}