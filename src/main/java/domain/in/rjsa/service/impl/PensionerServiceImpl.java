package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.PensionerDao;
import domain.in.rjsa.model.form.Pensioner;
import domain.in.rjsa.model.form.Vendor;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.PensionerService;

@Transactional("transactionManager")
@Service("pensionerService")
public class PensionerServiceImpl extends AbstractService<Long, Pensioner, PensionerDao> implements PensionerService{
@Autowired
PensionerDao dao;
	@Override
	public PensionerDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Pensioner getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
