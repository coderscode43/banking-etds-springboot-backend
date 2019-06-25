package domain.in.rjsa.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.BankAccDetail;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Vendor;
import domain.in.rjsa.model.form.VendorLDC;
import domain.in.rjsa.model.wrapper.VendorDetailWrapper;
import domain.in.rjsa.service.BankAccDetailService;
import domain.in.rjsa.service.VendorLDCService;
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
	BankAccDetailService bService;
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
       
		LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
		constrains.put("clientId", l.getClientId());
		constrains.put("vendorId", id);
		List<VendorLDC> v = vldcservice.search(constrains);
		
		ew.setVldcs(v);
		Vendor vendor = service.getByKey(id);
		ew.setVendor(vendor);
		if(vendor.getBankId()!=null) {
			ew.setBank(bService.getByKey(vendor.getBankId()));
		}else {
			ew.setBank(new BankAccDetail());
		}
		
		
		return ew;
	}

}
