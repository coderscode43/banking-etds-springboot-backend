package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Regular24Q4Salary;
import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
import domain.in.rjsa.model.wrapper.DeductorDetailWrapper;
import domain.in.rjsa.service.DEDUCTORDETAILSService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apiDEDUCTORDETAILS")
public class DEDUCTORDETAILSController extends AbstractTDSController<String, DEDUCTORDETAILS, DEDUCTORDETAILSService>{
@Autowired
DEDUCTORDETAILSService service;
@Autowired
ApplicationCache applicationCache;
	@Override
	public DEDUCTORDETAILSService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<DEDUCTORDETAILS> getEntity() {		
		// TODO Auto-generated method stub
		return DEDUCTORDETAILS.class;
	}
	
	
	public Object getDetail(String tan, Long clientId) {
		// TODO Auto-generated method stub
		DeductorDetailWrapper ew = new DeductorDetailWrapper();
		DEDUCTORDETAILS deductor = service.getByKey(tan);
		ew.setDeductorDetails(deductor);
		return ew;
		
	
	}
	
	
	

}
