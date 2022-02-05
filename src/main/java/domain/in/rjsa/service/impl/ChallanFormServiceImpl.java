package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.ChallanFormDao;
import domain.in.rjsa.model.form.ChallanForm;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.ChallanFormService;

@Transactional("transactionManager")
@Service("challanFormService")
public class ChallanFormServiceImpl extends AbstractServiceForm<Long, ChallanForm, ChallanFormDao>
		implements ChallanFormService {
	@Autowired
	ChallanFormDao dao;

	@Override
	public ChallanFormDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public ChallanForm getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
}
