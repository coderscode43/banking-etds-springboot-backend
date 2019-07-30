package domain.in.rjsa.service;

import domain.in.rjsa.model.form.LogsJson;

public interface LogsJsonService extends ServiceInterface<Long,LogsJson>{

	public LogsJson getByKey(Long id);

}
