package domain.in.rjsa.web;

import java.util.HashMap;

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
import domain.in.rjsa.service.STATEMENTSTATUSService;
import domain.in.rjsa.service.UserDetailsService;

@Service("ApplicationCache")
public class ApplicationCache {

	@Autowired
	STATEMENTSTATUSService sService;
	private OrganizationDetailsService organizationDetailsService;
	private BranchService branchService;
	private UserDetailsService userDetailsService;
	private LoginService loginService;

	
	@Cacheable(value = "allAdminUsers", key = "#username")
	public domain.in.rjsa.model.form.UserDetails getAdminUser(String username) {
		// TODO Auto-generated method stub
		return userDetailsService.getByKey(username);
	}
	
	
	@CacheEvict(value = "allAdminUsers", key = "#username")
	public void adminRefresh(String username) {
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
	public void setBranchService(BranchService branchService) {
		this.branchService = branchService;
	}
	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}


	public Object getStatementStatus() {
		// TODO Auto-generated method stub
		HashMap<String, Object> constrains = new HashMap<String, Object>();
		return sService.findAll(constrains, 0, 10);
	}


	@Cacheable(value = "login")
	public Login getLoginDetail(String userName) {
		// TODO Auto-generated method stub
		return loginService.getLogin(userName);
	}





	

}
