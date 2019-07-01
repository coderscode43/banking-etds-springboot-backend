package domain.in.rjsa.controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Regular24Q4Salary;
import domain.in.rjsa.model.form.Salary;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular24Q4SalaryService;
import domain.in.rjsa.service.Regular27QDeducteeService;

@Controller
@RequestMapping("/apiform24Qsalary")
public class Regular24Q4SalaryController extends AbstractController<Long,  Regular24Q4Salary,  Regular24Q4SalaryService> {

	
	@Autowired
	Regular24Q4SalaryService service;
	@Override
	public Regular24Q4SalaryService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular24Q4Salary> getEntity() {
		// TODO Auto-generated method stub
		return Regular24Q4Salary.class;
	}
	
	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		SalaryDetailWrapper ew = new SalaryDetailWrapper();
	//	Login l = applicationCache.getLoginDetail(getPrincipal());
		
	//	LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
	//	constrains.put("clientId", l.getClientId());
		Regular24Q4Salary sal = service.getByKey(id);
		ew.setSalary(sal);
		
		
		
		return ew;
	}

}
