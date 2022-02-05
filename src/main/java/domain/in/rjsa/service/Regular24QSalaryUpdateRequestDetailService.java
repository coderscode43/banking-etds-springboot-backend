package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.Regular24QSalaryUpdateRequestDetail;

public interface Regular24QSalaryUpdateRequestDetailService
		extends ServiceInterfaceFY<Long, Regular24QSalaryUpdateRequestDetail> {
	public Regular24QSalaryUpdateRequestDetail getByKey(Long id);
}
