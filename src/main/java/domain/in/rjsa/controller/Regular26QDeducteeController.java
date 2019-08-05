package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Regular24Q4Challan;
import domain.in.rjsa.model.form.Regular26QDeductee;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular26QDeducteeService;

@Controller
@RequestMapping("/apiform26Q")
public class Regular26QDeducteeController extends AbstractBranchController<Long, Regular26QDeductee, Regular26QDeducteeService> {

	@Autowired
	Regular26QDeducteeService service;
	
	@Override
	public Regular26QDeducteeService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular26QDeductee> getEntity() {
		// TODO Auto-generated method stub
		return Regular26QDeductee.class;
	}

	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		SalaryDetailWrapper ew = new SalaryDetailWrapper();
	//	Login l = applicationCache.getLoginDetail(getPrincipal());
		
	//	LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
	//	constrains.put("clientId", l.getClientId());
		Regular26QDeductee deductee26 = service.getByKey(id);
		ew.setDeductee26(deductee26);
		
		
		
		return ew;
	}
}
