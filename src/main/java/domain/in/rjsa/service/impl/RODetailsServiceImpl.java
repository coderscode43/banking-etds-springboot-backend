package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.RODetailsDao;
import domain.in.rjsa.model.form.RODetails;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.RODetailsService;

@Transactional("transactionManager")
@Service("RODetailsService")
public class RODetailsServiceImpl extends AbstractServiceForm<Long, RODetails, RODetailsDao>
implements RODetailsService{
	@Autowired
	RODetailsDao dao;
	
	@Override
	public RODetails getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public RODetailsDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
