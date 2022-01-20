package domain.in.rjsa.service;

import domain.in.rjsa.model.form.EmployeeMaster;

public interface EmployeeMasterService extends ServiceInterface<Long,EmployeeMaster>{
	EmployeeMaster getByKey(Long id);
	
}
