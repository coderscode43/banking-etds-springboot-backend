package domain.in.rjsa.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.DaoInterfaceTaxo;

public abstract class AbstractServiceTaxo<K extends Serializable, E, D extends DaoInterfaceTaxo<K, E>>
		implements ServiceInterfaceTaxo<K, E> {
	
	public static final ObjectMapper mapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	@Override
	public List<E> findAll(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		// TODO Auto-generated method stub
		return getPrimaryDao().findall(constrains, pageNo, noOfResult);
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

	@Override
	public void update(E entity) {
		// TODO Auto-generated method stub
		getPrimaryDao().update(entity);
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
	public List<String> ajax(String name, String term) {
		// TODO Auto-generated method stub
		return getPrimaryDao().ajax(name, term);
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
