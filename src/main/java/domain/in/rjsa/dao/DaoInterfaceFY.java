package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public interface DaoInterfaceFY<K, E> {

	public E getByKey(K key);
	public List<E> findall(HashMap<String,Object> constrains, int pageNo, int noOfResult);
	public Long findallCount(HashMap<String,Object> constrains);
	public void persist(E entity);
	public void delete(E entity);
	public void deleteByKey(K key);
	public void update(E entity);
	public List<E> search(HashMap map);
	public E uniqueSearch(HashMap map);
	public List<String> ajax(String name, String term,HashMap<String, Object>constrain);
	public Long getMaxValue(String name,Map<String, Object> propertyNameValues);
	public void changeValue(Map<String, Object> propertyNameValuesToUpdate,String operator, Map<String, Object> propertyNameValues);
	public List<E> searchIn(HashSet set,String property);
}
