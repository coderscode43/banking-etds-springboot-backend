package domain.in.rjsa.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular24QDeducteeDao;
import domain.in.rjsa.dao.RemarkDao;
import domain.in.rjsa.model.fy.Regular24QDeductee;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.Regular24QDeducteeService;

@Transactional("transactionManager")
@Service("regular24QDeducteeService")
public class Regular24QDeducteeServiceImpl extends AbstractServiceFY<Long, Regular24QDeductee, Regular24QDeducteeDao>
		implements Regular24QDeducteeService {

	@Autowired
	Regular24QDeducteeDao dao;
	@Autowired
	RemarkDao rDao;

	@Override
	public Regular24QDeducteeDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Regular24QDeductee getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	
	@Override
	public void save(Regular24QDeductee entity) {
		// TODO Auto-generated method stub
		getPrimaryDao().persist(entity);
		
	}
	
	
	

}
