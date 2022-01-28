package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.VendorPayment;

public interface VendorPaymentService extends ServiceInterface<Long,VendorPayment>{

	public VendorPayment getByKey(Long id);

}
