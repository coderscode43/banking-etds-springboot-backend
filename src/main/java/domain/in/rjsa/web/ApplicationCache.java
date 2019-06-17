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

	
	private ClientDetailService cdService;
	private LoginService loginService;
	private AdminService aService;
	private EmployeeService eService;

	@Cacheable(value = "clientDetail")
	public ClientDetail getClientDetail(Long id) {
		// TODO Auto-generated method stub
		return cdService.getByKey(id);
	}




	@Cacheable(value = "employeeAdmin")
	public Boolean isAdmin(Long employeeId) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<>();
		map.put("employeeId", employeeId);
		Admin admin = aService.uniqueSearch(map);
		if(admin!=null) {
			return true;
		}else {
			return false;
		}
		
	}
	
	

	@Cacheable(value = "login")
	public Login getLoginDetail(String userName) {
		// TODO Auto-generated method stub
		return loginService.getLogin(userName);
	}


	@CacheEvict(value = "login", key = "#userName")
	public void refreshLogin(String userName) {
		// TODO Auto-generated method stub
	}

	

	@Autowired
	public void setClientDetailService(ClientDetailService clientDetailService) {
		this.cdService = clientDetailService;
	}


	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	@Autowired
	public void setAdminService(AdminService adminService) {
		this.aService = adminService;
	}
	@Autowired
	public void setEmployeeService(EmployeeService eService) {
		this.eService = eService;
	}

}
