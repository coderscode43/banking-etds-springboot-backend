package domain.in.rjsa.service;


import domain.in.rjsa.model.fy.Regular27QDeductee;

public interface Regular27QDeducteeService extends ServiceInterfaceFY<Long,Regular27QDeductee> {
	 public Regular27QDeductee getByKey(Long id);

	public void updateAllowed(Regular27QDeductee entity);
}
