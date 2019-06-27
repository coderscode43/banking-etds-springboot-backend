package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.VendorPaymentDao;
import domain.in.rjsa.model.form.VendorPayment;

@Repository("vendorPaymentDao")
public class VendorPaymentDaoImpl extends AbstractNewDao<Long, VendorPayment> implements VendorPaymentDao{

}
