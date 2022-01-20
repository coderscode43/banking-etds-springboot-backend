package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular24QDeducteeDefualtDao;
import domain.in.rjsa.model.form.Regular24QDeducteeDefualt;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular24QDeducteeDefualtService;

@Transactional("transactionManager")
@Service("regular24QDeducteeDefualtService")
public class Regular24QDeducteeDefualtServiceImpl extends AbstractService<Long, Regular24QDeducteeDefualt, Regular24QDeducteeDefualtDao>
		implements Regular24QDeducteeDefualtService {

	@Autowired
	Regular24QDeducteeDefualtDao dao;

	@Override
	public Regular24QDeducteeDefualtDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Regular24QDeducteeDefualt getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
