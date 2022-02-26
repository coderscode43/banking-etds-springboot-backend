package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.fy.Logs;

public interface LogsDao extends DaoInterfaceForm<Long,  Logs>{
	public Logs getByKey(Long key) ;
	public Long findSearchCount(LinkedHashMap<String, Object> map);
	List<Logs> search(HashMap entity, int pageNo, int noOfResult);
}
