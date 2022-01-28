package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.Regular27QDeductee;

public interface Regular27QDeducteeService extends ServiceInterface<Long,Regular27QDeductee> {
	 public Regular27QDeductee getByKey(Long id);
}
