package domain.in.rjsa.controller;

import javax.naming.ldap.LdapContext;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import domain.in.rjsa.security.CustomUserDetails;

public class AbstractController {

	
	
	
	
	public String getBranchCode() {
		LdapContext ctxGC = null;
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//return userDetails.getPhysicalDeliveryOfficeName();
		return "admin";
	}
	
	public String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;

	}

}
