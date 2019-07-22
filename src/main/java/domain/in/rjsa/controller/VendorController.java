package domain.in.rjsa.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonElement;


import domain.in.rjsa.model.form.Address;
import domain.in.rjsa.model.form.BankAccDetail;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.Employee;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Vendor;
import domain.in.rjsa.model.form.VendorLDC;
import domain.in.rjsa.model.form.VendorPayment;
import domain.in.rjsa.model.form.Zone;
import domain.in.rjsa.model.wrapper.VendorDetailWrapper;
import domain.in.rjsa.service.AddressService;
import domain.in.rjsa.service.BankAccDetailService;
import domain.in.rjsa.service.VendorLDCService;
import domain.in.rjsa.service.VendorPaymentService;
import domain.in.rjsa.service.VendorService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apivendor")
public class VendorController extends AbstractController<Long, Vendor, VendorService>{
	@Autowired
	VendorService service;
	@Autowired
	VendorLDCService vldcservice;
	@Autowired
	VendorPaymentService vpservice;
	@Autowired
	BankAccDetailService bService;
	@Autowired
	AddressService aService;
	
	@Autowired
	ApplicationCache applicationCache;
	
	@Override
	public VendorService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Vendor> getEntity() {
		// TODO Auto-generated method stub
		return Vendor.class;
	}
	
	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		VendorDetailWrapper ew = new VendorDetailWrapper();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		Branch b=applicationCache.getBranch(id);
		Vendor vendor = service.getByKey(id);
		ew.setVendor(vendor);
		LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
	//	constrains.put("clientId", l.getClientId());
		constrains.put("vendorId", id);
	
		List<VendorLDC> v = vldcservice.search(constrains,l.getClientId());		
		ew.setVldcs(v);
		
//		VendorLDC emp = vldcservice.getByKey(id);
//		ew.setVendorLDC(emp);
	//	constrains.remove("vendorId");
		if(b!=null)
		{
		constrains.put("branchId", b.getId());
		List<VendorPayment> vendorPay = vpservice.search(constrains,l.getClientId());		
		ew.setVpList(vendorPay);
		constrains.remove("branchId");
		}
		else
		{
			ew.setVendorPayment(new VendorPayment());
		}
		if(vendor.getBankId()!=null || vendor.getAddressId()!=null) {
			ew.setBank(bService.getByKey(vendor.getBankId()));
			ew.setAddress(aService.getByKey(vendor.getAddressId()));
		}else {
			ew.setBank(new BankAccDetail());
			ew.setAddress(new Address());
		}
		
		
		return ew;
	}
	
	
	@Override
	public void create(LinkedHashMap<String, Object> entity) {
		Gson gson = new Gson();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		if (entity.containsKey("clientId")) {
			entity.put("clientId", l.getClientId());
		}	
		JsonElement jsonElement = gson.toJsonTree(entity);
		VendorDetailWrapper vendor = gson.fromJson(jsonElement, VendorDetailWrapper.class);
		vendor.getAddress().setClientId(Long.valueOf( entity.get("clientId").toString()));
		vendor.getVendor().setClientId(Long.valueOf( entity.get("clientId").toString()));
		vendor.getBank().setClientId(Long.valueOf( entity.get("clientId").toString()));
		
		

		getService().saveEntity(vendor);

	}

}
