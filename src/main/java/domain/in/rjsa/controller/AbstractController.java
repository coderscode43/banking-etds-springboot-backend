
package domain.in.rjsa.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.security.CustomUserDetails;
import domain.in.rjsa.web.ApplicationCache;

public class AbstractController {

	public static final ObjectMapper mapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	@Value("${panel.access:}")
	private String panelAccess;

	@Autowired
	ApplicationCache applicationCache;

	static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	public String getBranchCode() {
		// IOB & KB
		if ("IOB".equalsIgnoreCase(panelAccess) || "KB".equalsIgnoreCase(panelAccess)) {
			CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			domain.in.rjsa.model.form.UserDetails user = applicationCache.getAdminUser(getPrincipal());
			if (user == null && userDetails.getBranchCode() != null) {
				userDetails.getBranchCode();
			} else {
				if (user != null && user.getTypeOfUser().equalsIgnoreCase("admin")) {
					return "admin";
				}
			}
			return userDetails.getBranchCode();
		}
		// NIA
		else {
			Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Login login = applicationCache.getLoginDetail(getPrincipal());
			domain.in.rjsa.model.form.UserDetails ud = null;

			if (login.getType() != null && login.getType().equalsIgnoreCase("admin")) {
				try {
					ud = applicationCache.getAdminUser(getPrincipal());
				} catch (Exception e) {
//					e.printStackTrace();
				}
			}
			String type = login.getType();
			if (type != null && type.equals("branch16A")) {
				return type;
			} else if (ud != null) {
				return "admin";
			} else {
				String branch = ((UserDetails) userDetails).getUsername();
				try {
					int b = Integer.parseInt(branch);
					return String.valueOf(b);
				} catch (Exception e) {
					return branch;
				}
			}
		}
	}

	public void adminValidation(LinkedHashMap<String, Object> map) {

		if (!"admin".equals(getBranchCode())) {
			Long b = 1L;
			try {
				b = Long.valueOf(getBranchCode());
			} catch (Exception e) {
				logger.warn("Error in admin validation: ", e);
			}
			map.put("branchCode", b);
		} else {
			if (map.containsKey("branchCode")) {
				Long b = 1L;
				try {
					b = Long.valueOf(map.get("branchCode").toString());
				} catch (Exception e) {
					logger.warn("Error in admin validation: ", e);
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
			ex.printStackTrace();

			return null;
		}
	}
}
