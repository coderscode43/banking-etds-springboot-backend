package domain.in.rjsa.controller;

import java.io.File;
import java.io.OutputStream;
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
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.form.UserDetails;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.service.LogsService;
import domain.in.rjsa.service.UserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
				addLogs("Delete");
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

	@RequestMapping(value = "/search/get/{pageNo}/{resultPerPage}/{json}", method = RequestMethod.GET)
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
					String decodedString = URLDecoder.decode(arguments, "UTF-8");decodedString = decodedString.replace(", \"", "\"");searchParam = json + '/' + decodedString;
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
			create(entity);

			addLogs("Add");
			// ermsg.setMessage(" Saved Successfully");
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);

	}

	public void create(LinkedHashMap<String, Object> entity) {
		UserDetails u = null;
		try {
			String jsonElement = mapper.writeValueAsString(entity);
			u = mapper.readValue(jsonElement, UserDetails.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getService().save(u);
		applicationCache.adminRefresh(u.getEmployeeId());

	}

	public void addLogs(String Action) {
		Logs log = new Logs();
		log.setAction(Action);
		log.setIpaddrs(getIp());
		log.setLogsDate(new Date(System.currentTimeMillis()));
		log.setUsername(getPrincipal());
		log.setEntity(Action + " User");
		lservice.save(log);

	}

	// ------------------- Generate Excel ---------------------------------

	@RequestMapping(value = "/generateExcel/{json}/**", method = RequestMethod.GET)
	public void generateExcel(@PathVariable String json, HttpServletRequest request, HttpServletResponse response) {
		try {
			final String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
			final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
					.toString();

			String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);

			String searchParam;
			if (null != arguments && !arguments.isEmpty()) {
				String decodedString = URLDecoder.decode(arguments, "UTF-8");decodedString = decodedString.replace(", \"", "\"");searchParam = json + '/' + decodedString;
			} else {
				searchParam = json;
			}
			ObjectMapper mapper = new ObjectMapper();

			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

			// convert JSON string to Map
			map = mapper.readValue(searchParam, new TypeReference<LinkedHashMap<String, Object>>() {
			});
			if (!"admin".equals(getBranchCode())) {
				Long b = 1L;
				try {
					b = Long.valueOf(getBranchCode());
				} catch (Exception e) {
					// TODO: handle exception
				}
				map.put("branchCode", b);
			} else {
				if (map.containsKey("branchCode")) {
					Long b = 1L;
					try {
						b = Long.valueOf(map.get("branchCode").toString());
					} catch (Exception e) {
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
//			logger.error("Error in listALL", e);
		}
	}

}
