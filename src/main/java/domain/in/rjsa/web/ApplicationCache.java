package domain.in.rjsa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.OrganizationDetails;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.LoginService;
import domain.in.rjsa.service.OrganizationDetailsService;

@Service("ApplicationCache")
public class ApplicationCache {

	
	private LoginService loginService;
	private OrganizationDetailsService organizationDetailsService;
	private BranchService branchService;
	
	

	@Cacheable(value = "login")
	public Login getLoginDetail(String userName) {
		// TODO Auto-generated method stub
		return loginService.getLogin(userName);
	}


	@CacheEvict(value = "login", key = "#userName")
	public void refreshLogin(String userName) {
		// TODO Auto-generated method stub
	}


	@Cacheable(value = "organizationDetails")
	public OrganizationDetails getOrganizationDetails(Long id) {
		// TODO Auto-generated method stub
		return organizationDetailsService.getByKey(id);
	}
	
	

	@Cacheable(value = "branch")
	public Branch getBranch(Long id) {
		// TODO Auto-generated method stub
		return branchService.getByKey(id);
	}
	
	
	
	@Autowired
	public void setOrganizationDetailsService(OrganizationDetailsService organizationDetailsService) {
		this.organizationDetailsService = organizationDetailsService;
	}

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@Autowired
	public void setBranchService(BranchService branchService) {
		this.branchService = branchService;
	}





	

}
