package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.PensionerSalary;

public interface PensionerSalaryService extends ServiceInterfaceFY<Long,PensionerSalary> {

	public PensionerSalary getByKey(Long id);

}
