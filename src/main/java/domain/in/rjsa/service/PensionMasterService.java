package domain.in.rjsa.service;

import domain.in.rjsa.model.form.PensionMaster;

public interface PensionMasterService extends ServiceInterfaceForm<Long,PensionMaster>{
	PensionMaster getByKey(Long id);
}
