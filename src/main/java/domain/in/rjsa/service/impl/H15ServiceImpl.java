package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.H15Dao;
import domain.in.rjsa.model.fy.H15;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.H15Service;

@Transactional("transactionManager")
@Service("H15Service")
public class H15ServiceImpl extends AbstractServiceFY<Long, H15, H15Dao>
implements H15Service {

	@Autowired
	H15Dao dao;

	@Override
	public H15Dao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public H15 getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
