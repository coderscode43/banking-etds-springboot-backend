package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Regular24QDeducteeUpdateRequestDetail;

public interface Regular24QDeducteeUpdateRequestDetailService extends ServiceInterface<Long, Regular24QDeducteeUpdateRequestDetail> {
	 public Regular24QDeducteeUpdateRequestDetail getByKey(Long id);
}
