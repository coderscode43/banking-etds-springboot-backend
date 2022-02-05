package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.Regular26QDeducteeUpdateRequestDetail;

public interface Regular26QDeducteeUpdateRequestDetailService extends ServiceInterfaceFY<Long, Regular26QDeducteeUpdateRequestDetail> {
	public Regular26QDeducteeUpdateRequestDetail getByKey(Long id);
}
