package domain.in.rjsa.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
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

import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.fy.Regular24QDeductee;
import domain.in.rjsa.model.fy.Regular26QDeductee;
import domain.in.rjsa.model.fy.Regular27EQDeductee;
import domain.in.rjsa.model.fy.Regular27QDeductee;
import domain.in.rjsa.model.fy.Remark;
import domain.in.rjsa.model.fy.Ticket;
import domain.in.rjsa.service.Regular24QDeducteeService;
import domain.in.rjsa.service.Regular26QDeducteeService;
import domain.in.rjsa.service.Regular27EQDeducteeService;
import domain.in.rjsa.service.Regular27QDeducteeService;
import domain.in.rjsa.service.RemarkService;
import domain.in.rjsa.service.TicketService;

@Controller
@RequestMapping("/apiremark")
public class RemarkController extends AbstractControllerFY<Long, Remark, RemarkService> {

	@Autowired
	RemarkService service;
	@Autowired
	Regular24QDeducteeService r24qService;
	@Autowired
	Regular26QDeducteeService r26qService;
	@Autowired
	Regular27EQDeducteeService r27eqService;
	@Autowired
	Regular27QDeducteeService r27qService;
	@Autowired
	TicketService tService;

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
		Login l = applicationCache.getLoginDetail(getPrincipal());
		entity.put("userName", l.getUserName());
		entity.put("status",type);
		create(entity);
		int ID = (int) entity.get("deducteeId");
		Long id = new Long(ID);
		String remark = entity.get("deducteeForm").toString();
		if (type.endsWith("Remark")) {
			if (remark.endsWith("24Qform")) {
				Regular24QDeductee r24q = r24qService.getByKey(id);
				r24q.setResolved(true);
				r24qService.update(r24q);
			} else if (remark.endsWith("26Qform")) {
				Regular26QDeductee r26q = r26qService.getByKey(id);
				r26q.setResolved(true);
				r26qService.update(r26q);
			} else if (remark.endsWith("27EQform")) {
				Regular27EQDeductee r27eq = r27eqService.getByKey(id);
				r27eq.setResolved(true);
				r27eqService.update(r27eq);
			} else if (remark.endsWith("27Qform")) {
				Regular27QDeductee r27q = r27qService.getByKey(id);
				r27q.setResolved(true);
				r27qService.update(r27q);
			} else if (remark.endsWith("ticket")) {
				Ticket ticket = tService.getByKey(id);
				ticket.setResolved(true);
				tService.update(ticket);
			}
		}else if(type.endsWith("Resolve")||type.endsWith("Reject"))
		{	
			if (remark.endsWith("24Qform")) {
				Regular24QDeductee r24q = r24qService.getByKey(id);
				r24q.setResolved(false);
				r24qService.update(r24q);
			} else if (remark.endsWith("26Qform")) {
				Regular26QDeductee r26q = r26qService.getByKey(id);
				r26q.setResolved(false);
				r26qService.update(r26q);
			} else if (remark.endsWith("27EQform")) {
				Regular27EQDeductee r27eq = r27eqService.getByKey(id);
				r27eq.setResolved(false);
				r27eqService.update(r27eq);
			} else if (remark.endsWith("27Qform")) {
				Regular27QDeductee r27q = r27qService.getByKey(id);
				r27q.setResolved(false);
				r27qService.update(r27q);
			} else if (remark.endsWith("ticket")) {
				Ticket ticket = tService.getByKey(id);
				ticket.setResolved(false);
				tService.update(ticket);
			}
		}

		addRemarkLogs(entity);
		return new ResponseEntity<Object>(HttpStatus.CREATED);

	}

	public void create(LinkedHashMap<String, Object> entity) {
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(entity);
		getService().save(gson.fromJson(jsonElement, getEntity()));

	}

	private final Logger logger = LoggerFactory.getLogger(getClass());

}
