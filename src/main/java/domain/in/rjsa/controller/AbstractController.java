package domain.in.rjsa.controller;

import javax.naming.ldap.LdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import domain.in.rjsa.security.CustomUserDetails;
import domain.in.rjsa.web.ApplicationCache;

public class AbstractController {

	@Autowired
	ApplicationCache applicationCache;

	public String getBranchCode() {
		LdapContext ctxGC = null;
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		;
		if (applicationCache.getAdminUser(getPrincipal()) != null) {
			return "admin";

		} else {
			String branch = userDetails.getPhysicalDeliveryOfficeName();
			try {
				int b = Integer.valueOf(branch);
				return String.valueOf(b);
			} catch (Exception e) {
				// TODO: handle exception
				return branch;
			}
		}

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
