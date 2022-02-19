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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import domain.in.rjsa.model.form.Ajax;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Model;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.service.LogsService;
import domain.in.rjsa.service.ServiceInterfaceTaxo;
import domain.in.rjsa.web.ApplicationCache;

public abstract class AbstractBranchControllerTaxo<K extends Serializable, E extends Model, S extends ServiceInterfaceTaxo<K, E>>
		implements TDSControllerInterface<K, E> {

	public abstract S getService();

	@Autowired
	ApplicationCache applicationCache;
	
	@Autowired
	LogsService lservice;
	


	private final Logger logger = LoggerFactory.getLogger(getClass());
	

	// ------------------- List Entity ---------------------------------

	@RequestMapping(value = "/listBranch/{clientId}/get/{pageNo}/{resultPerPage}", method = RequestMethod.GET)
	public ResponseEntity<?> listAll(@PathVariable Long clientId, HttpServletRequest request, @PathVariable int pageNo,
			@PathVariable int resultPerPage) {
		// verify the clientId authorization
//		applicationCache.getUserAuthorised();
		String mapping = request.getPathInfo();

		try {
			List<?> list = getList(clientId, pageNo, resultPerPage);

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<?> getList(Long clientId, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		HashMap<String, Object> constrains = new HashMap<>();
//		constrains.put("clientId", applicationCache.getLoginDetail(getPrincipal()).getClientId());
//		constrains.put("employeeId", applicationCache.getLoginDetail(getPrincipal()).getEmployeeId());

		return getService().findAll(constrains, pageNo, resultPerPage);
	}

	// ------------------- Count Entity ---------------------------------

	@RequestMapping(value = "/listBranch/{clientId}/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(@PathVariable Long clientId, HttpServletRequest request) {
		// verify the clientId authorization
//			applicationCache.getUserAuthorised();
		HashMap<String, Object> constrains = new HashMap<>();
//		constrains.put("clientId", applicationCache.getLoginDetail(getPrincipal()).getClientId());
//		constrains.put("employeeId", applicationCache.getLoginDetail(getPrincipal()).getEmployeeId());

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

			return new ResponseEntity<>(getSearch(map), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<?> getSearch(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		Login l = applicationCache.getLoginDetail(getPrincipal());
//		map.put("clientId", l.getClientId());
//		map.put("employeeId", l.getEmployeeId());
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
//		map.put("clientId", l.getClientId());
//		map.put("employeeId", l.getEmployeeId());
		return getService().uniqueSearch(map);
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

	public Object getDetail(K tan, Long clientId) {
		// TODO Auto-generated method stub
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("TAN", tan);
	//	constrains.put("clientId", clientId);
		return getService().uniqueSearch(constrains);
	}

	
	
		
		 public void addLogsU(HashMap<String, Object> entity) {
			
		    	Login l = applicationCache.getLoginDetail(getPrincipal());
//				HashMap<String, Object>constrains= new HashMap<>();
//				constrains.put("id", Long.valueOf(entity.get("id").toString()));
//				constrains.put("clientId",l.getClientId());
//				Logs log = lservice.uniqueSearch(constrains);			
		    	Logs log = new Logs();
			  
			    log.setAction("Updated");
			    log.setIpaddrs(getIp());
			    String s=getEntity().getName();
			    String[] arrOfStr = s.split(".", 28); 
			    for (String a : arrOfStr) 
			    log.setEntity(a);
			    Gson gason = new Gson(); 
			    String json = gason.toJson(entity); 
			    log.setDate(new Date(System.currentTimeMillis()));
				log.setUsername(l.getUserName());
				
				lservice.save(log);
				
			}
		
		

		
		
	
	
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
		HashMap<String, Object> constrains = new HashMap<>();
//		constrains.put("clientId", clientId);
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
	
	private String getIp() {
		try {
			  InetAddress ipAddr = InetAddress.getLocalHost();
			  String str=ipAddr.getHostAddress();
			  return str;
		} catch (UnknownHostException ex) {
			 ex.printStackTrace(); // print Exception StackTrace
	
			return null;
		}
	}

}
