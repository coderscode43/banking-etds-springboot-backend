package domain.in.rjsa.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.model.form.Address;
import domain.in.rjsa.model.form.BankAccDetail;
import domain.in.rjsa.model.form.Employee;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Salary;
import domain.in.rjsa.model.wrapper.EmployeeDetailWrapper;
import domain.in.rjsa.model.wrapper.VendorDetailWrapper;
import domain.in.rjsa.service.AddressService;
import domain.in.rjsa.service.BankAccDetailService;
import domain.in.rjsa.service.EmployeeService;
import domain.in.rjsa.service.SalaryService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apiemployee")

public class EmployeeController extends AbstractController<Long, Employee, EmployeeService>{
@Autowired
EmployeeService service;
@Autowired
SalaryService service1;
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


		
		Salary sal = service1.getByKey(id);
		ew.setSalary(sal);
		
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
	
	@Override
	public void create(LinkedHashMap<String, Object> entity) {
		Gson gson = new Gson();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		if (entity.containsKey("clientId")) {
			entity.put("clientId", l.getClientId());
		}	
		JsonElement jsonElement = gson.toJsonTree(entity);
		EmployeeDetailWrapper employee = gson.fromJson(jsonElement, EmployeeDetailWrapper.class);
		employee.getBank().setClientId(Long.valueOf( entity.get("clientId").toString()));
		employee.getEmployee().setClientId(Long.valueOf( entity.get("clientId").toString()));
		employee.getPaddress().setClientId(Long.valueOf( entity.get("clientId").toString()));
		employee.getTaddress().setClientId(Long.valueOf( entity.get("clientId").toString()));

		getService().saveEntity(employee);

	}

}
