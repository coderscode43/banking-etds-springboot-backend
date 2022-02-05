package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.VendorPaymentDao;
import domain.in.rjsa.model.fy.VendorPayment;

@Repository("vendorPaymentDao")
public class VendorPaymentDaoImpl extends AbstractDaoFY<Long, VendorPayment> implements VendorPaymentDao {


}
