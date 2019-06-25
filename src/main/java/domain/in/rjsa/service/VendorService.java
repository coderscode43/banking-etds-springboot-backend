package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.Vendor;

public interface VendorService extends ServiceInterface<Long,Vendor>{

	

	Vendor getByKey(Long id);

}
