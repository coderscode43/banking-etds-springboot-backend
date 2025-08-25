package domain.in.rjsa.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.DaoInterfaceFY;

public abstract class AbstractServiceFY<K extends Serializable, E, D extends DaoInterfaceFY<K, E>>
		implements ServiceInterfaceFY<K, E> {
	
	public static final ObjectMapper mapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//	@Autowired
//	FileDetailDao fDao;
	
	
	@Override
	public void save(E entity) {
		// TODO Auto-generated method stub
		getPrimaryDao().persist(entity);
		
	}

	@Override
	public void update(E entity) {
		// TODO Auto-generated method stub
		getPrimaryDao().update(entity);
	}
	

	@Override
	public void deleteT(K key) {
		// TODO Auto-generated method stub
		getPrimaryDao().deleteByKey(key);
	}

	@Override
	public List<E> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		// TODO Auto-generated method stub
		return getPrimaryDao().findall(constrains, pageNo, noOfResult);
	}
	@Override
	public List<E> findForm(HashMap<String, Object> constrains, int pageNo, int noOfResult,String type) {
		// TODO Auto-generated method stub
		return getPrimaryDao().findForm(constrains, pageNo, noOfResult,type);
	}

	@Override
	public Long findallCount(HashMap<String, Object> constrains) {
		// TODO Auto-generated method stub
		return getPrimaryDao().findallCount(constrains);
	}

	public List<E> search(HashMap map) {
		// TODO Auto-generated method stub
		return getPrimaryDao().search(map);
	}
	
	public List<E> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return getPrimaryDao().searchExcel(map);
	}


	@Override
	public E uniqueSearch(HashMap map) {
		// TODO Auto-generated method stub
		return (E) getPrimaryDao().uniqueSearch(map);
	}

	@Override
	public List<String> ajax(String name, String term, HashMap<String, Object> constrains) {
		// TODO Auto-generated method stub
		return getPrimaryDao().ajax(name, term, constrains);
	}
	
	public abstract D getPrimaryDao();

//	@Override
//	public void saveFile(FileDetail file, LinkedHashMap<String, Object> map, Class<E> entity) {
//		// TODO Auto-generated method stub
//		fDao.persist(file);
//		map.put("fileId", file.getId());
//		map.put("employeeId", file.getEmployeeId());
//		map.put("clientId", file.getClientId());
//		Gson gson = new Gson();
//		JsonElement jsonElement = gson.toJsonTree(map);
//		E ob = gson.fromJson(jsonElement,entity );
//		save(ob);
//	}
//	@Override
//	public void updateFile(FileDetail file, E entity) {
//		// TODO Auto-generated method stub
//		fDao.update(file);
//		update(entity);
//		
//	}
	
	

}
