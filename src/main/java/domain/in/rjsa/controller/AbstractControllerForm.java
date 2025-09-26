package domain.in.rjsa.controller;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.Ajax;
import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.form.Model;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.service.CorrectionRequestService;
import domain.in.rjsa.service.LogsService;
import domain.in.rjsa.service.ServiceInterfaceForm;
import domain.in.rjsa.service.UserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class AbstractControllerForm<K extends Serializable, E extends Model, S extends ServiceInterfaceForm<K, E>>
		extends AbstractController {

	public abstract S getService();

	@Autowired
	UserDetailsService userDetailsService;
	CorrectionRequestService service;

	@Autowired
	LogsService lservice;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	// ------------------- List Entity ---------------------------------

	@RequestMapping(value = "/list/get/{pageNo}/{resultPerPage}", method = RequestMethod.GET)
	public ResponseEntity<?> listAll(HttpServletRequest request, @PathVariable int pageNo,
			@PathVariable int resultPerPage) {

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
		// verify the clientId authorization
//			applicationCache.getUserAuthorised();
		HashMap<String, Object> constrains = new HashMap<>();
//		constrains.put("employeeId", applicationCache.getLoginDetail(getPrincipal()).getEmployeeId());

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
			if (map.containsKey("branchCode")) {
				Long branchCode = Long.valueOf((String) map.get("branchCode"));
				map.put("branchCode", branchCode);
			}
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
		return getService().uniqueSearch(map);
	}

	// ------------------- Add Entity ---------------------------------

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
			logger.info("################# Creating new Return instance");
			adminValidation(entity);
			create(entity);
			addLogs("Add");
			// ermsg.setMessage(" Saved Successfully");
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (CustomException cEx) {
			logger.error("################# Error adding details: ", cEx);
			ermsg.setMessage("Error");
			ermsg.setEntityName(getEntity().getSimpleName());
			ermsg.setExceptionMsg(cEx.getMessage());
			return new ResponseEntity<Object>(ermsg, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("################# Error adding details: ", e);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	public void create(LinkedHashMap<String, Object> entity) {
		try {
			String json = mapper.writeValueAsString(entity);
			getService().save(mapper.readValue(json, getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addLogs(String Action) {
		try {
			Logs log = new Logs();
			log.setAction(Action);
			log.setIpaddrs(getIp());
			String s = getEntity().getName().replace(getEntity().getPackage().getName() + ".", "");
			log.setLogsDate(new Date(System.currentTimeMillis()));
			log.setUsername(getPrincipal());
			log.setEntity(Action + " " + s);
			lservice.save(log);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

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

	public Object getDetail(K id) {
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
		constrains.put("id", id);
		return getService().uniqueSearch(constrains);
	}

	// ------------------- Update Entity ---------------------------------

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody LinkedHashMap<String, Object> entity, HttpServletResponse response,
			UriComponentsBuilder ucBuilder) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
//		Login l = applicationCache.getLoginDetail(getPrincipal());
		Object o = getDetail((K) Long.valueOf(entity.get("id").toString()));
		ObjectMapper oMapper = new ObjectMapper();
		HashMap<String, Object> map = oMapper.convertValue(o, HashMap.class);
		adminValidation(entity);
		update(entity);
		addLogsU("Update");
		ermsg.setMessage(" Updated Successfully");
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	public void update(LinkedHashMap<String, Object> entity) {
		try {
			String json = mapper.writeValueAsString(entity);
			getService().update(mapper.readValue(json, getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addLogsU(String Action) {

		Logs log = new Logs();
		log.setAction(Action);
		log.setIpaddrs(getIp());
		String s = getEntity().getName().replace(getEntity().getPackage().getName() + ".", "");
		log.setEntity(Action + " " + s);
		log.setLogsDate(new Date(System.currentTimeMillis()));
		log.setUsername(getPrincipal());
		lservice.save(log);
	}

	// ------------------- ajax Entities ---------------------------------

	@RequestMapping(value = "/ajax", method = RequestMethod.POST)
	public ResponseEntity<?> ajax(@RequestBody Ajax ajax) {
		// verify the clientId authorization
		try {

			List<?> list = getAjax(ajax.getName(), ajax.getTerm());
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<?> getAjax(String name, String term) {
		// TODO Auto-generated method stub
		return getService().ajax(name, term);
	}

	// ------------------- Generate Excel ---------------------------------

	@RequestMapping(value = "/generateExcel/{json}", method = RequestMethod.GET)
	public void generateExcel(@PathVariable(required = false) String json, HttpServletRequest request, HttpServletResponse response)
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

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Excel Can Not Export.");
//				logger.error("Error in listALL", e);
		}
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

}
