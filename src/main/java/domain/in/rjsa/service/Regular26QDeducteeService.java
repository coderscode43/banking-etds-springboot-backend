package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.Regular26QDeductee;

public interface Regular26QDeducteeService extends ServiceInterfaceFY<Long,Regular26QDeductee> {

	public Regular26QDeductee getByKey(Long id);
}
