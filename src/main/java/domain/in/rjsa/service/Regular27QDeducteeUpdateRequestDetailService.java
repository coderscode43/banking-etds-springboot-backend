package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Regular27QDeducteeUpdateRequestDetail;

public interface Regular27QDeducteeUpdateRequestDetailService extends ServiceInterface<Long, Regular27QDeducteeUpdateRequestDetail> {
	public Regular27QDeducteeUpdateRequestDetail getByKey(Long id);
}
