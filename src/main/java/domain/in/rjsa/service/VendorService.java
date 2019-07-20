package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.Vendor;
import domain.in.rjsa.model.wrapper.VendorDetailWrapper;

public interface VendorService extends ServiceInterface<Long,Vendor>{

	
	public void saveEntity(VendorDetailWrapper vendor);
	Vendor getByKey(Long id);

}
