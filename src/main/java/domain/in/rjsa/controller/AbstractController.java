package domain.in.rjsa.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;

import javax.naming.ldap.LdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import domain.in.rjsa.security.CustomUserDetails;
import domain.in.rjsa.web.ApplicationCache;

public class AbstractController {

	@Autowired
	ApplicationCache applicationCache;
	
/////////////////////////////////KARNATAKA BANK////////////////////////////////////////	

//	public String getBranchCode() {
//		LdapContext ctxGC = null;
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
//				.getPrincipal();
//		;
//		if (applicationCache.getAdminUser(getPrincipal()) != null) {
//			return "admin";
//
//		} else {
//			String branch = userDetails.getPhysicalDeliveryOfficeName();
//			try {
//				int b = Integer.valueOf(branch);
//				return String.valueOf(b);
//			} catch (Exception e) {
//				// TODO: handle exception
//				return branch;
//			}
//		}
//
//	}
	
	
/////////////////////////////////UCO BANK////////////////////////////////////////
	
	public String getBranchCode() {
//		LdapContext ctxGC = null;
		Object userDetails =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (applicationCache.getAdminUser(getPrincipal()) != null) {
			return "admin";

		} else {
			String branch = ((UserDetails) userDetails).getUsername();
			try {
				int b = Integer.parseInt(branch);
				return String.valueOf(b);
			} catch (Exception e) {
				// TODO: handle exception
				return branch;
			}
		}

	}
	
	public void adminValidation(LinkedHashMap<String, Object> map) {
		
		if (!"admin".equals(getBranchCode())) {
			Long b=1L;
			try {
				b =Long.valueOf(getBranchCode());
			}catch (Exception e) {
				// TODO: handle exception
			}
			map.put("branchCode", b);
		}else{
			if(map.containsKey("branchCode")) {
				Long b=1L;
				try {
					b =Long.valueOf(map.get("branchCode").toString());
				}catch (Exception e) {
					// TODO: handle exception
				}
				map.put("branchCode", b);
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

	protected String getIp() {
		try {
			InetAddress ipAddr = InetAddress.getLocalHost();
			String str = ipAddr.getHostAddress();
			return str;
		} catch (UnknownHostException ex) {
			ex.printStackTrace(); // print Exception StackTrace

			return null;
		}
	}
}
