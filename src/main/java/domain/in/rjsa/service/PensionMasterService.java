package domain.in.rjsa.service;

import domain.in.rjsa.model.form.PensionMaster;

public interface PensionMasterService extends ServiceInterface<Long,PensionMaster>{
	PensionMaster getByKey(Long id);
}
