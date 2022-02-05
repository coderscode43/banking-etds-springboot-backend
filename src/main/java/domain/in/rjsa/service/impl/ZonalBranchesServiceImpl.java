package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.ZonalBranchesDao;
import domain.in.rjsa.model.form.ZonalBranches;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.ZonalBranchesService;

@Transactional("transactionManager")
@Service("zonalBranchesService")
public class ZonalBranchesServiceImpl extends AbstractServiceForm<Long, ZonalBranches, ZonalBranchesDao> implements ZonalBranchesService{

	@Autowired
	ZonalBranchesDao dao;
	
	@Override
	public ZonalBranchesDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
