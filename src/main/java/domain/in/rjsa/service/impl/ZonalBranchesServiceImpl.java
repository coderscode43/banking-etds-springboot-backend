package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

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

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

}
