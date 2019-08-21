package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.NOPDao;
import domain.in.rjsa.model.form.NOP;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.NOPService;

@Transactional("transactionManager")
@Service("nopService")
public class NOPServiceImpl extends AbstractService<Long, NOP, NOPDao> implements NOPService{
@Autowired
NOPDao dao;
	@Override
	public NOPDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
