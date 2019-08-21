package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.VendorSectionAmountDao;
import domain.in.rjsa.model.form.VendorSectionAmount;
@Repository("vendorSectionAmountDao")
public class VendorSectionAmountDaoImpl extends AbstractNewDao<Long, VendorSectionAmount> implements VendorSectionAmountDao{

}
