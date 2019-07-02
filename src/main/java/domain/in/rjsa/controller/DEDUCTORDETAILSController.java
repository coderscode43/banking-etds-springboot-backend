package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.ClientDetail;
import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
import domain.in.rjsa.model.tds.GOVERNMENTDETAILS;
import domain.in.rjsa.model.tds.RESPONSIBLEPERSONEDETAILS;
import domain.in.rjsa.model.wrapper.DeductorDetailWrapper;
import domain.in.rjsa.service.ClientDetailService;
import domain.in.rjsa.service.DEDUCTORDETAILSService;
import domain.in.rjsa.service.GOVERNMENTDETAILSService;
import domain.in.rjsa.service.RESPONSIBLEPERSONEDETAILSService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apideductorDetails")
public class DEDUCTORDETAILSController extends AbstractTDSController<String, DEDUCTORDETAILS, DEDUCTORDETAILSService>{
@Autowired
DEDUCTORDETAILSService service;
@Autowired
RESPONSIBLEPERSONEDETAILSService rService;
@Autowired
ApplicationCache applicationCache;
@Autowired
GOVERNMENTDETAILSService gService;
@Autowired
ClientDetailService sdService;

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
	
     @Override	
	public Object getDetail(String id, Long clientId) {
		// TODO Auto-generated method stub
    	String tan =id;
		DeductorDetailWrapper ew = new DeductorDetailWrapper();
		DEDUCTORDETAILS deductor = service.getByKey(tan);
		ew.setDeductorDetails(deductor);
		RESPONSIBLEPERSONEDETAILS reaponsible = rService.getByKey(tan);
		ew.setRespersonDetails(reaponsible);
		GOVERNMENTDETAILS govt = gService.getByKey(tan);
		ew.setGovtDetails(govt);
		//ClientDetail cd = sdService.getByKey(tan);
		//ew.setGovtDetails(govt);
		return ew;
	}
}
