package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Regular24Q4Challan;
import domain.in.rjsa.model.form.Regular26QChallan;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular26QChallanService;

@Controller
@RequestMapping("/apiformchalllan26Q")
public class Regular26QChallanController extends AbstractController<Long,  Regular26QChallan,  Regular26QChallanService>  {
@Autowired
Regular26QChallanService service;
	@Override
	public Regular26QChallanService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular26QChallan> getEntity() {
		// TODO Auto-generated method stub
		return Regular26QChallan.class;
	}
	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		SalaryDetailWrapper ew = new SalaryDetailWrapper();
	//	Login l = applicationCache.getLoginDetail(getPrincipal());
		
	//	LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
	//	constrains.put("clientId", l.getClientId());
		Regular26QChallan challan26 = service.getByKey(id);
		ew.setChallan26(challan26);
		
		
		
		return ew;
	}
}
