package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.tds.CLIENTDETAILS;
import domain.in.rjsa.service.CLIENTDETAILSService;
@Controller
@RequestMapping("/apiclientDetails")
public class CLIENTDETAILSController extends AbstractControllerTaxo<String, CLIENTDETAILS, CLIENTDETAILSService>{
@Autowired
CLIENTDETAILSService service;
	@Override
	public CLIENTDETAILSService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<CLIENTDETAILS> getEntity() {
		// TODO Auto-generated method stub
		return CLIENTDETAILS.class;
	}

}
