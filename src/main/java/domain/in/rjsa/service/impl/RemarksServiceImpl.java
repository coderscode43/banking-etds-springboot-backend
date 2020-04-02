package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.RemarksDao;
import domain.in.rjsa.model.form.Remarks;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.RemarksService;

@Transactional("transactionManager")
@Service("remarksService")
public class RemarksServiceImpl extends AbstractService<Long, Remarks, RemarksDao> implements RemarksService{
	
	@Autowired
	RemarksDao dao;
	@Override
	public RemarksDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Remarks getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
}
