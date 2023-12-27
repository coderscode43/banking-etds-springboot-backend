package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.tds.PANITR2324;
import domain.in.rjsa.service.PANITR2324Service;

@Controller
@RequestMapping("/apipanitr2324")
public class PANITR2324Controller extends AbstractControllerTaxo<Long, PANITR2324, PANITR2324Service> {

	@Autowired
	PANITR2324Service service;
	
	@Override
	public PANITR2324Service getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<PANITR2324> getEntity() {
		// TODO Auto-generated method stub
		return PANITR2324.class;
	}


}
