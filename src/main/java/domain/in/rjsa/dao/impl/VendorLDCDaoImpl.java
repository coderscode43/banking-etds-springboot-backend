package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.VendorLDCDao;
import domain.in.rjsa.model.form.VendorLDC;

@Repository("vendorLDCDao")
public class VendorLDCDaoImpl extends AbstractNewDao<Long, VendorLDC> implements VendorLDCDao{

}
