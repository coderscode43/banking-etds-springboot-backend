package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Regular24Q4Challan;
import domain.in.rjsa.model.form.Regular24Q4Deductee;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular24Q4ChallanService;

@Controller
@RequestMapping("/apiregular24Q4Challan")
public class Regular24Q4ChallanController extends AbstractController<Long, Regular24Q4Challan, Regular24Q4ChallanService>{
@Autowired
Regular24Q4ChallanService service;
	@Override
	public Regular24Q4ChallanService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular24Q4Challan> getEntity() {
		// TODO Auto-generated method stub
		return Regular24Q4Challan.class;
	}
	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		SalaryDetailWrapper ew = new SalaryDetailWrapper();
	//	Login l = applicationCache.getLoginDetail(getPrincipal());
		
	//	LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
	//	constrains.put("clientId", l.getClientId());
		Regular24Q4Challan challan = service.getByKey(id);
		ew.setChallan(challan);
		
		
		
		return ew;
	}

}
