package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.VendorLDC;

public interface VendorLDCService extends ServiceInterfaceFY<Long,VendorLDC>{

	VendorLDC getByKey(Long id);
}
