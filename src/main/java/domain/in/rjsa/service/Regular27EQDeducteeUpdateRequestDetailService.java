package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.Regular27EQDeducteeUpdateRequestDetail;

public interface Regular27EQDeducteeUpdateRequestDetailService extends ServiceInterface<Long, Regular27EQDeducteeUpdateRequestDetail> {
	public Regular27EQDeducteeUpdateRequestDetail getByKey(Long id);
}
