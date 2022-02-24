package domain.in.rjsa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.RemarkDao;
import domain.in.rjsa.model.fy.Remark;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.RemarkService;

@Transactional("transactionManager")
@Service("remarkService")
public class RemarkServiceImpl extends AbstractServiceFY<Long, Remark,RemarkDao> implements RemarkService{
	
	@Autowired
	RemarkDao dao;
	@Override
	public RemarkDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
