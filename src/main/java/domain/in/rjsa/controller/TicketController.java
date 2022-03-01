package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.fy.Remark;
import domain.in.rjsa.model.fy.Ticket;
import domain.in.rjsa.service.RemarkService;
import domain.in.rjsa.service.TicketService;

@Controller
@RequestMapping("/apiticket")
public class TicketController extends AbstractControllerForm<Long, Ticket, TicketService> {
	@Autowired
	TicketService service;
	@Autowired
	RemarkService rService;

	@Override
	public TicketService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Ticket> getEntity() {
		// TODO Auto-generated method stub
		return Ticket.class;
	}

	@RequestMapping(value = "/list/{fy}/{branchCode}/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(@PathVariable String fy, @PathVariable String branchCode,
			HttpServletRequest request) {
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("fy", fy);
		constrains.put("branchCode", branchCode);
		try {
			Long count = getService().findallCount(constrains);
			List<?> list = getList(fy, branchCode, 0, 100);
			ListCount send = new ListCount();
			send.setCount(count);
			send.setEntities(list);
			return new ResponseEntity<>(send, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/add/{branchCode}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createEntity(@PathVariable String branchCode,
			@RequestBody LinkedHashMap<String, Object> entity) {
		// FieldErrorDTO ermsg=new FieldErrorDTO();
		logger.info("Creating new Return instance");
		String userName = getPrincipal();
		entity.put("userName", userName);
		entity.put("branchCode",branchCode);
		create(entity);
//		addLogs(entity);
		// ermsg.setMessage(" Saved Successfully");
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	@RequestMapping(value = "/add/{fy}/{branchCode}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createFYEntity(@PathVariable String branchCode,@PathVariable String fy,
			@RequestBody LinkedHashMap<String, Object> entity) {
		// FieldErrorDTO ermsg=new FieldErrorDTO();
		logger.info("Creating new Return instance");
		String userName = getPrincipal();
		entity.put("userName", userName);
		entity.put("branchCode",branchCode);
		create(entity);
//		addLogs(entity);
		// ermsg.setMessage(" Saved Successfully");
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity) {
		// FieldErrorDTO ermsg=new FieldErrorDTO();
		logger.info("Creating new Return instance");
		Ticket ticket = new Ticket();		String userName = getPrincipal();
		entity.put("userName", userName);
		create(entity);
		addLogs(entity);
		// ermsg.setMessage(" Saved Successfully");
		return new ResponseEntity<Object>(HttpStatus.CREATED);

	}

	public List<?> getList(String fy, String branchCode, int pageNo, int resultPerPage) {
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("fy", fy);
		constrains.put("branchCode", branchCode);
		return getService().findAll(constrains, pageNo, resultPerPage);
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());



	@RequestMapping(value = "/search/{fy}/{branchCode}/{json}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable String fy, @PathVariable String branchCode,
			@PathVariable String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map = mapper.readValue(json, new TypeReference<Map<String, String>>() {
			});
			for (String key : map.keySet()) {
				if (key.endsWith("Id")) {
					map.put(key, Long.valueOf((String) map.get(key)));
				}
			}
			map.put("fy", fy);
			map.put("branchCode", branchCode);
			return new ResponseEntity<>(getSearch(map), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/detail/{fy}/{branchCode}/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController(@PathVariable Long id, @PathVariable String fy,
			@PathVariable String branchCode){
		// verify the clientId authorization
		try {
			return new ResponseEntity<>(getDetail(id, fy, branchCode), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	public HashMap<String, Object> getDetail(Long id, String fy, String branchCode) {
		// TODO Auto-generated method stub
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", id);
		constrains.put("fy", fy);
		constrains.put("branchCode", branchCode);
		HashMap<String, Object> map = new HashMap<>();
		map.put("deductee",getService().uniqueSearch(constrains));
		constrains.remove("id", id);
		constrains.put("deducteeId",id);
		List<Remark> remark = rService.findForm(constrains, 0, 100,"ticket");
		map.put("remark",remark);
		return map; 
	}
	
	
}
