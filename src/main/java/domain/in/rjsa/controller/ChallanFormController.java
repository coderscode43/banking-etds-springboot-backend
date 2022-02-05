package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.ChallanForm;
import domain.in.rjsa.service.ChallanFormService;

@Controller
@RequestMapping("/apichallanForm")
public class ChallanFormController extends AbstractBranchControllerForm<Long, ChallanForm, ChallanFormService> {
	
	@Autowired
	ChallanFormService service;

	@Override
	public ChallanFormService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<ChallanForm> getEntity() {
		// TODO Auto-generated method stub
		return ChallanForm.class;
	}

}
