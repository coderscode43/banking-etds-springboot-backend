package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.tds.LDC;

public interface LDCService extends ServiceInterfaceTaxo<String, LDC>{
	public LDC getByKey(String tan);
	
	public Long findallCount(HashMap<String, Object> constrains);

	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage);
}
