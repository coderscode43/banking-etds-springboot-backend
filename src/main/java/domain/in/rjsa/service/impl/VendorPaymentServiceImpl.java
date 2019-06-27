package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.VendorPaymentDao;
import domain.in.rjsa.model.form.VendorPayment;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.VendorPaymentService;

@Transactional("transactionManager")
@Service("vendorPaymentService")
public class VendorPaymentServiceImpl extends AbstractService<Long, VendorPayment, VendorPaymentDao> implements VendorPaymentService{
	@Autowired
	VendorPaymentDao dao;
		@Override
		public VendorPaymentDao getPrimaryDao() {
			// TODO Auto-generated method stub
			return dao;
		}

}
