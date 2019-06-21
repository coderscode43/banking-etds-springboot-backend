package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.DEFAULTSUMMARY;
import domain.in.rjsa.service.DEFAULTSUMMARYService;

@Controller
@RequestMapping("/apidefaultSummary")
public class DEFAULTSUMMARYController extends AbstractController<Long, DEFAULTSUMMARY, DEFAULTSUMMARYService>{
@Autowired
DEFAULTSUMMARYService service;
	@Override
	public DEFAULTSUMMARYService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<DEFAULTSUMMARY> getEntity() {
		// TODO Auto-generated method stub
		return DEFAULTSUMMARY.class;
	}

}
