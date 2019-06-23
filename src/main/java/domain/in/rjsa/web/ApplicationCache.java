package domain.in.rjsa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.ClientDetail;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Zone;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.ClientDetailService;
import domain.in.rjsa.service.LoginService;
import domain.in.rjsa.service.ZoneService;

@Service("ApplicationCache")
public class ApplicationCache {

	
	private LoginService loginService;
	private ClientDetailService cdService;
	private BranchService branchService;
	private ZoneService zoneService;


	

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
	
	@Cacheable(value = "zone")
	public Zone getZone(Long id) {
		// TODO Auto-generated method stub
		return zoneService.getByKey(id);
	}

	@Cacheable(value = "branch")
	public Branch getBranch(Long id) {
		// TODO Auto-generated method stub
		return branchService.getByKey(id);
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
	public void setZoneService(ZoneService zoneService) {
		this.zoneService = zoneService;
	}

}
