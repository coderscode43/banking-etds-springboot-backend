package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.PensionDao;
import domain.in.rjsa.model.form.Pension;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.PensionService;
@Transactional("transactionManager")
@Service("pensionService")
public class PensionServiceImpl  extends AbstractServiceForm<Long, Pension, PensionDao>
implements PensionService{
	@Autowired
	PensionDao dao;
	@Override
	public Pension getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public PensionDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
