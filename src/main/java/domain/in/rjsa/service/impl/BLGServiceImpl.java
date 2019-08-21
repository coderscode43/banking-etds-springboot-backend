package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.BLGDao;
import domain.in.rjsa.model.form.BLG;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.BLGService;

@Transactional("transactionManager")
@Service("blgService")
public class BLGServiceImpl extends AbstractService<Long, BLG, BLGDao> implements BLGService{
 @Autowired
 BLGDao dao;
	@Override
	public BLGDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
