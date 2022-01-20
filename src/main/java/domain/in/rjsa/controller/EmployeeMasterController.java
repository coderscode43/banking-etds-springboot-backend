package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.EmployeeMaster;
import domain.in.rjsa.service.EmployeeMasterService;
@Controller
@RequestMapping("/apiemployeeMaster")
public class EmployeeMasterController extends AbstractController<Long, EmployeeMaster, EmployeeMasterService>{
	@Autowired
	EmployeeMasterService service;
	@Override
	public EmployeeMasterService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<EmployeeMaster> getEntity() {
		// TODO Auto-generated method stub
		return EmployeeMaster.class;
	}

}
