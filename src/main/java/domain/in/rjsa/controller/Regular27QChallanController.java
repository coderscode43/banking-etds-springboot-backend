package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Regular24Q4Challan;
import domain.in.rjsa.model.form.Regular27QChallan;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular27QChallanService;

@Controller
@RequestMapping("/apiregular27QChallan")
public class Regular27QChallanController extends AbstractBranchController<Long,  Regular27QChallan,  Regular27QChallanService>{
@Autowired
Regular27QChallanService service;
	@Override
	public Regular27QChallanService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular27QChallan> getEntity() {
		// TODO Auto-generated method stub
		return Regular27QChallan.class;
	}

	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		SalaryDetailWrapper ew = new SalaryDetailWrapper();
	//	Login l = applicationCache.getLoginDetail(getPrincipal());
		
	//	LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
	//	constrains.put("clientId", l.getClientId());
		Regular27QChallan challan27 = service.getByKey(id);
		ew.setChallan27(challan27);
		
		
		
		return ew;
	}
}
