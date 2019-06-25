package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.tds.TOKENNUMBER;
import domain.in.rjsa.service.TOKENNUMBERService;

@Controller
@RequestMapping("/apitokenNumber")
public class TOKENNUMBERController extends AbstractTDSController<Long, TOKENNUMBER, TOKENNUMBERService> {

	@Autowired
	TOKENNUMBERService service;
	
	@Override
	public TOKENNUMBERService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<TOKENNUMBER> getEntity() {
		// TODO Auto-generated method stub
		return TOKENNUMBER.class;
	}

}
