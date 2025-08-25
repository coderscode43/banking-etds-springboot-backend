package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public interface DaoInterfaceTaxo<K, E> {

	public E getByKey(K key);

	public List<E> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult);

	public List<E> search(HashMap<String, Object> map);

	public E uniqueSearch(HashMap<String, Object> map);

	public List<E> findallWithConstrain(HashMap<String, Object> constrains, int pageNo, int noOfResult);

	public Long findallCount(HashMap<String, Object> constrains);

	public List<String> ajax(String name, String term, HashMap<String, Object> constrain);

	public List<String> ajax(String name, String term);

	public Long getMaxValue(String name, Map<String, Object> propertyNameValues);

	public void changeValue(Map<String, Object> propertyNameValuesToUpdate, String operator,
			Map<String, Object> propertyNameValues);

	public List<E> searchIn(HashSet<?> set, String property);

	public List<E> searchExcel(HashMap map);

	public void persist(E entity);
	
	public void update(E entity);
	
	public void delete(E entity);
	
	public void deleteByKey(K key);

}
