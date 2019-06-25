package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.tds.GH15RETURNSTATUS;
import domain.in.rjsa.service.GH15RETURNSTATUSService;
import domain.in.rjsa.service.TOKENNUMBERService;

@Controller
@RequestMapping("/apiGH15RETURNSTATUS")
public class GH15RETURNSTATUSController  extends AbstractTDSController<Long, GH15RETURNSTATUS, GH15RETURNSTATUSService> {

	
	@Autowired
	GH15RETURNSTATUSService service;
	@Override
	public GH15RETURNSTATUSService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<GH15RETURNSTATUS> getEntity() {
		// TODO Auto-generated method stub
		return  GH15RETURNSTATUS.class;
	}

}
