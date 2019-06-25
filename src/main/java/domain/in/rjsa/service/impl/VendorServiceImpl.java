package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.VendorDao;
import domain.in.rjsa.dao.ZoneDao;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.Vendor;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.VendorService;

@Transactional("transactionManager")
@Service("vendorService")
public class VendorServiceImpl extends AbstractService<Long, Vendor, VendorDao> implements VendorService{

	@Autowired
	VendorDao dao;
	@Override
	public VendorDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Vendor getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
