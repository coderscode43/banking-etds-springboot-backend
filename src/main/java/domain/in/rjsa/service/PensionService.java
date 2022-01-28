package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Pension;

public interface PensionService extends ServiceInterface<Long,Pension>{
	Pension getByKey(Long id);
}
