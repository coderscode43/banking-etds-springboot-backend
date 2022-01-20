package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.PensionMaster;
import domain.in.rjsa.service.PensionMasterService;
@Controller
@RequestMapping("/apipensionMaster")
public class PensionMasterController extends AbstractController<Long, PensionMaster, PensionMasterService>{
	@Autowired
	PensionMasterService service;
	@Override
	public PensionMasterService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<PensionMaster> getEntity() {
		// TODO Auto-generated method stub
		return PensionMaster.class;
	}
}
