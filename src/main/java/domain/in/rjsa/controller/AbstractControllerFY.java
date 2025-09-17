
package domain.in.rjsa.controller;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.poi.ss.formula.functions.T;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Model;
import domain.in.rjsa.model.fy.DeducteeRemark;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.DeducteeRemarkService;
import domain.in.rjsa.service.LogsService;
import domain.in.rjsa.service.ServiceInterfaceFY;
import domain.in.rjsa.web.ApplicationCache;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

public abstract class AbstractControllerFY<K extends Serializable, E extends Model, S extends ServiceInterfaceFY<K, E>>
		extends AbstractController {
	
	@Autowired
	ApplicationCache applicationCache;

	@Autowired
	BranchService bservice;

	@Autowired
	LogsService lservice;

	@Autowired
	DeducteeRemarkService deducteeService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	// ------------------- List Entity ---------------------------------
	public List<?> getList(String fy, Long branchCode, int pageNo, int resultPerPage) {
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("fy", fy);

		if (!"admin".equals(getBranchCode())) {
			Long b = 1L;
			try {
				b = Long.valueOf(getBranchCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		} else {
			constrains.put("branchCode", branchCode);
		}

		return getService().findall(constrains, pageNo, resultPerPage);
	}

	// ------------------- Count Entity ---------------------------------
	@RequestMapping(value = "/list/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(HttpServletRequest request) {
		HashMap<String, Object> constrains = new HashMap<>();

		if (!"admin".equals(getBranchCode())) {
			Long b = 1L;
			try {
				b = Long.valueOf(getBranchCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		} else {
		}

		try {
			Long count = getService().findallCount(constrains);
			List<?> list = getList(0, 100);
			ListCount send = new ListCount();
			send.setCount(count);
			send.setEntities(list);
			return new ResponseEntity<>(send, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<?> getList(int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		HashMap<String, Object> constrains = new HashMap<>();

		if (!"admin".equals(getBranchCode())) {
			Long b = 1L;
			try {
				b = Long.valueOf(getBranchCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		} else {
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
			Long count = getService().findallCount(new HashMap<>());
			List<?> list = getList(pageNo, resultPerPage);
			
			ListCount data = new ListCount();
			data.setCount(count);
			data.setEntities(list);

			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/list/{fy}/{branchCode}/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(@PathVariable String fy, @PathVariable Long branchCode, HttpServletRequest request) {
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("fy", fy);

		if (!"admin".equals(getBranchCode())) {
			Long b = 1L;
			try {
				b = Long.valueOf(getBranchCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		} else {
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

	// ------------------- Search Entities ---------------------------------

	@RequestMapping(value = "/search/{fy}/{branchCode}/{pageNo}/{resultPerPage}/{json}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable String fy, @PathVariable Long branchCode, @PathVariable String json,
			@PathVariable int pageNo, @PathVariable int resultPerPage) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map = mapper.readValue(json, new TypeReference<LinkedHashMap<String, Object>>() {
			});
			if (map.containsKey("branchCode")) {
				branchCode = Long.valueOf(map.get("branchCode").toString());
				map.put("branchCode", branchCode);
			}
			if (map.containsKey("roCode")) {
				Long roCode = Long.valueOf(map.get("roCode").toString());
				map.put("roCode", roCode);
			}
			map.put("fy", fy);

			if (!"admin".equals(getBranchCode())) {
				Long b = 1L;
				try {
					b = Long.valueOf(getBranchCode());
				} catch (Exception e) {
					// TODO: handle exception
				}
				map.put("branchCode", b);
			} else {
				map.put("branchCode", branchCode);
			}
			adminValidation(map);
			Long count = getService().findallCount(map);
			List<?> list = getSearch(map, pageNo, resultPerPage);
			ListCount send = new ListCount();
			send.setCount(count);
			send.setEntities(list);

			return new ResponseEntity<>(send, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<?> getSearch(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return getService().search(map, pageNo, resultPerPage);
	}

	// ------------------- Search Single Entity ---------------------------------
	@RequestMapping(value = "/searchEntity/{fy}/{branchCode}", method = RequestMethod.POST)
	public ResponseEntity<?> searchEntity(@RequestBody LinkedHashMap<String, Object> map, @PathVariable String fy,
			@PathVariable Long branchCode) {
		try {
			map.put("fy", fy);
			if (!"admin".equals(getBranchCode())) {
				Long b = 1L;
				try {
					b = Long.valueOf(getBranchCode());
				} catch (Exception e) {
					// TODO: handle exception
				}
				map.put("branchCode", b);
			} else {
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
		try {
			String json = mapper.writeValueAsString(entity);
			getService().save(mapper.readValue(json, getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addRemarkLogs(HashMap<String, Object> entity) {

		try {
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
				log.setEntity(a + " in " + entity.get("deducteeForm"));
			String json = mapper.writeValueAsString(entity);
			log.setLogsDate(new Date(System.currentTimeMillis()));
			log.setUsername(getPrincipal());

			lservice.save(log);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addLogs(String Action) {

		Logs log = new Logs();
		log.setAction(Action);
		log.setIpaddrs(getIp());
		String s = getEntity().getName().replace(getEntity().getPackage().getName() + ".", "");
		log.setLogsDate(new Date(System.currentTimeMillis()));
		log.setUsername(getPrincipal());
		log.setEntity(Action + " " + s);
		lservice.save(log);

	}
	// ------------------- Get Detail ---------------------------------

	@RequestMapping(value = "/detail/{fy}/{branchCode}/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController(@PathVariable K id, @PathVariable String fy,
			@PathVariable Long branchCode) {
		// verify the clientId authorization
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

		try {
			return new ResponseEntity<>(getDetail(id, fy, branchCode), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	public void addLogsU(HashMap<String, Object> entity) {
		try {

//		Login l = applicationCache.getLoginDetail(getPrincipal());
//		HashMap<String, Object> constrains = new HashMap<>();
//		constrains.put("id", Long.valueOf(entity.get("id").toString()));
//		constrains.put("clientId", 1);
//		Logs log = lservice.uniqueSearch(constrains);
			Logs log = new Logs();

			log.setAction("Update");
			log.setIpaddrs(getIp());
			String s = getEntity().getName();
			String[] arrOfStr = s.split(".", 25);
			for (String a : arrOfStr)
				log.setEntity(a);
			String json = mapper.writeValueAsString(entity);
			log.setLogsDate(new Date(System.currentTimeMillis()));
			log.setUsername(getPrincipal());

			lservice.save(log);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public abstract Class<E> getEntity();

	public abstract S getService();

	public Object getDetail(K id, String fy, Long branchCode) {
		// TODO Auto-generated method stub
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", id);
		constrains.put("fy", fy);
		if (!"admin".equals(getBranchCode())) {
			Long b = 1L;
			try {
				b = Long.valueOf(getBranchCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		} else {
			constrains.put("branchCode", branchCode);
		}

		return getService().uniqueSearch(constrains);
	}

	// ------------------- Generate Excel ---------------------------------

	@RequestMapping(value = "/generateExcel/{json}/**", method = RequestMethod.GET)
	public ResponseEntity<?> generateExcel(@PathVariable String json, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			final String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
			final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
					.toString();

			String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);

			String searchParam;
			if (null != arguments && !arguments.isEmpty()) {
				String decodedString = URLDecoder.decode(arguments, "UTF-8");
				decodedString = decodedString.replace(", \"", "\"");
				searchParam = json + '/' + decodedString;
			} else {
				searchParam = json;
			}
			ObjectMapper mapper = new ObjectMapper();

			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

			// convert JSON string to Map
			map = mapper.readValue(json, new TypeReference<LinkedHashMap<String, Object>>() {
			});
			if (map.containsKey("TAN")) {
				String TAN = (map.get("TAN").toString().split(Pattern.quote("-"), -1))[0];
				map.put("TAN", TAN);
			}

			if (map.containsKey("quarter")) {
				if (map.get("quarter").toString().equalsIgnoreCase("ALL QUARTER")) {
					map.remove("quarter");
				}
			}

			adminValidation(map);
			Long count = getService().findallCount(map);
			try {
//				if (count >= 1000000) {
//					throw new CustomException("The number of Transaction is Greater Than 10 Lakhs");
//				} else {
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
//				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new ResponseEntity<>("The number of Transaction is Greater Than 10 Lakhs.",
						HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Excel Can Not Export.", HttpStatus.BAD_REQUEST);
//			logger.error("Error in listALL", e);
		}

	}

	@RequestMapping(value = "/addDeducteeRemark", method = RequestMethod.PUT)
	public ResponseEntity<?> addDeducteeRemark(@RequestBody LinkedHashMap<String, Object> entity,
			HttpServletResponse response) {
		if (!"admin".equals(getBranchCode())) {
			return new ResponseEntity<T>(HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/updateDeductee/{id}/{deducteeId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDeductee(@PathVariable Long id, @PathVariable Long deducteeId,
			@RequestBody String entity) {
		try {
			FieldErrorDTO ermsg = new FieldErrorDTO();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", id);
			map.put("DEDUCTEEID", deducteeId);
			DeducteeRemark remark = deducteeService.uniqueSearch(map);

			if (remark.getADDEDBY().equals(getPrincipal())) {
				ermsg.setExceptionMsg("Maker Cannot be the Checker");
				return new ResponseEntity<>(ermsg, HttpStatus.FORBIDDEN);
			}

			LinkedHashMap<String, Object> entityMap = mapper.readValue(entity, LinkedHashMap.class);
			entityMap.put("resolved", false);
			// Update Deductee
			update(entityMap);

			// change DeducteeRemark status
			remark.setSTATUS("Approved");
			remark.setAPPROVEDON(new Date());
			remark.setAPPROVEDBY(getPrincipal());
			deducteeService.update(remark);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/rejectDeductee/{id}/{deducteeformid}/{rejectRemark}", method = RequestMethod.PUT)
	public ResponseEntity<?> rejectDeductee(@PathVariable Long id, @PathVariable Long deducteeformid,
			@PathVariable String rejectRemark, @RequestBody String entity) {
		try {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", id);
		map.put("DEDUCTEEID", deducteeformid);
		DeducteeRemark remark = deducteeService.uniqueSearch(map);

		if (remark.getADDEDBY().equals(getPrincipal())) {
			ermsg.setExceptionMsg("Maker Cannot be the Checker");
			return new ResponseEntity<>(ermsg, HttpStatus.FORBIDDEN);
		}

		// Update Old Remark
		remark.setSTATUS("Rejected");
		remark.setAPPROVEDBY("-");
		deducteeService.update(remark);

		// Create New Remark
		DeducteeRemark newDeductee = new DeducteeRemark();
		newDeductee.setDEDUCTEEID(deducteeformid);
		newDeductee.setADDEDBY(getPrincipal());
		newDeductee.setDATETIME(new Date());
		newDeductee.setREMARK(rejectRemark);
		newDeductee.setSTATUS("Rejected");
		newDeductee.setAPPROVEDBY("-");
		deducteeService.save(newDeductee);

		// Update Deductee Status
		LinkedHashMap<String, Object> entityMap = mapper.readValue(entity, LinkedHashMap.class);
		entityMap.put("resolved", false);
		update(entityMap);
		} catch(Exception e) {
			e.printStackTrace();
		}

		// change DeducteeRemark status

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody LinkedHashMap<String, Object> entity, HttpServletResponse response,
			UriComponentsBuilder ucBuilder, String fy, Long branchCode) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
//		Login l = applicationCache.getLoginDetail(getPrincipal());
		HashMap<String, Object> constrains = new HashMap<>();
		if (!"admin".equals(getBranchCode())) {

			return new ResponseEntity<T>(HttpStatus.BAD_REQUEST);
		}
		Object o = getDetail((K) Long.valueOf(entity.get("id").toString()), fy, branchCode);
		ObjectMapper oMapper = new ObjectMapper();
		HashMap<String, Object> map = oMapper.convertValue(o, HashMap.class);
		adminValidation(entity);
		update(entity);
		addLogsU(entity);
		ermsg.setMessage(" Updated Successfully");
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	public void update(LinkedHashMap<String, Object> entity) {

//		Gson gson = new Gson();
//		// Login l = applicationCache.getLoginDetail(getPrincipal());
//		JsonElement jsonElement = gson.toJsonTree(entity);
//		getService().update(gson.fromJson(jsonElement, getEntity()));
//		Gson gson = new Gson();
//		Login l = applicationCache.getLoginDetail(getPrincipal());
//		if (entity.containsKey("clientId")) {
//			entity.put("clientId", l.getClientId());
//		}
		// if (entity.containsKey("employeeId")) {
//		entity.put("employeeId",  l.getEmployeeId());
		// }
		// JsonElement jsonElement = gson.toJsonTree(entity);

		// getEntity from controller and validate that with validate method in
		// contorller and message from Service
		// getService().update(gson.fromJson(jsonElement, getEntity()));
		return;
	}
	
	
	/*------------------- Add data In Table -------------------------------*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addData(@Valid @RequestBody String json, HttpServletRequest request) {
		try {
			Login login = applicationCache.getLoginBasedOnAuth(request.getHeader("auth").toString());
			if (login != null) {
				getService().addData(json);
			}
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	

	/*------------------- Get data through directAPI -------------------------------*/
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public ResponseEntity<?> get(@RequestBody String json, HttpServletRequest request) {
		try {
			HashMap<String, Object> dataMap = processRequest(json);
			List<E> dataList = getService().search(dataMap);
			return new ResponseEntity<List<E>>(dataList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception Occurred!", e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/*------------------- Update data through directAPI -------------------------------*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(@RequestBody String json, HttpServletRequest request) {
		try {
			HashMap<String, Object> dataMap = processRequest(json);
			List<E> entityList = getService().search(dataMap);

			if (entityList.isEmpty()) {
				return new ResponseEntity<List<E>>(entityList, HttpStatus.OK);
			}
			updateData(dataMap, entityList);

			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception Occurred!", e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	private void updateData(HashMap<String, Object> dataMap, List<E> entityList) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		boolean flag = false;

		HashMap<String, Object> setMap = (HashMap<String, Object>) dataMap.get("set");

		for (E entity : entityList) {
			List<Field> fields = Arrays.asList(entity.getClass().getDeclaredFields());

			if (!flag) {
				for (Map.Entry<String, Object> entry : setMap.entrySet()) {
					String key = entry.getKey();
					String stringValue = entry.getValue().toString();
					Field field = getFieldType(fields, key);

					if (field != null) {
						Object value = convertValue(stringValue, field.getType());

						if (value != null) {
							setMap.put(key, value);
						}
					}
				}
				flag = true;
			}

			mapper.updateValue(entity, setMap);
			getService().update(entity);
		}

	}

	private HashMap<String, Object> processRequest(String json) {
		JSONParser jsonParser = new JSONParser();	
		HashMap<String, Object> dataMap = new LinkedHashMap<String, Object>();

		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
			Set<String> keySet = jsonObject.keySet();

			for (String key : keySet) {
				if (key.equalsIgnoreCase("and") || key.equalsIgnoreCase("or") || key.equalsIgnoreCase("set")
						|| key.equalsIgnoreCase("orderBy")) {
					dataMap.putAll(processObject(jsonObject, key));

				} else if (key.equalsIgnoreCase("distinct")) {
					dataMap.putAll(processDistinct(jsonObject, key));

				} else {
					throw new CustomException("Invalid key!");
				}
			}

		} catch (ParseException e) {
			logger.error("Exception Occurred!", e);
		}
		return dataMap;

	}

	private HashMap<String, Object> processObject(JSONObject jsonObject, String key) {
		HashMap<String, Object> conditionsMap = new HashMap<String, Object>();
		JSONArray jsonArray = (JSONArray) jsonObject.get(key);

		for (Object object : jsonArray) {
			String condition = (String) object;
			String[] parts = condition.split(Pattern.quote("^"), -1);

			if (key.equalsIgnoreCase("set")) {
				if (parts.length == 2) {
					HashMap<String, Object> setMap = (HashMap<String, Object>) conditionsMap.getOrDefault("set",
							new HashMap<String, Object>());
					setMap.put(parts[0], parts[1]);
					conditionsMap.put("set", setMap);
				}

			} else if (key.equalsIgnoreCase("orderBy")) {
				if (parts.length == 2) {
					String operator = parts[0];

					if (!conditionsMap.containsKey(key)) {
						HashMap<String, Object> operatorMap = new LinkedHashMap<String, Object>();
						conditionsMap.put(key, operatorMap);
					}

					HashMap<String, Object> operatorMap = (HashMap<String, Object>) conditionsMap.get(key);

					if (!operatorMap.containsKey(operator)) {
						List<String> operatorList = new ArrayList<String>();
						operatorMap.put(operator, operatorList);
					}

					((ArrayList) operatorMap.get(operator)).add(parts[1]);
				}

			} else {
				if (parts.length == 3) {
					String operator = parts[1];

					if (!conditionsMap.containsKey(key)) {
						HashMap<String, Object> operatorMap = new LinkedHashMap<String, Object>();
						conditionsMap.put(key, operatorMap);
					}

					HashMap<String, Object> operatorMap = (HashMap<String, Object>) conditionsMap.get(key);

					if (!operatorMap.containsKey(operator)) {
						List<HashMap<String, Object>> operatorList = new ArrayList<HashMap<String, Object>>();
						operatorMap.put(operator, operatorList);
					}

					HashMap<String, Object> conditionEntry = new HashMap<String, Object>();
					conditionEntry.put(parts[0], parts[2]);

					((List<HashMap<String, Object>>) operatorMap.get(operator)).add(conditionEntry);
				}
			}
		}

		return conditionsMap;
	}

//	private HashMap<String, Object> processOrderBy(JSONObject jsonObject, String key) {
//		HashMap<String, Object> orderByMap = new HashMap<String, Object>();
//		String orderBy = jsonObject.get(key).toString();
//		String[] parts = orderBy.split(Pattern.quote("^"), -1);
//
//		orderByMap.put(parts[0], parts[1]);
//		return orderByMap;
//	}

	private HashMap<String, Object> processDistinct(JSONObject jsonObject, String key) {
		HashMap<String, Object> distinctMap = new HashMap<String, Object>();
		JSONArray jsonArray = (JSONArray) jsonObject.get(key);

		if (!jsonArray.isEmpty()) {
			List<String> asList = jsonArray;
			distinctMap.put(key, asList);
		}
		return distinctMap;
	}

	private Field getFieldType(List<Field> fields, String key) {
		return fields.stream().filter(f -> f.getName().equalsIgnoreCase(key)).findFirst().get();
	}

	private Object convertValue(String stringValue, Class<?> fieldType) {
		if (fieldType == String.class) {
			return stringValue;
		}
		if (fieldType == Date.class) {
			return parseDate(stringValue);
		}
		return null;
	}

	private Date parseDate(String dateAsString) {
		Date date = null;

		try {
			if (!(dateAsString.equals(""))) {
//			"Sep 2, 2024, 12:00:00 AM"
				String[] data = dateAsString.split(Pattern.quote(","), -1);
				String[] dayNmonth = data[0].split(Pattern.quote(" "), -1);

				String month = dayNmonth[0];
				String day = dayNmonth[1];
				String year = data[1];

				String formatttedDateAsString = year + "-" + getMonthNum(month) + "-"
						+ (day.length() == 1 ? "0" + day : day);

				return new SimpleDateFormat("yyyy-MM-dd").parse(formatttedDateAsString);
			} else {
				return date;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Invalid date format provided!");
		}
	}

	private String getMonthNum(String month) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Jan", "01");
		map.put("Feb", "02");
		map.put("Mar", "03");
		map.put("Apr", "04");
		map.put("May", "05");
		map.put("Jun", "06");
		map.put("Jul", "07");
		map.put("Aug", "08");
		map.put("Sep", "09");
		map.put("Oct", "10");
		map.put("Nov", "11");
		map.put("Dec", "12");

		return map.get(month);
	}

}
