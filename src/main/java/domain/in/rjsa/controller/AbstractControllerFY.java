
package domain.in.rjsa.controller;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
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
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.form.Model;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.LogsService;
import domain.in.rjsa.service.ServiceInterfaceFY;
import domain.in.rjsa.web.ApplicationCache;

public abstract class AbstractControllerFY<K extends Serializable, E extends Model, S extends ServiceInterfaceFY<K, E>> extends AbstractController {
	@Autowired
	ApplicationCache applicationCache;

	@Autowired
	BranchService bservice;

	@Autowired
	LogsService lservice;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	// ------------------- List Entity ---------------------------------
	public List<?> getList(String fy, Long branchCode, int pageNo, int resultPerPage) {
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("fy", fy);
		
		if (!"admin".equals(getBranchCode())) {
			Long b=1L;
			try {
				b =Long.valueOf(getBranchCode());
			}catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		}else {
			constrains.put("branchCode", branchCode);
		}
		
		
		
		
		return getService().findall(constrains, pageNo, resultPerPage);
	}

	// ------------------- Count Entity ---------------------------------
	@RequestMapping(value = "/list/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count( HttpServletRequest request) {
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
		

		try {
			Long count = getService().findallCount(constrains);
			List<?> list = getList( 0, 100);
			ListCount send = new ListCount();
			send.setCount(count);
			send.setEntities(list);
			return new ResponseEntity<>(send, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	public List<?> getList( int pageNo, int resultPerPage) {
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
		
		
		
		
		return getService().findall(constrains, pageNo, resultPerPage);
	}
	
	
	// ------------------- List Entity ---------------------------------

	@RequestMapping(value = "/list/get/{pageNo}/{resultPerPage}", method = RequestMethod.GET)
	public ResponseEntity<?> listAll(HttpServletRequest request, @PathVariable int pageNo,
			@PathVariable int resultPerPage) {
		// verify the clientId authorization
//		applicationCache.getUserAuthorised();

		try {
			List<?> list = getList(pageNo, resultPerPage);

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	
	
	@RequestMapping(value = "/list/{fy}/{branchCode}/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(@PathVariable String fy, @PathVariable Long branchCode,
			HttpServletRequest request) {
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("fy",fy);
		

		if (!"admin".equals(getBranchCode())) {
			Long b=1L;
			try {
				b =Long.valueOf(getBranchCode());
			}catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		}else {
			constrains.put("branchCode", branchCode);
		}
		
		
		
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
	
	

	// ------------------- List Entity ---------------------------------

	@RequestMapping(value = "/list/{fy}/{branchCode}/get/{pageNo}/{resultPerPage}", method = RequestMethod.GET)
	public ResponseEntity<?> listAll(@PathVariable String fy, @PathVariable Long branchCode,
			HttpServletRequest request, @PathVariable int pageNo, @PathVariable int resultPerPage) {
		try {
			

			if (!"admin".equals(getBranchCode())) {
				Long b=1L;
				try {
					b =Long.valueOf(getBranchCode());
				}catch (Exception e) {
					// TODO: handle exception
				}
				branchCode=b;
			}else {
			}
			
			List<?> list = getList(fy, branchCode, pageNo, resultPerPage);

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// ------------------- Search Entities ---------------------------------

	@RequestMapping(value = "/search/{fy}/{branchCode}/{json}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable String fy, @PathVariable Long branchCode, @PathVariable String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map = mapper.readValue(json, new TypeReference<Map<String, String>>() {
			});
			if(map.containsKey("branchCode")) {
				branchCode = Long.valueOf(map.get("branchCode").toString());
				map.put("branchCode", branchCode);
			}
			if(map.containsKey("roCode")) {
				Long roCode = Long.valueOf(map.get("roCode").toString());
				map.put("roCode", roCode);
			}
			map.put("fy", fy);
			
			if (!"admin".equals(getBranchCode())) {
				Long b=1L;
				try {
					b =Long.valueOf(getBranchCode());
				}catch (Exception e) {
					// TODO: handle exception
				}
				map.put("branchCode", b);
			}else {
				map.put("branchCode", branchCode);
			}
			Long count = getService().findallCount(map);
			List<?> list = getSearch(map);
			ListCount send = new ListCount();
			send.setCount(count);
			send.setEntities(list);
			
			return new ResponseEntity<>(send, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<?> getSearch(LinkedHashMap<String, Object> map) {
//		Login l = applicationCache.getLoginDetail(getPrincipal());
		return getService().search(map);
	}

	// ------------------- Search Single Entity ---------------------------------
	@RequestMapping(value = "/searchEntity/{fy}/{branchCode}", method = RequestMethod.POST)
	public ResponseEntity<?> searchEntity(@RequestBody LinkedHashMap<String, Object> map, @PathVariable String fy,
			@PathVariable Long branchCode) {
		try {
			map.put("fy", fy);
			if (!"admin".equals(getBranchCode())) {
				Long b=1L;
				try {
					b =Long.valueOf(getBranchCode());
				}catch (Exception e) {
					// TODO: handle exception
				}
				map.put("branchCode", b);
			}else {
				map.put("branchCode", branchCode);
			}
			return new ResponseEntity<>(getSearchEntity(map), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public E getSearchEntity(LinkedHashMap<String, Object> map) {
		return getService().uniqueSearch(map);
	}

	// ------------------- Add Entity ---------------------------------

	/*
	 * @RequestMapping(value = "/add/{fy}/{branchCode}", method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public ResponseEntity<?> createEntity(@RequestBody
	 * LinkedHashMap<String, Object> entity,
	 * 
	 * @PathVariable Long branchCode, @PathVariable String fy) { // FieldErrorDTO
	 * ermsg=new FieldErrorDTO(); logger.info("Creating new Return instance");
	 * entity.put("branchCode", branchCode); entity.put("fy", fy); String userName =
	 * getPrincipal(); entity.put("userName", userName); create(entity); //
	 * addLogs(entity); // ermsg.setMessage(" Saved Successfully"); return new
	 * ResponseEntity<Object>(HttpStatus.CREATED);
	 * 
	 * }
	 */

	public void create(LinkedHashMap<String, Object> entity) {
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(entity);
		getService().save(gson.fromJson(jsonElement, getEntity()));

	}

	public void addRemarkLogs(HashMap<String, Object> entity) {

//		Login l = applicationCache.getLoginDetail(getPrincipal());
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", entity.get("id"));
 		Logs log = lservice.uniqueSearch(constrains);
		log = new Logs();

		log.setAction("Added");
		log.setIpaddrs(getIp());
		String s = getEntity().getName();
		String[] arrOfStr = s.split(".", 25);
		for (String a : arrOfStr)
			log.setEntity(a + " in " + entity.get("deducteeForm") );
		Gson gason = new Gson();
		String json = gason.toJson(entity);
		log.setDate(new Date(System.currentTimeMillis()));
		log.setUsername(getPrincipal());

		lservice.save(log);

	}
	
	public void addLogs(HashMap<String, Object> entity) {

//		Login l = applicationCache.getLoginDetail(getPrincipal());
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", entity.get("id"));
		Logs log = lservice.uniqueSearch(constrains);
		log = new Logs();

		log.setAction("Added");
		log.setIpaddrs(getIp());
		String s = getEntity().getName();
		String[] arrOfStr = s.split(".", 25);
		for (String a : arrOfStr)
			log.setEntity(a);
		Gson gason = new Gson();
		String json = gason.toJson(entity);
		log.setDate(new Date(System.currentTimeMillis()));
		log.setUsername(getPrincipal());

		lservice.save(log);

	}
	// ------------------- Get Detail ---------------------------------

	@RequestMapping(value = "/detail/{fy}/{branchCode}/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController(@PathVariable K id, @PathVariable String fy,
			@PathVariable Long branchCode) {
		// verify the clientId authorization
		if (!"admin".equals(getBranchCode())) {
			Long b=1L;
			try {
				b =Long.valueOf(getBranchCode());
			}catch (Exception e) {
				// TODO: handle exception
			}
			branchCode=b;
		}else {
		}
		
		try {
			return new ResponseEntity<>(getDetail(id, fy, branchCode), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	public void addLogsU(HashMap<String, Object> entity) {

//		Login l = applicationCache.getLoginDetail(getPrincipal());
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", Long.valueOf(entity.get("id").toString()));
		constrains.put("clientId", 1);
		Logs log = lservice.uniqueSearch(constrains);
		log = new Logs();

		log.setAction("Update"
				);
		log.setIpaddrs(getIp());
		String s = getEntity().getName();
		String[] arrOfStr = s.split(".", 25);
		for (String a : arrOfStr)
			log.setEntity(a);
		Gson gason = new Gson();
		String json = gason.toJson(entity);
		log.setDate(new Date(System.currentTimeMillis()));
		log.setUsername(getPrincipal());

		lservice.save(log);

	}

	public abstract Class<E> getEntity();

	public abstract S getService();


	public Object getDetail(K id, String fy, Long branchCode) {
		// TODO Auto-generated method stub
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", id);
		constrains.put("fy", fy);
		if (!"admin".equals(getBranchCode())) {
			Long b=1L;
			try {
				b =Long.valueOf(getBranchCode());
			}catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		}else {
			constrains.put("branchCode", branchCode);
		}
		
		
		return getService().uniqueSearch(constrains);
	}
	
	// ------------------- Generate Excel ---------------------------------
	
	
	@RequestMapping(value = "/generateExcel/{json}/**", method = RequestMethod.GET)
	public void generateExcel(@PathVariable String json, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			final String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
			final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
					.toString();

			String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);

			String searchParam;
			if (null != arguments && !arguments.isEmpty()) {
				searchParam = json + '/' + arguments;
			} else {
				searchParam = json;
			}
			ObjectMapper mapper = new ObjectMapper();

			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			

			// convert JSON string to Map
			map = mapper.readValue(searchParam, new TypeReference<Map<String, String>>() {
			});
			if (!"admin".equals(getBranchCode())) {
				Long b=1L;
				try {
					b =Long.valueOf(getBranchCode());
				}catch (Exception e) {
					// TODO: handle exception
				}
				map.put("branchCode", b);
			}else{
				if(map.containsKey("branchCode")) {
					Long b=1L;
					try {
						b =Long.valueOf(map.get("branchCode").toString());
					}catch (Exception e) {
						// TODO: handle exception
					}
					map.put("branchCode", b);
				}
			}
			String address = getService().createUserExcel(map);

			File file = new File(address);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
			Path p = file.toPath();
			OutputStream out;
			try {

				out = response.getOutputStream();
				out.flush();
				Files.copy(p, out);

				out.close();
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
//				logger.error("Error in downloading the " + entity.get("type") + ".xlsx file", e);
			}

//			try {
//				StringBuilder fw = new StringBuilder();
//				for (User user : users) {
//					fw.append(user.getId() + ";" + user.getName() + ";" + user.getUserName() + ";"
//							+ user.getDateOfSignup() + "\n");
//				}
//				 File file = File.createTempFile("temp", null);
//				 FileInputStream is =new FileInputStream(file);
//				 FileWriter fw1 = new FileWriter(file);
//				 fw1.append(fw.toString());
//				 fw1.flush();
//				 fw1.close();
//				
//				String mimeType= URLConnection.guessContentTypeFromName("myFile.txt");
//				response.setContentType(mimeType);
//				response.addHeader("Content-Disposition","attachment; filename=\"" + "myFile.csv" + "\"");
//				FileCopyUtils.copy(is, response.getOutputStream());
//                response.getOutputStream().flush();
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Excel Can Not Export.");
//			logger.error("Error in listALL", e);
		}
	}


}
