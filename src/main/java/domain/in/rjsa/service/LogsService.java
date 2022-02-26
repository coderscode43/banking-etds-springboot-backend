package domain.in.rjsa.service;

import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.fy.Logs;

public interface LogsService extends ServiceInterfaceForm<Long,Logs>{

	public Logs getByKey(Long id);
	
	public Long findSearchCount(LinkedHashMap<String, Object> map);

	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage);
}
