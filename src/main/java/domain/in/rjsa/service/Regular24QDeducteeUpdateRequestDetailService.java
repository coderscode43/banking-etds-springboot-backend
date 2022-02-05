package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.Regular24QDeducteeUpdateRequestDetail;

public interface Regular24QDeducteeUpdateRequestDetailService extends ServiceInterfaceFY<Long, Regular24QDeducteeUpdateRequestDetail> {
	 public Regular24QDeducteeUpdateRequestDetail getByKey(Long id);
}
