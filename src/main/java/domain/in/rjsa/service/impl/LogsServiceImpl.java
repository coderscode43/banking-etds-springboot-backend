package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.LogsDao;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.LogsService;

@Transactional("transactionManager")
@Service("logsService")
public class LogsServiceImpl extends AbstractServiceForm<Long, Logs, LogsDao> implements LogsService{
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
	@Override
	public List<String> ajax(String name, String term) {
		// TODO Auto-generated method stub
		return dao.ajax(name, term);
	}
	@Override
	public Long findSearchCount(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findSearchCount(map);
	}
	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}
}
