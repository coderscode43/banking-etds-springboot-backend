package domain.in.rjsa.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
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
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.Ajax;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.LogsService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apibranch")
public class BranchController extends AbstractController {

	@Autowired
	BranchService service;

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	ApplicationCache applicationCache;

	@Autowired
	LogsService lservice;

	/* pranay */
	@RequestMapping(value = "/ajax", method = RequestMethod.POST)
	public ResponseEntity<?> ajax(@RequestBody Ajax ajax) {
		// verify the clientId authorization
		try {
			List<String> list = getAjax(ajax.getName(), ajax.getTerm());
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
//			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<String> getAjax(String name, String term) {
		// TODO Auto-generated method stub
		return service.ajax(name, term);
	}

	@RequestMapping(value = "/search/get/{pageNo}/{resultPerPage}/{json}/**", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable String json, HttpServletRequest request, @PathVariable int pageNo,
			@PathVariable int resultPerPage) {
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
				Long b = 1L;
				try {
					b = Long.valueOf(getBranchCode());
				} catch (Exception e) {
					// TODO: handle exception
				}
				map.put("branchCode", b);
			}
			if(map.containsKey("TAN")) {
				String TAN = (map.get("TAN").toString().split(Pattern.quote("-"),-1))[0];
				map.put("TAN", TAN);
			}
			Long count = service.findSearchCount(map);
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

	public List<?> getSearch(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return service.search(map, pageNo, resultPerPage);
	}

	// ------------------- Add Entity ---------------------------------
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity) {
		logger.info("Creating new Return instance");

		if ("admin".equals(getBranchCode())) {
			// service.saveNewUser(entity.get("userName").toString(),
			// entity.get("password").toString(),
			// Long.valueOf(entity.get("branchCode").toString()));
			entity.remove("userName");
			entity.remove("password");
			create(entity);
			addLogs(entity);

			return new ResponseEntity<Object>(HttpStatus.CREATED);
		}
		return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);

	}

	public void create(LinkedHashMap<?, ?> entity) {
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(entity);
		ObjectMapper om = new ObjectMapper();
		try {
			service.save(om.readValue(gson.toJson(entity), Branch.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ------------------- Get Detail ---------------------------------
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController(@PathVariable Long id) {
		// verify the clientId authorization

		try {
			return new ResponseEntity<>(getDetail(id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	public Object getDetail(Long id) {
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
		return service.uniqueSearch(constrains);
	}

	// ------------------- Update Entity ---------------------------------
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody LinkedHashMap<?, ?> entity, HttpServletResponse response,
			UriComponentsBuilder ucBuilder) {
		if ("admin".equals(getBranchCode())) {
			FieldErrorDTO ermsg = new FieldErrorDTO();
			Long id = Long.valueOf(entity.get("id").toString());
			update(entity, id);
			ermsg.setMessage("Updated Successfully");
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
	}

	public void update(LinkedHashMap<?, ?> entity, Long id) {
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(entity);
		service.update(gson.fromJson(jsonElement, Branch.class));
	}

	/* END-pranay */

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

	@RequestMapping(value = "/list/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(HttpServletRequest request) {
		// verify the clientId authorization
//			applicationCache.getUserAuthorised();
		HashMap<String, Object> constrains = new HashMap<>();
		if (!"admin".equals(getBranchCode())) {

			if (!"admin".equals(getBranchCode())) {
				Long b = 1L;
				try {
					b = Long.valueOf(getBranchCode());
				} catch (Exception e) {
					// TODO: handle exception
				}
				constrains.put("branchCode", b);
			}
		}

		try {
			Long count = service.findallCount(constrains);
			List<?> list = getList(constrains, 0, 100);
			ListCount send = new ListCount();
			send.setCount(count);
			send.setEntities(list);
			return new ResponseEntity<>(send, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/list/get/{pageNo}/{resultPerPage}", method = RequestMethod.GET)
	public ResponseEntity<?> listAll(HttpServletRequest request, @PathVariable int pageNo,
			@PathVariable int resultPerPage) {
		// verify the clientId authorization
//		applicationCache.getUserAuthorised();
		String mapping = request.getPathInfo();

		try {
			List<?> list = getList(pageNo, resultPerPage);

			return new ResponseEntity<>(list, HttpStatus.OK);
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
		}

		return service.findAll(constrains, pageNo, resultPerPage);
	}

	public List<?> getList(HashMap<String, Object> constrains, int pageNo, int resultPerPage) {
		if (!"admin".equals(getBranchCode())) {
			Long b = 1L;
			try {
				b = Long.valueOf(getBranchCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		}
		return service.findAll(constrains, pageNo, resultPerPage);
	}

	public void addLogs(HashMap<String, Object> entity) {

		// Login l = applicationCache.getLoginDetail(getPrincipal());
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", entity.get("id"));
		Logs log = lservice.uniqueSearch(constrains);
		log = new Logs();

		log.setAction("Added");
		log.setIpaddrs(getIp());
		String s = getEntity().getName();
		String[] arrOfStr = s.split(".", 27);
		for (String a : arrOfStr)
			log.setEntity(a);
		Gson gason = new Gson();
		String json = gason.toJson(entity);
		log.setDate(new Date(System.currentTimeMillis()));
		log.setUsername(getPrincipal());

		lservice.save(log);

	}

	public Class<Branch> getEntity() {
		return Branch.class;
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
				searchParam = json + '/' + arguments;
			} else {
				searchParam = json;
			}
			ObjectMapper mapper = new ObjectMapper();

			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

			// convert JSON string to Map
			map = mapper.readValue(searchParam, new TypeReference<Map<String, String>>() {
			});
			adminValidation(map);

			String address = service.createUserExcel(map);

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
//				logger.error("Error in listALL", e);
		}
	}

}
