package domain.in.rjsa.controller;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.UserDetails;
import domain.in.rjsa.model.fy.Ticket;
import domain.in.rjsa.service.UserDetailsService;

@Controller
@RequestMapping("/apiuserDetails")
public class UserDetailsController extends AbstractControllerForm<Long, UserDetails, UserDetailsService> {

	@Autowired
	UserDetailsService service;

	@Override
	public UserDetailsService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<UserDetails> getEntity() {
		// TODO Auto-generated method stub
		return UserDetails.class;
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity) {
		// FieldErrorDTO ermsg=new FieldErrorDTO();
		logger.info("Creating new Return instance");
		Ticket ticket = new Ticket();
		UserDetails user = service.getUserByUserName(entity.get("userName").toString());
		if (user != null) {
			// send otp to mobile number here
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			create(entity);
			service.saveNewUser(entity.get("userName").toString(), entity.get("password").toString());
			addLogs(entity);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		}
		
		// ermsg.setMessage(" Saved Successfully");
		

	}

	public void create(LinkedHashMap<String, Object> entity) {
		Gson gson = new Gson();
		Login l = applicationCache.getLoginDetail(getPrincipal());

		JsonElement jsonElement = gson.toJsonTree(entity);

		getService().save(gson.fromJson(jsonElement, getEntity()));

	}

}
