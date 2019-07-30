package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.LogsJsonDao;
import domain.in.rjsa.model.form.LogsJson;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.LogsJsonService;

@Transactional("transactionManager")
@Service("logsJsonService")
public class LogsJsonServiceImpl extends AbstractService<Long, LogsJson, LogsJsonDao> implements LogsJsonService{
@Autowired
LogsJsonDao dao;
	@Override
	public LogsJsonDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public LogsJson getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
