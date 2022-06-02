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

import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.form.Model;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.service.LogsService;
import domain.in.rjsa.service.ServiceInterfaceTaxo;
import domain.in.rjsa.web.ApplicationCache;

public abstract class AbstractControllerTaxo<K extends Serializable, E extends Model, S extends ServiceInterfaceTaxo<K, E>>
		extends AbstractController {

	public abstract S getService();

	@Autowired
	ApplicationCache applicationCache;

	@Autowired
	LogsService lservice;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	// ------------------- List Entity ---------------------------------

	@RequestMapping(value = "/list/get/{pageNo}/{resultPerPage}", method = RequestMethod.GET)
	public ResponseEntity<?> listAll(HttpServletRequest request, @PathVariable int pageNo,
			@PathVariable int resultPerPage) {
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
			Long b=1L;
			try {
				b =Long.valueOf(getBranchCode());
			}catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		}else {
		}

		return getService().findAll(constrains, pageNo, resultPerPage);
	}

	// ------------------- Count Entity ---------------------------------

	@RequestMapping(value = "/list/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(HttpServletRequest request) {
		HashMap<String, Object> constrains = new HashMap<>();

		String mapping = request.getPathInfo();
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
			
			
			
			
			return getService().findAll(constrains, pageNo, resultPerPage);
		}

	// ------------------- Search Entities ---------------------------------

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

	public List<?> getSearch(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
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
			Long b=1L;
			try {
				b =Long.valueOf(getBranchCode());
			}catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		}else {
		}
		constrains.put("TAN", tan);
		return getService().uniqueSearch(constrains);
	}

	public void addLogsU(HashMap<String, Object> entity) {

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
		Gson gason = new Gson();
		String json = gason.toJson(entity);
		log.setDate(new Date(System.currentTimeMillis()));
		log.setUsername(getPrincipal());

		lservice.save(log);

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


}
