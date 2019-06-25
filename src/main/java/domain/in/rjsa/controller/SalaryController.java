package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Salary;
import domain.in.rjsa.service.SalaryService;

@Controller
@RequestMapping("/apisalary")
public class SalaryController extends AbstractController<Long, Salary, SalaryService>{
@Autowired
SalaryService service;
	@Override
	public SalaryService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Salary> getEntity() {
		// TODO Auto-generated method stub
		return Salary.class;
	}

}
