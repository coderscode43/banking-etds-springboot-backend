package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.dao.AddressDao;
import domain.in.rjsa.dao.BankAccDetailDao;
import domain.in.rjsa.dao.VendorDao;
import domain.in.rjsa.model.form.Vendor;
import domain.in.rjsa.model.wrapper.VendorDetailWrapper;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.VendorService;

@Transactional("transactionManager")
@Service("vendorService")
public class VendorServiceImpl extends AbstractService<Long, Vendor, VendorDao> implements VendorService{

	@Autowired
	VendorDao dao;
	
	@Autowired
	AddressDao adao;
	
	@Autowired
	BankAccDetailDao bdao;
	
	@Override
	public VendorDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Vendor getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
	
	@Override
	public void saveEntity(VendorDetailWrapper vendor) {
	
		adao.persist(vendor.getAddress());
		bdao.persist(vendor.getBank());
		vendor.getVendor().setBankId(vendor.getBank().getId());
		vendor.getVendor().setAddressId(vendor.getAddress().getId());
		save(vendor.getVendor());
	}

}
