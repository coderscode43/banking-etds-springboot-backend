package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.LogsDao;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.LogsService;

@Transactional("transactionManager")
@Service("logsService")
public class LogsServiceImpl extends AbstractService<Long, Logs, LogsDao> implements LogsService{
@Autowired
LogsDao dao;
	@Override
	public LogsDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Logs getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
