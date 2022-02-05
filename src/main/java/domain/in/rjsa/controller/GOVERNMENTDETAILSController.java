package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
import domain.in.rjsa.model.tds.GOVERNMENTDETAILS;
import domain.in.rjsa.model.wrapper.DeductorDetailWrapper;
import domain.in.rjsa.service.DEDUCTORDETAILSService;
import domain.in.rjsa.service.GOVERNMENTDETAILSService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apiGOVERNMENTDETAILS")
public class GOVERNMENTDETAILSController extends AbstractControllerTaxo<String, GOVERNMENTDETAILS, GOVERNMENTDETAILSService>{
	@Autowired
	GOVERNMENTDETAILSService service;
	@Autowired
	ApplicationCache applicationCache;
	@Override
	public GOVERNMENTDETAILSService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<GOVERNMENTDETAILS> getEntity() {
		// TODO Auto-generated method stub
		return GOVERNMENTDETAILS.class;
	}

	
}
