package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.Testing;
import domain.in.rjsa.service.TestingService;

@Controller
@RequestMapping("/apitesting")
public class TestingController extends AbstractBranchControllerFY<Long, Testing, TestingService>{
	@Autowired
	TestingService service;
	@Override
	public TestingService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Testing> getEntity() {
		// TODO Auto-generated method stub
		return Testing.class;
	}

}
