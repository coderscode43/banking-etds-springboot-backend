package domain.in.rjsa.service;

import java.util.HashMap;

import domain.in.rjsa.model.fy.Regular26QDeductee;

public interface Regular26QDeducteeService extends ServiceInterfaceFY<Long,Regular26QDeductee> {

	public Regular26QDeductee getByKey(Long id);
	
	public Long findallCount(HashMap<String, Object> constrains);

	public void updateAllowed(Regular26QDeductee entity);
}
