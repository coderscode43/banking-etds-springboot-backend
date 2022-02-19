package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.MonthlyChallan;
import domain.in.rjsa.service.MonthlyChallanService;

@Controller
@RequestMapping("/apimonthlyChallan")
public class MonthlyChallanController extends AbstractControllerFY<Long, MonthlyChallan, MonthlyChallanService>{
	@Autowired
	MonthlyChallanService service;
	
	@Autowired
	@Override
	public Class<MonthlyChallan> getEntity() {
		// TODO Auto-generated method stub
		return MonthlyChallan.class;
	}

	@Override
	public MonthlyChallanService getService() {
		// TODO Auto-generated method stub
		return service;
	}

}
