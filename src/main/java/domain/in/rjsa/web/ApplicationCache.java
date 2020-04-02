package domain.in.rjsa.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.ClientDetail;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.UserSol;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.ClientDetailService;
import domain.in.rjsa.service.LoginService;
import domain.in.rjsa.service.UserSolService;

@Service("ApplicationCache")
public class ApplicationCache {

	
	private LoginService loginService;
	private ClientDetailService cdService;
	private BranchService branchService;
	private UserSolService userSolService;
	


	

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
	
	

	@Cacheable(value = "branch")
	public Branch getBranch(Long id) {
		// TODO Auto-generated method stub
		return branchService.getByKey(id);
	}
	
	@Cacheable(value= "userSol")
	public UserSol getUserSol(Long userId, Long clientId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("clientId", clientId);
		map.put("userId", userId);
		return userSolService.uniqueSearch(map );
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
	public void setBranchService(BranchService branchService) {
		this.branchService = branchService;
	}
	
	@Autowired
	public void setUserSolService(UserSolService userSolService) {
		this.userSolService = userSolService;
	}
	

	


}
