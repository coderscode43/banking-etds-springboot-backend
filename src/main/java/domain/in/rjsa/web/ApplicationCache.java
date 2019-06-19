package domain.in.rjsa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import domain.in.rjsa.model.ClientDetail;
import domain.in.rjsa.model.Login;
import domain.in.rjsa.service.ClientDetailService;
import domain.in.rjsa.service.LoginService;

@Service("ApplicationCache")
public class ApplicationCache {

	
	private LoginService loginService;
	private ClientDetailService cdService;


	

	@Cacheable(value = "login")
	public Login getLoginDetail(String userName) {
		// TODO Auto-generated method stub
		return loginService.getLogin(userName);
	}


	@CacheEvict(value = "login", key = "#userName")
	public void refreshLogin(String userName) {
		// TODO Auto-generated method stub
	}


	@Cacheable(value = "clientDetail")
	public ClientDetail getClientDetail(Long id) {
		// TODO Auto-generated method stub
		return cdService.getByKey(id);
	}

	@Autowired
	public void setClientDetailService(ClientDetailService clientDetailService) {
		this.cdService = clientDetailService;
	}

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
