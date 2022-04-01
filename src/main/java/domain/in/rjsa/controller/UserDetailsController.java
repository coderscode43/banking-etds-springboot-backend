package domain.in.rjsa.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.form.UserDetails;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.model.fy.Ticket;
import domain.in.rjsa.service.LogsService;
import domain.in.rjsa.service.UserDetailsService;

@Controller
@RequestMapping("/apiuserDetails")
public class UserDetailsController extends AbstractController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	UserDetailsService service;

	@Autowired
	LogsService lservice;

	public UserDetailsService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	// ------------------- Delete Entity ---------------------------------

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> delete(@PathVariable String id) {
		// verify the clientId authorization
		try {
			if ("admin".equals(getBranchCode())) {
				getService().deleteT(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/list/get/{pageNo}/{resultPerPage}", method = RequestMethod.GET)
	public ResponseEntity<?> listAll(HttpServletRequest request, @PathVariable int pageNo,
			@PathVariable int resultPerPage) {
		try {
			if ("admin".equals(getBranchCode())) {
				List<?> list = getList(pageNo, resultPerPage);
				return new ResponseEntity<>(list, HttpStatus.OK);
			}

			return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	public List<?> getList(int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		HashMap<String, Object> constrains = new HashMap<>();

		return getService().findAll(constrains, pageNo, resultPerPage);
	}

	// ------------------- Count Entity ---------------------------------

	@RequestMapping(value = "/list/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(HttpServletRequest request) {
		// verify the clientId authorization
//				applicationCache.getUserAuthorised();
		HashMap<String, Object> constrains = new HashMap<>();
//			constrains.put("employeeId", applicationCache.getLoginDetail(getPrincipal()).getEmployeeId());
		if ("admin".equals(getBranchCode())) {

			try {
				Long count = getService().findallCount(constrains);
				List<?> list = getList(0, 100);
				ListCount send = new ListCount();
				send.setCount(count);
				send.setEntities(list);
				return new ResponseEntity<>(send, HttpStatus.OK);
			} catch (Exception e) {
				logger.error("Error in listALL", e);
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/search/get/{pageNo}/{resultPerPage}/{json}/**", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable String json, HttpServletRequest request, @PathVariable int pageNo,
			@PathVariable int resultPerPage) {
		if ("admin".equals(getBranchCode())) {
			try {
				final String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE)
						.toString();
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
				for (String key : map.keySet()) {
					if (key.endsWith("Code")) {
						map.put(key, Long.valueOf((String) map.get(key)));
					}
				}
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
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);

	}

	public List<?> getSearch(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return getService().search(map);
	}

	public List<?> getSearch(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		// Login l = applicationCache.getLoginDetail(getPrincipal());
//			map.put("employeeId", l.getEmployeeId());
		return getService().search(map);
	}

	// ------------------- Add Entity ---------------------------------

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity) {
		if ("admin".equals(getBranchCode())) {
			// FieldErrorDTO ermsg=new FieldErrorDTO();
			logger.info("Creating new Return instance");
			Ticket ticket = new Ticket();
			create(entity);
			addLogs(entity);
			// ermsg.setMessage(" Saved Successfully");
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);

	}

	public void create(LinkedHashMap<String, Object> entity) {
		Gson gson = new Gson();
		// Login l = applicationCache.getLoginDetail(getPrincipal());

		JsonElement jsonElement = gson.toJsonTree(entity);

		getService().save(gson.fromJson(jsonElement, UserDetails.class));

	}

	public void addLogs(HashMap<String, Object> entity) {

		// Login l = applicationCache.getLoginDetail(getPrincipal());
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", entity.get("id"));
		Logs log = lservice.uniqueSearch(constrains);
		log = new Logs();
		log.setAction("Added ");
		log.setIpaddrs(getIp());
		String s = "User Details";
		String[] arrOfStr = s.split(".", 25);
		for (String a : arrOfStr)
			log.setEntity(a);
		Gson gason = new Gson();
		String json = gason.toJson(entity);
		log.setDate(new Date(System.currentTimeMillis()));
		log.setUsername(getPrincipal());
		lservice.save(log);

	}

}
