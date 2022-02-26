package domain.in.rjsa.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
import domain.in.rjsa.model.tds.RESPONSIBLEPERSONEDETAILS;
import domain.in.rjsa.model.tds.STATEMENTSTATUS;
import domain.in.rjsa.model.tds.TRACESSLOGIN;
import domain.in.rjsa.model.wrapper.DeductorDetailWrapper;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.DEDUCTORDETAILSService;
import domain.in.rjsa.service.RESPONSIBLEPERSONEDETAILSService;
import domain.in.rjsa.service.STATEMENTSTATUSService;
import domain.in.rjsa.service.TRACESSLOGINService;
import domain.in.rjsa.web.ApplicationCache;

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