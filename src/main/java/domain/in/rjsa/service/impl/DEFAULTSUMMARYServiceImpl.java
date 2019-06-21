package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.DEFAULTSUMMARYDao;
import domain.in.rjsa.model.DEFAULTSUMMARY;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.DEFAULTSUMMARYService;

@Transactional
@Service("DEFAULTSUMMARYService")
public class DEFAULTSUMMARYServiceImpl extends AbstractService<Long, DEFAULTSUMMARY, DEFAULTSUMMARYDao> implements DEFAULTSUMMARYService{
@Autowired
DEFAULTSUMMARYDao dao;
	@Override
	public DEFAULTSUMMARYDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
