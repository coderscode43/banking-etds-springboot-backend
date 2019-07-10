package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.RequestCorrectionDao;
import domain.in.rjsa.model.form.RequestCorrection; 
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.RequestCorrectionService;

@Transactional("transactionManager")
@Service("requestCorrectionService")
public class RequestCorrectionServiceImpl extends AbstractService<Long, RequestCorrection, RequestCorrectionDao> implements RequestCorrectionService{
	@Autowired
	RequestCorrectionDao dao;
	@Override
	public RequestCorrectionDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public RequestCorrection getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
