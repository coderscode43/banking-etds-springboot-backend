package domain.in.rjsa.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import domain.in.rjsa.model.Admin;
import domain.in.rjsa.model.ClientDetail;
import domain.in.rjsa.model.Login;
import domain.in.rjsa.service.AdminService;
import domain.in.rjsa.service.ClientDetailService;
import domain.in.rjsa.service.EmployeeService;
import domain.in.rjsa.service.LoginService;

@Service("ApplicationCache")
public class ApplicationCache {

	
	private LoginService loginService;


	

	@Cacheable(value = "login")
	public Login getLoginDetail(String userName) {
		// TODO Auto-generated method stub
		return loginService.getLogin(userName);
	}


	@CacheEvict(value = "login", key = "#userName")
	public void refreshLogin(String userName) {
		// TODO Auto-generated method stub
	}

	

}
