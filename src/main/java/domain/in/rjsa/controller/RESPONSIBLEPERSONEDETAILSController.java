package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
import domain.in.rjsa.model.tds.RESPONSIBLEPERSONEDETAILS;
import domain.in.rjsa.service.DEDUCTORDETAILSService;
import domain.in.rjsa.service.RESPONSIBLEPERSONEDETAILSService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apiRESPONSIBLEPERSONEDETAILS")
public class RESPONSIBLEPERSONEDETAILSController extends AbstractControllerTaxo<String, RESPONSIBLEPERSONEDETAILS, RESPONSIBLEPERSONEDETAILSService>{
	@Autowired
	RESPONSIBLEPERSONEDETAILSService service;
	@Autowired
	ApplicationCache applicationCache;
	
	@Override
	public RESPONSIBLEPERSONEDETAILSService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<RESPONSIBLEPERSONEDETAILS> getEntity() {
		// TODO Auto-generated method stub
		return RESPONSIBLEPERSONEDETAILS.class;
	}

}
