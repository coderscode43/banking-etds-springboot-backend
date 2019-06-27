package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Employee;
import domain.in.rjsa.model.form.Vendor;

public interface EmployeeService extends ServiceInterface<Long,Employee> {

	Employee getByKey(Long id);

}
