package domain.in.rjsa.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.model.fy.Regular24QDeductee;
import domain.in.rjsa.model.fy.Regular27QDeductee;
import domain.in.rjsa.model.fy.Remark;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular27QDeducteeService;
import domain.in.rjsa.service.RemarkService;

@Controller
@RequestMapping("/apiform27QDeductee")
public class Regular27QDeducteeController
		extends AbstractControllerFY<Long, Regular27QDeductee, Regular27QDeducteeService> {

	@Autowired
	Regular27QDeducteeService service;
	@Autowired
	RemarkService rService;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	

	@Override
	public Regular27QDeducteeService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular27QDeductee> getEntity() {
		// TODO Auto-generated method stub
		return Regular27QDeductee.class;
	}

	@RequestMapping(value = "/detail/{fy}/{branchCode}/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController(@PathVariable Long id, @PathVariable String fy,
			@PathVariable String branchCode) {
		// verify the clientId authorization
		try {
			return new ResponseEntity<>(getDetail(id, fy, branchCode), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	public HashMap<String, Object> getDetail(Long id, String fy, String branchCode) {
		// TODO Auto-generated method stub
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", id);
		constrains.put("fy", fy);
		constrains.put("branchCode", branchCode);
		HashMap<String, Object> map = new HashMap<>();
		map.put("deductee",getService().uniqueSearch(constrains));
		constrains.remove("id", id);
		constrains.put("deducteeId",id);
		List<Remark> remark = rService.findForm(constrains, 0, 100,"27Qform");
		map.put("remark",remark);
		return map; 
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTestimonial(@RequestBody LinkedHashMap<?, ?> entity) {
		try {
			Long id = Long.valueOf(entity.get("id").toString());
			Regular27QDeductee form27Q = service.getByKey(id);
			form27Q.setResolved(false);
			service.update(form27Q);
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("fy", form27Q.getFy());
			map.put("deducteeId", form27Q.getId());
			map.put("deducteeForm", "27Qform");
			map.put("remark", "Resolved");
			String timeStamp = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss").format(Calendar.getInstance().getTime());
			map.put("dateTime", timeStamp);
			map.put("userName", getPrincipal());
			map.put("branchCode", form27Q.getBranchCode());
			rService.saveRemark(map);
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// ------------------- Search Single Entity ---------------------------------
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

//				Long count = getService().findSearchCount(map);
//				List<?> list = getSearch(map, pageNo, resultPerPage);
//				ListCount send = new ListCount();
//				send.setCount(count);
//				send.setEntities(list);

				return new ResponseEntity<>(getSearch(map, pageNo, resultPerPage), HttpStatus.OK);
			} catch (Exception e) {
				logger.error("Error in listALL", e);
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

		public List<?> getSearch(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
			// TODO Auto-generated method stub
			return getService().search(map);
		}
	
	
}
