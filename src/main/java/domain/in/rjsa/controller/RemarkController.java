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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.fy.Remark;
import domain.in.rjsa.service.Regular24QDeducteeService;
import domain.in.rjsa.service.Regular26QDeducteeService;
import domain.in.rjsa.service.RemarkService;

@Controller
@RequestMapping("/apiremark")
public class RemarkController extends AbstractControllerFY<Long, Remark, RemarkService>{

	
	@Autowired
	RemarkService service;
	@Autowired
	Regular24QDeducteeService r24qService;
	@Autowired
	Regular26QDeducteeService r26qService;
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
	@RequestMapping(value = "/add/{fy}/{branchCode}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity) {
		// FieldErrorDTO ermsg=new FieldErrorDTO();
		logger.info("Creating new Return instance");
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss").format(Calendar.getInstance().getTime());
		entity.put("dateTime", timeStamp);
		Login l = applicationCache.getLoginDetail(getPrincipal());
		entity.put("userName", l.getUserName());
		create(entity);
//		int ID = (int) entity.get("deducteeId");
//		Long id = new Long(ID); 
//		if(entity.get("deducteeForm")=="24Q") {
//			Regular24QDeductee r24q = r24qService.getByKey(id);
//			r24q.setResolved(false);
//		}
//		else if(entity.get("deducteeForm")=="26Q") {
//			Regular26QDeductee r26q = r26qService.getByKey(id);
//			r26q.setResolved(false);
//		}
			
			
//		addLogs(entity);
		// ermsg.setMessage(" Saved Successfully");
		return new ResponseEntity<Object>(HttpStatus.CREATED);

	}

	public void create(LinkedHashMap<String, Object> entity) {
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(entity);
		getService().save(gson.fromJson(jsonElement, getEntity()));

	}
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
}
