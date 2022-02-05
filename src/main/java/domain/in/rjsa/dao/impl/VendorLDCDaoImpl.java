package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.VendorLDCDao;
import domain.in.rjsa.model.fy.VendorLDC;

@Repository("vendorLDCDao")
public class VendorLDCDaoImpl extends AbstractDaoFY<Long, VendorLDC> implements VendorLDCDao{

}
