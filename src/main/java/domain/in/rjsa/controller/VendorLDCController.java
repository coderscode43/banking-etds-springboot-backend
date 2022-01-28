package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.VendorLDC;
import domain.in.rjsa.model.wrapper.VendorDetailWrapper;
import domain.in.rjsa.service.VendorLDCService;

@Controller
@RequestMapping("/apivendorLDC1")
public class VendorLDCController extends AbstractController<Long, VendorLDC, VendorLDCService>{
@Autowired
VendorLDCService service;
	@Override
	public VendorLDCService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<VendorLDC> getEntity() {
		// TODO Auto-generated method stub
		return VendorLDC.class;
	}
	
	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		VendorDetailWrapper ew = new VendorDetailWrapper();

		VendorLDC vld = service.getByKey(id);
		ew.setVendorLDC(vld);
		
		
		
		return ew;
	}


}
