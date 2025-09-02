package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface ServiceInterfaceFY<K, E> {

	public void save(E entity);

	public void update(E entity);

	public void deleteT(K key);

	public Long findallCount(HashMap<String, Object> constrains);

	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage);

	public E uniqueSearch(HashMap map);

	public List<String> ajax(String name, String term, HashMap<String, Object> constrains);

	public List<E> findForm(HashMap<String, Object> constrains, int pageNo, int noOfResult, String type);

	public List<E> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult);

	public String createUserExcel(LinkedHashMap<String, Object> map);

	public List<E> search(HashMap map);
	
	public void addData(String json);

//	List<E> search(HashMap map, Long clientId);

}
