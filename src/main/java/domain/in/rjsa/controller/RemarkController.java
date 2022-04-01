package domain.in.rjsa.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.model.fy.Remark;
import domain.in.rjsa.service.RemarkService;

@Controller
@RequestMapping("/apiremark")
public class RemarkController extends AbstractControllerFY<Long, Remark, RemarkService> {

	@Autowired
	RemarkService service;
	

	@Override
	public RemarkService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Remark> getEntity() {
		// TODO Auto-generated method stub
		return Remark.class;
	}

	@RequestMapping(value = "/add/{fy}/{branchCode}/{type}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity,
			@PathVariable String type) {
		// FieldErrorDTO ermsg=new FieldErrorDTO();
		logger.info("Creating new Return instance");
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss").format(Calendar.getInstance().getTime());
		entity.put("dateTime", timeStamp);
		//Login l = applicationCache.getLoginDetail(getPrincipal());
		entity.put("userName", getPrincipal());
		entity.put("status",type);
		
		
		
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(entity);
		
		service.setResolve(gson.fromJson(jsonElement, getEntity()),type);
		addRemarkLogs(entity);
		return new ResponseEntity<Object>(HttpStatus.CREATED);

	}

	private final Logger logger = LoggerFactory.getLogger(getClass());

}
