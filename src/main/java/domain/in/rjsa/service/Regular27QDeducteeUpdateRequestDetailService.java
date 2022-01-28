package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.Regular27QDeducteeUpdateRequestDetail;

public interface Regular27QDeducteeUpdateRequestDetailService extends ServiceInterface<Long, Regular27QDeducteeUpdateRequestDetail> {
	public Regular27QDeducteeUpdateRequestDetail getByKey(Long id);
}
