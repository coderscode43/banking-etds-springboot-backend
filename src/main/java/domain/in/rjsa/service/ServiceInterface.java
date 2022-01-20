package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.form.FileDetail;

public interface ServiceInterface<K, E> {
	public void save(E entity);

	public void update(E entity);

	public void deleteT(K key);

	public List<E> findAll(HashMap<String, Object> constrains, int pageNo, int noOfResult);

	public Long findallCount(HashMap<String, Object> constrains);

	public List<E> search(HashMap map,Long clientId);

	public E uniqueSearch(HashMap map);

	public List<String> ajax(String name, String term, HashMap<String, Object> constrains);

	public void saveFile(FileDetail file, LinkedHashMap<String, Object> map, Class<E> entity);


//	List<E> search(HashMap map, Long clientId);
	


}
