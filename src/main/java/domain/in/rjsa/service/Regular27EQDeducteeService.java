package domain.in.rjsa.service;


import domain.in.rjsa.model.fy.Regular27EQDeductee;

public interface Regular27EQDeducteeService extends ServiceInterfaceFY<Long,Regular27EQDeductee> {
	public Regular27EQDeductee getByKey(Long id);

	public void updateAllowed(Regular27EQDeductee entity);
}
