package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Regular24QDeductee;

public interface Regular24QDeducteeService extends ServiceInterface<Long, Regular24QDeductee> {
	 public Regular24QDeductee getByKey(Long id);
}
