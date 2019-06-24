package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.VendorDao;
import domain.in.rjsa.model.form.Vendor;

@Repository("vendorDao")
public class VendorDaoImpl extends AbstractNewDao<Long, Vendor> implements VendorDao{

}
