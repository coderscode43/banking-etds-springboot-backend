package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.tds.ITRLOGIN;
import domain.in.rjsa.service.ITRLOGINService;

@Controller
@RequestMapping("/apiitrlogin")
public class ITRLOGINController extends AbstractControllerTaxo<Long, ITRLOGIN, ITRLOGINService> {

	@Autowired
	ITRLOGINService service;
	
	
	@Override
	public ITRLOGINService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<ITRLOGIN> getEntity() {
		// TODO Auto-generated method stub
		return ITRLOGIN.class;
	}



}
