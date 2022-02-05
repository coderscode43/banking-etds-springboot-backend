package domain.in.rjsa.dao;

import domain.in.rjsa.model.form.EmployeeMaster;

public interface EmployeeMasterDao extends DaoInterfaceForm<Long,  EmployeeMaster>{
	public EmployeeMaster getByKey(Long key) ;
}
