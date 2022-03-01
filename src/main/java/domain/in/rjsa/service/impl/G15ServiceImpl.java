package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.G15Dao;
import domain.in.rjsa.model.fy.G15;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.G15Service;

@Transactional("transactionManager")
@Service("G15Service")
public class G15ServiceImpl extends AbstractServiceFY<Long, G15, G15Dao>
implements G15Service {

	@Autowired
	G15Dao dao;

	@Override
	public G15Dao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public G15 getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
}
