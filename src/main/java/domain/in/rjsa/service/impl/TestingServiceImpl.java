package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.TestingDao;
import domain.in.rjsa.model.fy.Testing;
import domain.in.rjsa.service.AbstractFYService;
import domain.in.rjsa.service.TestingService;

@Transactional("fyTxManager")
@Service("testingService")
public class TestingServiceImpl extends AbstractFYService<Long, Testing, TestingDao> implements TestingService{
	@Autowired 
	TestingDao dao;
	@Override
	public TestingDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	@Override
	public Testing getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, Long clientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
