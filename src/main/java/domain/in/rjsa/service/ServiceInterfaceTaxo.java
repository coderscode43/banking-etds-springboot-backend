package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface ServiceInterfaceTaxo<K, E> {

	public List<E> findAll(HashMap<String, Object> constrains, int pageNo, int noOfResult);

	public Long findallCount(HashMap<String, Object> constrains);

	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage);

	public E uniqueSearch(HashMap map);

	public List<String> ajax(String name, String term);

	public String createUserExcel(LinkedHashMap<String, Object> map);

	public List<E> search(HashMap map);

	public void addData(String json);
	
	public void update(E entity);
}
