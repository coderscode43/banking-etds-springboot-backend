package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.BranchDao;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.BranchService;

@Transactional
@Service("branchService")
public class BranchServiceImpl extends AbstractService<Long, Branch, BranchDao> implements BranchService{
	@Autowired
	BranchDao dao;
	@Override
	public BranchDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Branch getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
