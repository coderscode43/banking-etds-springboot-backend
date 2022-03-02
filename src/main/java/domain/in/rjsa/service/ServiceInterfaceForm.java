package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.List;

public interface ServiceInterfaceForm<K, E> {
	public void save(E entity);

	public void update(E entity);

	public void deleteT(K key);

	public List<E> findAll(HashMap<String, Object> constrains, int pageNo, int noOfResult);

	public Long findallCount(HashMap<String, Object> constrains);

	public List<E> search(HashMap map);

	public E uniqueSearch(HashMap map);

	public List<String> ajax(String name, String term);

	public E getByKey(K key);

	

//	List<E> search(HashMap map, Long clientId);
	


}
