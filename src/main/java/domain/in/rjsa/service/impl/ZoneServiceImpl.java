package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.ZoneDao;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.Zone;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.ZoneService;

@Transactional("transactionManager")
@Service("zoneService")
public class ZoneServiceImpl extends AbstractService<Long, Zone, ZoneDao> implements ZoneService{
@Autowired
ZoneDao dao;
	@Override
	public ZoneDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	@Override
	public Zone getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
}
