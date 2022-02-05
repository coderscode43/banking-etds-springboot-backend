package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

public interface DaoInterfaceTaxo<K, E> {

	public E getByKey(K key);
	public List<E> findall();
	public List<E> search(HashMap entity);
	public E uniqueSearch(HashMap entity);
	public List<E> findallWithConstrain(HashMap<String,Object> constrains, int pageNo, int noOfResult);
	public Long findallCount(HashMap<String, Object> constrains);
	public List<String> ajax(String name, String term, HashMap<String, Object> constrain) ;
	

}
