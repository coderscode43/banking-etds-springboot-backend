package domain.in.rjsa.controller;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.Ajax;
import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Model;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.model.fy.LogsJson;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.LogsJsonService;
import domain.in.rjsa.service.LogsService;
import domain.in.rjsa.service.ServiceInterfaceFY;
import domain.in.rjsa.web.ApplicationCache;

public abstract class AbstractBranchControllerFY<K extends Serializable, E extends Model, S 
extends ServiceInterfaceFY<K, E>> {
	@Autowired
	ApplicationCache applicationCache;

	@Autowired
	BranchService bservice;

	@Autowired
	LogsService lservice;

	@Autowired
	LogsJsonService ljService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	// ------------------- List Entity ---------------------------------

	public List<?> getList(Long clientId, int pageNo, int resultPerPage) {
		Login login = applicationCache.getLoginDetail(getPrincipal());
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("clientId", applicationCache.getLoginDetail(getPrincipal()).getClientId());
		
		for (String sol : applicationCache.getUserSol(login.getId(), clientId).getSolId().split("-")) {
			
		}
	

		return getService().findAll(constrains, pageNo, resultPerPage);
	}

	// ------------------- Count Entity ---------------------------------

	@RequestMapping(value = "/listBranch/{clientId}/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(@PathVariable Long clientId, HttpServletRequest request) {
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("clientId", applicationCache.getLoginDetail(getPrincipal()).getClientId());

		String mapping = request.getPathInfo();

		try {
			Long count = getService().findallCount(constrains);
			List<?> list = getList(clientId, 0, 100);
			ListCount send = new ListCount();
			send.setCount(count);
			send.setEntities(list);
			return new ResponseEntity<>(send, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	// ------------------- List Entity ---------------------------------

		@RequestMapping(value = "/list/{clientId}/get/{pageNo}/{resultPerPage}", method = RequestMethod.GET)
		public ResponseEntity<?> listAll(@PathVariable Long clientId, HttpServletRequest request, @PathVariable int pageNo,
				@PathVariable int resultPerPage) {
			// verify the clientId authorization
//			applicationCache.getUserAuthorised();
			String mapping = request.getPathInfo();

			try {
				List<?> list = getList(clientId, pageNo, resultPerPage);

				return new ResponseEntity<>(list, HttpStatus.OK);
			} catch (Exception e) {
				logger.error("Error in listALL", e);
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

		
	
		public List<?> getList1(Long clientId, int pageNo, int resultPerPage) {
			// TODO Auto-generated method stub
			HashMap<String, Object> constrains = new HashMap<>();
//			constrains.put("clientId", applicationCache.getLoginDetail(getPrincipal()).getClientId());
//			constrains.put("employeeId", applicationCache.getLoginDetail(getPrincipal()).getEmployeeId());

			return getService().findAll(constrains, pageNo, resultPerPage);
		}
	
	
	
	
	
	
	
	
	
	
	
	

	// ------------------- Search Entities ---------------------------------

	@RequestMapping(value = "/search/{clientId}/{json}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable String json, @PathVariable Long clientId) {
		// verify the clientId authorization
		try {
			ObjectMapper mapper = new ObjectMapper();

			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

			// convert JSON string to Map
			map = mapper.readValue(json, new TypeReference<Map<String, String>>() {
			});

			for (String key : map.keySet()) {
				if (key.endsWith("Id")) {
					map.put(key, Long.valueOf((String) map.get(key)));
				}
			}

			return new ResponseEntity<>(getSearch(map, clientId), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<?> getSearch(LinkedHashMap<String, Object> map, Long clientId) {
		// TODO Auto-generated method stub
		Login l = applicationCache.getLoginDetail(getPrincipal());
		map.put("clientId", l.getClientId());
//			map.put("employeeId", l.getEmployeeId());
		return getService().search(map);
	}

	// ------------------- Search Single Entity ---------------------------------

	@RequestMapping(value = "/searchEntity/{clientId}", method = RequestMethod.POST)
	public ResponseEntity<?> searchEntity(@RequestBody LinkedHashMap<String, Object> map, @PathVariable Long clientId) {
		// verify the clientId authorization
		try {
			return new ResponseEntity<>(getSearchEntity(map, clientId), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public E getSearchEntity(LinkedHashMap<String, Object> map, Long clientId) {
		// TODO Auto-generated method stub
		Login l = applicationCache.getLoginDetail(getPrincipal());
		map.put("clientId", l.getClientId());
//			map.put("employeeId", l.getEmployeeId());
		return getService().uniqueSearch(map);
	}

	// ------------------- Add Entity ---------------------------------

	@RequestMapping(value = "/add/{clientId}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity) {
		// FieldErrorDTO ermsg=new FieldErrorDTO();
		logger.info("Creating new Return instance");
		create(entity);
		addLogs(entity);
		// ermsg.setMessage(" Saved Successfully");
		return new ResponseEntity<Object>(HttpStatus.CREATED);

	}

	public void create(LinkedHashMap<String, Object> entity) {
		Gson gson = new Gson();
		Login l = applicationCache.getLoginDetail(getPrincipal());

		if (entity.containsKey("clientId")) {
			entity.put("clientId", l.getClientId());
		}

		entity.put("clientId", l.getClientId());
		// entity.put("branchId", );
		JsonElement jsonElement = gson.toJsonTree(entity);

		getService().save(gson.fromJson(jsonElement, getEntity()));

	}

	public void addLogs(HashMap<String, Object> entity) {
		LogsJson lj = new LogsJson();

		Login l = applicationCache.getLoginDetail(getPrincipal());
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", entity.get("id"));
		constrains.put("clientId", l.getClientId());
		Logs log = lservice.uniqueSearch(constrains);
		log = new Logs();
		log.setClientId(l.getClientId());
		log.setAction("Added");
		log.setIpaddrs(getIp());
		String s = getEntity().getName();
		String[] arrOfStr = s.split(".", 27);
		for (String a : arrOfStr)
			log.setEntity(a);
		Gson gason = new Gson();
		String json = gason.toJson(entity);
		log.setDate(new Date(System.currentTimeMillis()));
		log.setUsername(l.getUserName());
		lj.setId(log.getId());
		lj.setData(json);
		lservice.save(log);
		ljService.save(lj);

	}

	// ------------------- Get Detail ---------------------------------

	@RequestMapping(value = "/detail/{clientId}/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController(@PathVariable K id, @PathVariable Long clientId) {
		// verify the clientId authorization
		try {
			return new ResponseEntity<>(getDetail(id, clientId), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	

	// ------------------- Update Entity ---------------------------------

	@RequestMapping(value = "/update/{clientId}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody LinkedHashMap<String, Object> entity, HttpServletResponse response,
			UriComponentsBuilder ucBuilder) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		Long clientId = Long.valueOf(entity.get("clientId").toString());
		if (clientId == l.getClientId()) {
			Object o = getDetail((K) Long.valueOf(entity.get("id").toString()), clientId);
			ObjectMapper oMapper = new ObjectMapper();
			HashMap<String, Object> map = oMapper.convertValue(o, HashMap.class);
			update(entity);
			addLogsU(entity);

			ermsg.setMessage(" Updated Successfully");
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	public void update(LinkedHashMap<String, Object> entity) {

		Gson gson = new Gson();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		JsonElement jsonElement = gson.toJsonTree(entity);
		getService().update(gson.fromJson(jsonElement, getEntity()));

//			Gson gson = new Gson();
//			Login l = applicationCache.getLoginDetail(getPrincipal());
//			if (entity.containsKey("clientId")) {
//				entity.put("clientId", l.getClientId());
//			}
		// if (entity.containsKey("employeeId")) {
//			entity.put("employeeId",  l.getEmployeeId());
		// }
		// JsonElement jsonElement = gson.toJsonTree(entity);

		// getEntity from controller and validate that with validate method in
		// contorller and message from Service
		// getService().update(gson.fromJson(jsonElement, getEntity()));

	}

	public void addLogsU(HashMap<String, Object> entity) {
		LogsJson lj = new LogsJson();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", Long.valueOf(entity.get("id").toString()));
		constrains.put("clientId", l.getClientId());
		Logs log = lservice.uniqueSearch(constrains);
		log = new Logs();
		log.setClientId(l.getClientId());
		log.setAction("Updated");
		log.setIpaddrs(getIp());
		String s = getEntity().getName();
		String[] arrOfStr = s.split(".", 27);
		for (String a : arrOfStr)
			log.setEntity(a);
		Gson gason = new Gson();
		String json = gason.toJson(entity);
		log.setDate(new Date(System.currentTimeMillis()));
		log.setUsername(l.getUserName());
		lj.setId(log.getId());
		lj.setData(json);
		lservice.save(log);
		ljService.save(lj);
	}

	// ------------------- Delete Entity ---------------------------------

	@RequestMapping(value = "/delete/{clientId}/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> delete(@PathVariable K id, @PathVariable Long clientId) {
		// verify the clientId authorization
		try {
			getService().deleteT(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	// --------------------------------Upload File---------------------------

	/*
	 * @RequestMapping(value = "/uploadFile/{clientId}", method =
	 * RequestMethod.POST) public ResponseEntity<?>
	 * uploadFileMulti(@RequestParam("file") MultipartFile
	 * file, @RequestParam("dec") String dec,
	 * 
	 * @PathVariable Long clientId) { String lFilename = file.getOriginalFilename();
	 * String[] lext = lFilename.split("\\."); if
	 * (!(lext[1].equalsIgnoreCase("jpeg") || lext[1].equalsIgnoreCase("png") ||
	 * lext[1].equalsIgnoreCase("jpg") || lext[1].equalsIgnoreCase("pdf"))) {
	 * FieldErrorDTO dto = new FieldErrorDTO(); Gson gson = new Gson(); throw new
	 * CustomException("Invalid File Format, Allowed formats('jpeg','png','jpg','pdf'"
	 * ); } else { ObjectMapper mapper = new ObjectMapper(); LinkedHashMap<String,
	 * Object> map = new LinkedHashMap<String, Object>(); try { map =
	 * mapper.readValue(dec, new TypeReference<Map<String, String>>() { }); } catch
	 * (JsonParseException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (JsonMappingException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace();
	 * 
	 * } for (String key : map.keySet()) { if (key.endsWith("Id") ||
	 * key.endsWith("id")) { map.put(key, Long.valueOf((String) map.get(key))); } }
	 * save(map, file);
	 * 
	 * return new ResponseEntity<String>(HttpStatus.CREATED); }
	 * 
	 * }
	 */

	/*
	 * public void save(LinkedHashMap<String, Object> map, MultipartFile file) {
	 * FileDetail doc = new FileDetail(); Login l =
	 * applicationCache.getLoginDetail(getPrincipal());
	 * doc.setFileName(file.getOriginalFilename());
	 * doc.setClientId(l.getClientId());
	 * 
	 * map.put("fileId", null);
	 * 
	 * try { doc.setType(doc.getFileName().split("\\.")[1]);
	 * doc.setFile(file.getBytes()); getService().saveFile(doc, map, getEntity()); }
	 * catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

	// ------------------- ajax Entities ---------------------------------

	@RequestMapping(value = "/ajax/{clientId}", method = RequestMethod.POST)
	public ResponseEntity<?> ajax(@RequestBody Ajax ajax, @PathVariable Long clientId) {
		// verify the clientId authorization
		try {
			List<?> list = getAjax(ajax.getName(), ajax.getTerm(), clientId);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<?> getAjax(String name, String term, Long clientId) {
		// TODO Auto-generated method stub
		Login l = applicationCache.getLoginDetail(getPrincipal());
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("clientId", l.getClientId());
		return getService().ajax(name, term, constrains);
	}

	// ------------------- Other Methods ---------------------------------

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

	public abstract Class<E> getEntity();

	public abstract S getService();

	private String getIp() {
		try {
			InetAddress ipAddr = InetAddress.getLocalHost();
			String str = ipAddr.getHostAddress();
			return str;
		} catch (UnknownHostException ex) {
			ex.printStackTrace(); // print Exception StackTrace

			return null;
		}
	}

	public Object getDetail(K id, Long clientId) {
		// TODO Auto-generated method stub
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", id);
		constrains.put("clientId", clientId);
		return getService().uniqueSearch(constrains);
	}

	

}
