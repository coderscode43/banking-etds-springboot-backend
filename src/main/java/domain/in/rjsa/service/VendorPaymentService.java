package domain.in.rjsa.service;

import domain.in.rjsa.model.form.VendorPayment;

public interface VendorPaymentService extends ServiceInterface<Long,VendorPayment>{

	public VendorPayment getByKey(Long id);

}
