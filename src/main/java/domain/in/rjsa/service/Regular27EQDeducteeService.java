package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.Regular27EQDeductee;

public interface Regular27EQDeducteeService extends ServiceInterface<Long,Regular27EQDeductee> {
	public Regular27EQDeductee getByKey(Long id);
}
