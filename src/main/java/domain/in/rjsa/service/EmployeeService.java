package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Employee;
import domain.in.rjsa.model.wrapper.EmployeeDetailWrapper;
import domain.in.rjsa.model.wrapper.VendorDetailWrapper;

public interface EmployeeService extends ServiceInterface<Long,Employee> {

	Employee getByKey(Long id);

	public void saveEntity(EmployeeDetailWrapper employee);

}
