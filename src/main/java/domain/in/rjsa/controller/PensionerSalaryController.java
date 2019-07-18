package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.PensionerSalary;
import domain.in.rjsa.service.PensionerSalaryService;
@Controller
@RequestMapping("/apipensionerSalary")
public class PensionerSalaryController  extends AbstractController<Long, PensionerSalary, PensionerSalaryService>{
	
	
	@Autowired
	PensionerSalaryService service;
		@Override
		public PensionerSalaryService getService() {
			// TODO Auto-generated method stub
			return service;
		}

		@Override
		public Class<PensionerSalary> getEntity() {
			// TODO Auto-generated method stub
			return PensionerSalary.class;
		}
	
	}

