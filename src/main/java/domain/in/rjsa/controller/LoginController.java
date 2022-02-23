package domain.in.rjsa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.service.LoginService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apilogin")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	@Autowired
	ApplicationCache applicationCache;
	
	static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	
	// ------------------- Authentication User ---------------------------------
	
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<?> updatePassword(@RequestBody String password) {
		try {
			Login login = applicationCache.getLoginDetail(getPrincipal());
			login.setPassword(password);
			loginService.updatePassword(login, password);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
//			logger.error("Error in listALL", e);
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
}
