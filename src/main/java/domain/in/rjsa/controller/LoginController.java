package domain.in.rjsa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.service.LoginService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apilogin")
public class LoginController extends AbstractControllerForm<Long, Login, LoginService>{
	
	@Autowired
	LoginService service;
	@Autowired
	ApplicationCache applicationCache;
	
	static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	
	//vaibhav
//	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
//	public ResponseEntity<?> updatePassword(@RequestBody String password) {
////		try {
////		//	Login login = applicationCache.getLoginDetail(getPrincipal());
////			login.setPassword(password);
////			service.updatePassword(login, password);
////			return new ResponseEntity<>(HttpStatus.OK);
////		} catch (Exception e) {
//////			logger.error("Error in listALL", e);
////			e.printStackTrace();
////			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
////		}
//	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController() {
		try {
			return new ResponseEntity<>(getDetail(), HttpStatus.OK);
		} catch (Exception e) {
//			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	public Login getDetail() {
		return service.getByKey(service.getLogin(getPrincipal()).getId());
	}//
	
	
	@Override
	public LoginService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Login> getEntity() {
		// TODO Auto-generated method stub
		return Login.class;
	}
	
}
