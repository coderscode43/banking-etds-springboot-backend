package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Regular26QDeducteeUpdateRequestDetail;

public interface Regular26QDeducteeUpdateRequestDetailService extends ServiceInterface<Long, Regular26QDeducteeUpdateRequestDetail> {
	public Regular26QDeducteeUpdateRequestDetail getByKey(Long id);
}
