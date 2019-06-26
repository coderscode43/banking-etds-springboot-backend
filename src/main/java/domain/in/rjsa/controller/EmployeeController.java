package domain.in.rjsa.controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Address;
import domain.in.rjsa.model.form.BankAccDetail;
import domain.in.rjsa.model.form.Employee;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.wrapper.EmployeeDetailWrapper;
import domain.in.rjsa.service.AddressService;
import domain.in.rjsa.service.BankAccDetailService;
import domain.in.rjsa.service.EmployeeService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apiemployee")

public class EmployeeController extends AbstractController<Long, Employee, EmployeeService>{
@Autowired
EmployeeService service;
@Autowired
BankAccDetailService bService;
@Autowired
AddressService aService;
@Autowired
ApplicationCache applicationCache;


	@Override
	public EmployeeService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Employee> getEntity() {
		// TODO Auto-generated method stub
		return Employee.class;
	}
	
	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		EmployeeDetailWrapper ew = new EmployeeDetailWrapper();
		Login l = applicationCache.getLoginDetail(getPrincipal());
       
		LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
		constrains.put("clientId", l.getClientId());
		
		Employee emp = service.getByKey(id);
		ew.setEmployee(emp);
		if(emp.getBankId()!=null || emp.getCurAddrId()!=null || emp.getPerAddrId()!=null) {
			ew.setBank(bService.getByKey(emp.getBankId()));
			ew.setTaddress(aService.getByKey(emp.getCurAddrId()));
			ew.setPaddress(aService.getByKey(emp.getPerAddrId()));
		}else {
			ew.setBank(new BankAccDetail());
			ew.setTaddress(new Address());
			ew.setPaddress(new Address());
		}
		
		return ew;
	}

}
