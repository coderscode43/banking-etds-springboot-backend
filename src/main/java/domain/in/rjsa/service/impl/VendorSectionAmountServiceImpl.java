package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.VendorSectionAmountDao;
import domain.in.rjsa.model.form.VendorSectionAmount;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.VendorSectionAmountService;

@Transactional("transactionManager")
@Service("vendorSectionAmountService")
public class VendorSectionAmountServiceImpl extends AbstractService<Long, VendorSectionAmount, VendorSectionAmountDao> implements VendorSectionAmountService{
@Autowired
VendorSectionAmountDao dao;
	@Override
	public VendorSectionAmountDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
