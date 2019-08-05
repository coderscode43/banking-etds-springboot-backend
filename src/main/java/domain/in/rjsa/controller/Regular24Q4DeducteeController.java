package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Regular24Q4Deductee;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular24Q4DeducteeService;

@Controller
@RequestMapping("/apiform24Q")
public class Regular24Q4DeducteeController extends AbstractBranchController<Long,  Regular24Q4Deductee,  Regular24Q4DeducteeService> {

	@Autowired
	Regular24Q4DeducteeService service;
	
	
	@Override
	public Regular24Q4DeducteeService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular24Q4Deductee> getEntity() {
		// TODO Auto-generated method stub
		return Regular24Q4Deductee.class;
	}
	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		SalaryDetailWrapper ew = new SalaryDetailWrapper();
	//	Login l = applicationCache.getLoginDetail(getPrincipal());
		
	//	LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
	//	constrains.put("clientId", l.getClientId());
		Regular24Q4Deductee ded = service.getByKey(id);
		ew.setDeductee(ded);
		
		
		
		return ew;
	}
	
	

}
