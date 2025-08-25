package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.tds.CHALLAN;

public interface CHALLANService extends ServiceInterfaceTaxo<String, CHALLAN>{
	public CHALLAN getByKey(String CIN);
	
	public Long findallCount(HashMap<String, Object> constrains);

	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage);

	public	List<String> ajax(String name, String term);

	 
}