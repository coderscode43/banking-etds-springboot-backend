package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.VendorLDCDao;
import domain.in.rjsa.model.fy.VendorLDC;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.VendorLDCService;

@Transactional("transactionManager")
@Service("vendorLDCService")
public class VendorLDCServiceImpl extends AbstractService<Long, VendorLDC, VendorLDCDao> implements VendorLDCService{
@Autowired
VendorLDCDao dao;
	@Override
	public VendorLDCDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public VendorLDC getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
}
