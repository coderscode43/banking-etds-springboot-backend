package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Regular24QSalaryUpdateRequestDetail;

public interface Regular24QSalaryUpdateRequestDetailService
		extends ServiceInterface<Long, Regular24QSalaryUpdateRequestDetail> {
	public Regular24QSalaryUpdateRequestDetail getByKey(Long id);
}
