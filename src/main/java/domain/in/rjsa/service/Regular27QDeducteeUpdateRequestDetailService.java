package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.Regular27QDeducteeUpdateRequestDetail;

public interface Regular27QDeducteeUpdateRequestDetailService extends ServiceInterfaceFY<Long, Regular27QDeducteeUpdateRequestDetail> {
	public Regular27QDeducteeUpdateRequestDetail getByKey(Long id);
}
