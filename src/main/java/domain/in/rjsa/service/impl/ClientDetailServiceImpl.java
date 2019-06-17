package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.ClientDetailDao;
import domain.in.rjsa.model.ClientDetail;
import domain.in.rjsa.service.ClientDetailService;
@Transactional
@Service("clientDetailService")
public class ClientDetailServiceImpl implements ClientDetailService{
	@Autowired
	ClientDetailDao dao;
	public ClientDetailDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public ClientDetail getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
