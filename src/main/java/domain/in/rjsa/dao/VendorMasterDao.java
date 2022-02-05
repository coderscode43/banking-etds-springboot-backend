package domain.in.rjsa.dao;

import domain.in.rjsa.model.form.VendorMaster;

public interface VendorMasterDao extends DaoInterfaceForm<Long,  VendorMaster>{
	public VendorMaster getByKey(Long key) ;
}
