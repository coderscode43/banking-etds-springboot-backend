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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.form.Model;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.service.LogsService;
import domain.in.rjsa.service.ServiceInterfaceTaxo;
import domain.in.rjsa.web.ApplicationCache;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

public abstract class AbstractControllerTaxo<K extends Serializable, E extends Model, S extends ServiceInterfaceTaxo<K, E>>
		extends AbstractController {

	public abstract S getService();

	@Autowired
	ApplicationCache applicationCache;

	@Autowired
	LogsService lservice;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/*------------------- Add data In Table -------------------------------*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addData(@Valid @RequestBody String json, HttpServletRequest request) {
		try {
//			Login login = applicationCache.getLoginBasedOnAuth(request.getHeader("auth").toString());
//			if (login != null) {
			getService().addData(json);
//			}
			return new ResponseEntity<String>("Data Added Successfully!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}

	// ------------------- List Entity ---------------------------------

	@RequestMapping(value = "/list/get/{pageNo}/{resultPerPage}", method = RequestMethod.GET)
	public ResponseEntity<?> listAll(HttpServletRequest request, @PathVariable int pageNo,
			@PathVariable int resultPerPage) {
		String mapping = request.getPathInfo();
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

		return getService().findAll(constrains, pageNo, resultPerPage);
	}

	// ------------------- Count Entity ---------------------------------

	@RequestMapping(value = "/list/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(HttpServletRequest request) {
		HashMap<String, Object> constrains = new HashMap<>();

		String mapping = request.getPathInfo();
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

		return getService().findAll(constrains, pageNo, resultPerPage);
	}

	// ------------------- Search Entities ---------------------------------

	@RequestMapping(value = "/search/get/{pageNo}/{resultPerPage}/{json}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable String json, HttpServletRequest request, @PathVariable int pageNo,
			@PathVariable int resultPerPage) {
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
			map = mapper.readValue(searchParam, new TypeReference<LinkedHashMap<String, Object>>() {
			});
			if (map.containsKey("TAN")) {
				String TAN = (map.get("TAN").toString().split(Pattern.quote("-"), -1))[0];
				map.put("TAN", TAN);
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
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<?> getSearch(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return getService().search(map, pageNo, resultPerPage);
	}

	// ------------------- Search Single Entity ---------------------------------

	@RequestMapping(value = "/searchEntity", method = RequestMethod.POST)
	public ResponseEntity<?> searchEntity(@RequestBody LinkedHashMap<String, Object> map) {
		// verify the clientId authorization
		try {

			adminValidation(map);
			return new ResponseEntity<>(getSearchEntity(map), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public E getSearchEntity(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return getService().uniqueSearch(map);
	}

	// ------------------- Get Detail ---------------------------------

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController(@PathVariable K id) {
		// verify the clientId authorization
		try {
			return new ResponseEntity<>(getDetail(id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	public Object getDetail(K tan) {
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
		constrains.put("TAN", tan);
		return getService().uniqueSearch(constrains);
	}

	public void addLogsU(HashMap<String, Object> entity) {
		try {

//		    	Login l = applicationCache.getLoginDetail(getPrincipal());
//				HashMap<String, Object>constrains= new HashMap<>();
//				constrains.put("id", Long.valueOf(entity.get("id").toString()));
//				constrains.put("clientId",l.getClientId());
//				Logs log = lservice.uniqueSearch(constrains);			
			Logs log = new Logs();

			log.setAction("Updated");
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

	// ------------------- Generate Excel ---------------------------------

	@RequestMapping(value = "/generateExcel/{json}/**", method = RequestMethod.GET)
	public void generateExcel(@PathVariable String json, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
			map = mapper.readValue(searchParam, new TypeReference<LinkedHashMap<String, Object>>() {
			});
			if (map.containsKey("TAN")) {
				String TAN = (map.get("TAN").toString().split(Pattern.quote("-"), -1))[0];
				map.put("TAN", TAN);
			}
			adminValidation(map);
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
//					logger.error("Error in downloading the " + entity.get("type") + ".xlsx file", e);
			}

//				try {
//					StringBuilder fw = new StringBuilder();
//					for (User user : users) {
//						fw.append(user.getId() + ";" + user.getName() + ";" + user.getUserName() + ";"
//								+ user.getDateOfSignup() + "\n");
//					}
//					 File file = File.createTempFile("temp", null);
//					 FileInputStream is =new FileInputStream(file);
//					 FileWriter fw1 = new FileWriter(file);
//					 fw1.append(fw.toString());
//					 fw1.flush();
//					 fw1.close();
//					
//					String mimeType= URLConnection.guessContentTypeFromName("myFile.txt");
//					response.setContentType(mimeType);
//					response.addHeader("Content-Disposition","attachment; filename=\"" + "myFile.csv" + "\"");
//					FileCopyUtils.copy(is, response.getOutputStream());
//	                response.getOutputStream().flush();
			//
//				} catch (IOException e) {
//					e.printStackTrace();
//				}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Excel Can Not Export.");
//				logger.error("Error in listALL", e);
		}
	}

	public abstract Class<E> getEntity();

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
