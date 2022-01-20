package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.VendorMasterDao;
import domain.in.rjsa.model.form.VendorMaster;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.VendorMasterService;
@Transactional("transactionManager")
@Service("vendorMasterService")
public class VendorMasterServiceImpl extends AbstractService<Long, VendorMaster, VendorMasterDao>
implements VendorMasterService{
	@Autowired
	VendorMasterDao dao;

	@Override
	public VendorMaster getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public VendorMasterDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
}
