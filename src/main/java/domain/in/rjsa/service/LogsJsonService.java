package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.LogsJson;

public interface LogsJsonService extends ServiceInterfaceFY<Long,LogsJson>{

	public LogsJson getByKey(Long id);

}
