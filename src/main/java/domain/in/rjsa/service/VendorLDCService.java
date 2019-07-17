package domain.in.rjsa.service;

import domain.in.rjsa.model.form.VendorLDC;

public interface VendorLDCService extends ServiceInterface<Long,VendorLDC>{

	VendorLDC getByKey(Long id);
}
