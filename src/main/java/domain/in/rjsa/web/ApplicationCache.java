package domain.in.rjsa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.OrganizationDetails;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.OrganizationDetailsService;
import domain.in.rjsa.service.UserDetailsService;

@Service("ApplicationCache")
public class ApplicationCache {

	
	private OrganizationDetailsService organizationDetailsService;
	private BranchService branchService;
	private UserDetailsService userDetailsService;
	

	
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





	

}
