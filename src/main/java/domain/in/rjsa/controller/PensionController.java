package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Pension;
import domain.in.rjsa.service.PensionService;
@Controller
@RequestMapping("/apipension")
public class PensionController extends AbstractBranchController<Long, Pension, PensionService>{

	@Autowired
	PensionService service;
	@Override
	public PensionService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Pension> getEntity() {
		// TODO Auto-generated method stub
		return Pension.class;
	}
	
}
