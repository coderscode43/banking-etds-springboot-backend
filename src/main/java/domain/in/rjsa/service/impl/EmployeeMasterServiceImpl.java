package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.EmployeeMasterDao;
import domain.in.rjsa.model.form.EmployeeMaster;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.EmployeeMasterService;
@Transactional("transactionManager")
@Service("EmployeeMasterService")
public class EmployeeMasterServiceImpl extends AbstractServiceForm<Long, EmployeeMaster, EmployeeMasterDao>
implements EmployeeMasterService{
   
	@Autowired
	EmployeeMasterDao dao;
	
	
	@Override
	public EmployeeMaster getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public EmployeeMasterDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
