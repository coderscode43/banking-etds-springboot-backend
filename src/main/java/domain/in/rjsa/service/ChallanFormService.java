package domain.in.rjsa.service;

import domain.in.rjsa.model.form.ChallanForm;

public interface ChallanFormService extends ServiceInterfaceForm<Long, ChallanForm> {
	public ChallanForm getByKey(Long id);

}
