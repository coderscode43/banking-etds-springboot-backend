package domain.in.rjsa.service;

import domain.in.rjsa.model.form.VendorMaster;

public interface VendorMasterService extends ServiceInterface<Long,VendorMaster>{
	VendorMaster getByKey(Long id);
}
