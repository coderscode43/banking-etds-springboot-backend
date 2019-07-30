package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Logs;

public interface LogsService extends ServiceInterface<Long,Logs>{

	public Logs getByKey(Long id);

}
