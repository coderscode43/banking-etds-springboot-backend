package domain.in.rjsa.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.fy.Remarks;
import domain.in.rjsa.model.fy.Tickets;
import domain.in.rjsa.service.RemarkService;
import domain.in.rjsa.service.TicketService;
import domain.in.rjsa.web.ApplicationCache;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/apiticket")
public class TicketController extends AbstractControllerForm<Long, Tickets, TicketService> {
	@Autowired
	TicketService service;
	@Autowired
	RemarkService rService;
	@Autowired
	ApplicationCache appliactionCache;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public TicketService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Tickets> getEntity() {
		// TODO Auto-generated method stub
		return Tickets.class;
	}

	@RequestMapping(value = "/list/{fy}/{branchCode}/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(@PathVariable String fy, @PathVariable Long branchCode,
			HttpServletRequest request) {
		HashMap<String, Object> constrains = new HashMap<>();
		if (!"admin".equals(getBranchCode())) {
			Long b=1L;
			try {
				b =Long.valueOf(getBranchCode());
			}catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		}else {
		}
		//constrains.put("fy", fy);
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
	
	
	@RequestMapping(value = "/list/{fy}/{branchCode}/get/{pageNo}/{resultPerPage}", method = RequestMethod.GET)
	public ResponseEntity<?> listAll(@PathVariable String fy, @PathVariable Long branchCode, HttpServletRequest request,
			@PathVariable int pageNo, @PathVariable int resultPerPage) {
		try {

			if (!"admin".equals(getBranchCode())) {
				Long b = 1L;
				try {
					b = Long.valueOf(getBranchCode());
				} catch (Exception e) {
					// TODO: handle exception
				}
				branchCode = b;
			} else {
			}

			List<?> list = getList(fy, branchCode, pageNo, resultPerPage);

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/add/{branchCode}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createEntity(@PathVariable Long branchCode,
			@RequestBody LinkedHashMap<String, Object> entity) {
		// FieldErrorDTO ermsg=new FieldErrorDTO();
		logger.info("Creating new Return instance");
		adminValidation(entity);
		String userName = getPrincipal();
		entity.put("userName", userName);
		entity.put("branchCode",branchCode);
		create(entity);
		addLogs("Add");
		// ermsg.setMessage(" Saved Successfully");
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	@RequestMapping(value = "/add/{fy}/{branchCode}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createFYEntity(@PathVariable Long branchCode,@PathVariable String fy,
			@RequestBody LinkedHashMap<String, Object> entity) {
		// FieldErrorDTO ermsg=new FieldErrorDTO();
		logger.info("Creating new Return instance");
		adminValidation(entity);
		String userName = getPrincipal();
		entity.put("userName", userName);
		entity.put("branchCode",branchCode);
		create(entity);
		addLogs("Add");
		// ermsg.setMessage(" Saved Successfully");
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	public void create(LinkedHashMap<String, Object> entity) {
		// Login l = applicationCache.getLoginDetail(getPrincipal());
		String userName = getPrincipal();
		entity.put("userName", userName);

		try {
			String jsonElement = mapper.writeValueAsString(entity);
			getService().save(mapper.readValue(jsonElement, getEntity()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "/addTicket", method = RequestMethod.POST)
	public ResponseEntity<?> addDocument(@RequestParam("branchCode") Long branchCode,
			@RequestParam("custVendId") String custVendId, 
			@RequestParam("dateOfChange") Date dateOfChange, 
			@RequestParam("dateOfOpening") Date dateOfOpening,
			@RequestParam("description") String description,
			@RequestParam("file") MultipartFile file,
			@RequestParam("form") String form, 
			@RequestParam("fy") String fy, 
			@RequestParam("pan") String pan,
			@RequestParam("quarter") String quarter,
			@RequestParam("resolved") boolean resolved, 
			@RequestParam("status") String status) {
		
		FieldErrorDTO ermsg = new FieldErrorDTO();
		logger.info("Creating new Return instance");
		LinkedHashMap<String, Object> entity = new LinkedHashMap<>();
		
		entity.put("branchCode", branchCode);
		entity.put("custVendId", custVendId);
		entity.put("dateOfChange", dateOfChange);
		entity.put("dateOfOpening", dateOfOpening);
		entity.put("description", description);
		entity.put("form", form);
		entity.put("fy", fy);
		entity.put("pan", pan);
		entity.put("quarter", quarter);
		entity.put("resolved", resolved);
		entity.put("status", status);
		//create(entity);
		
		adminValidation(entity);		String userName = getPrincipal();
		entity.put("userName", userName);
		service.addTicketWithFile(file,entity);
		addLogs("Add");
		
		 ermsg.setMessage(" Saved Successfully");
		return new ResponseEntity<Object>(HttpStatus.CREATED);

	}

	public List<?> getList(String fy, Long branchCode, int pageNo, int resultPerPage) {
		HashMap<String, Object> constrains = new HashMap<>();
		if (!"admin".equals(getBranchCode())) {
			Long b=1L;
			try {
				b =Long.valueOf(getBranchCode());
			}catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		}else {
		}
		//constrains.put("fy", fy);
		constrains.put("branchCode", branchCode);
		return getService().findAll(constrains, pageNo, resultPerPage);
	}


	@RequestMapping(value = "/search/{fy}/{branchCode}/{pageNo}/{resultPerPage}/{json}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable String fy, @PathVariable Long branchCode, @PathVariable int pageNo,@PathVariable int resultPerPage,
			@PathVariable String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map = mapper.readValue(json, new TypeReference<LinkedHashMap<String, Object>>() {
			});
			adminValidation(map);
			//map.put("fy", fy);
			map.put("branchCode", branchCode);
			Long count = getService().findallCount(map);
			List<?> list = getSearch(map, 0, 100);
			ListCount send = new ListCount();
			send.setCount(count);
			send.setEntities(list);
			return new ResponseEntity<>(send, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/detail/{fy}/{branchCode}/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController(@PathVariable Long id, @PathVariable String fy,
			@PathVariable Long branchCode){
		// verify the clientId authorization
		try {
			return new ResponseEntity<>(getDetail(id, fy, branchCode), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	public HashMap<String, Object> getDetail(Long id, String fy, Long branchCode) {
		// TODO Auto-generated method stub
		HashMap<String, Object> constrains = new HashMap<>();
		if (!"admin".equals(getBranchCode())) {
			Long b=1L;
			try {
				b =Long.valueOf(getBranchCode());
			}catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		}else {
		}
		constrains.put("id", id);
	//	constrains.put("fy", fy);
		constrains.put("branchCode", branchCode);
		HashMap<String, Object> map = new HashMap<>();
		map.put("deductee",getService().uniqueSearch(constrains));
		constrains.remove("id", id);
		constrains.put("deducteeId",id);
		List<Remarks> remarks = rService.findForm(constrains, 0, 100,"ticket");
		map.put("remark",remarks);
		return map; 
	}
	
	@RequestMapping(value = "/update/{page}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTestimonial(@RequestBody LinkedHashMap<String, Object> entity) {
		adminValidation(entity);
		try {
			Long id = Long.valueOf(entity.get("id").toString());
			Tickets tickets = service.getByKey(id);
			tickets.setResolved(false);
			service.update(tickets);
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("fy", tickets.getFy());
			map.put("deducteeId", tickets.getId());
			map.put("deducteeForm", "ticket");
			String remark=  entity.get("remark").toString();
			if(remark.endsWith("resolved")) {
			map.put("remark", "Resolved");
			}else if(remark.endsWith("reject")) {
				map.put("remark", "Reject");
			}
			String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			map.put("dateTime", timeStamp);
			map.put("userName", getPrincipal());
			map.put("branchCode", tickets.getBranchCode());
			rService.saveRemark(map);
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public List<?> getSearch(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return service.search(map, pageNo, resultPerPage);
	}
	
	@RequestMapping(value = "/downloadDoc/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> downloadDocument(HttpServletResponse response, @PathVariable Long id) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
			service.downloadDocument(id, response);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			ermsg.setEntityName("Upload Document");
			ermsg.setExceptionMsg(e.getMessage());
			return new ResponseEntity<>(ermsg, HttpStatus.BAD_REQUEST);
		}

	}
	
	
}
