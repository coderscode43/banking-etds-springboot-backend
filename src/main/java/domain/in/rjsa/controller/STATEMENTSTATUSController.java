package domain.in.rjsa.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.tds.STATEMENTSTATUS;
import domain.in.rjsa.service.STATEMENTSTATUSService;

@Controller
@RequestMapping("/apistatementStatus")
public class STATEMENTSTATUSController extends AbstractControllerTaxo<Long, STATEMENTSTATUS, STATEMENTSTATUSService>{

	@Autowired
	STATEMENTSTATUSService service;
	@Override
	public STATEMENTSTATUSService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<STATEMENTSTATUS> getEntity() {
		// TODO Auto-generated method stub
		return STATEMENTSTATUS.class;
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/search/get/{pageNo}/{resultPerPage}/{json}/**", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable String json, HttpServletRequest request,
			@PathVariable int pageNo, @PathVariable int resultPerPage) {
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
			
			if(map.containsKey("FY")) {
				 String FY = map.get("FY").toString();
	        	  String[] parts = FY.split("-", 2);
	        	  String fy = parts[0]+parts[1];
	        	  map.put("FY",fy);
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


}
