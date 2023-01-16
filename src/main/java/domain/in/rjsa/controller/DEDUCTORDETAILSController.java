package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
import domain.in.rjsa.service.DEDUCTORDETAILSService;

@Controller
@RequestMapping("/apideductorDetails")
public class DEDUCTORDETAILSController extends AbstractControllerTaxo<Long, DEDUCTORDETAILS, DEDUCTORDETAILSService> {
	
	
	
@Autowired
DEDUCTORDETAILSService service;
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
}