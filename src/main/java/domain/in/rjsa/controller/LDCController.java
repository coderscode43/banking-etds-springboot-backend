package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.tds.LDC;
import domain.in.rjsa.service.LDCService;

@Controller
@RequestMapping("/apildc")
public class LDCController extends AbstractBranchControllerTaxo<String, LDC, LDCService>{

	@Autowired
	LDCService service;
	
	@Override
	public LDCService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<LDC> getEntity() {
		// TODO Auto-generated method stub
		return LDC.class;
	}

}
