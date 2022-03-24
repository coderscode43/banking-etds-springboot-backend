package domain.in.rjsa.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.tds.LDC;
import domain.in.rjsa.service.LDCService;

@Controller
@RequestMapping("/apildc")
public class LDCController extends AbstractControllerTaxo<String, LDC, LDCService>{

	@Autowired
	LDCService service;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public LDCService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<LDC> getEntity() {
		// TODO Auto-generated method stub
		return LDC.class;
	}
	@RequestMapping(value = "/list/{fy}/{branchCode}/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(@PathVariable String fy, @PathVariable Long branchCode,
			HttpServletRequest request) {
		HashMap<String, Object> constrains = new HashMap<>();
//		constrains.put("fy",fy);
//		constrains.put("branchCode",branchCode);
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
	
	public List<?> getList(String fy, Long branchCode, int pageNo, int resultPerPage) {
		HashMap<String, Object> constrains = new HashMap<>();
//		constrains.put("fy", fy);
//		constrains.put("branchCode", branchCode);
		return getService().findAll(constrains, pageNo, resultPerPage);
	}
	@RequestMapping(value = "/search/{fy}/{branchCode}/{json}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable String fy, @PathVariable Long branchCode, @PathVariable String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map = mapper.readValue(json, new TypeReference<Map<String, String>>() {
			});
			for (String key : map.keySet()) {
				if (key.endsWith("Id")) {
					map.put(key, Long.valueOf((String) map.get(key)));
				}
			}
			//map.put("fy", fy);
			//map.put("branchCode", branchCode);
			return new ResponseEntity<>(getSearch(map), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<?> getSearch(LinkedHashMap<String, Object> map) {
		Login l = applicationCache.getLoginDetail(getPrincipal());
		return getService().search(map);
	}
	@RequestMapping(value = "/searchEntity/{fy}/{branchCode}", method = RequestMethod.POST)
	public ResponseEntity<?> searchEntity(@RequestBody LinkedHashMap<String, Object> map, @PathVariable String fy,
			@PathVariable Long branchCode) {
		try {
			/*
			 * map.put("fy", fy); map.put("branchCode", branchCode);
			 */
			return new ResponseEntity<>(getSearchEntity(map), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public LDC getSearchEntity(LinkedHashMap<String, Object> map) {
		return (LDC) getService().uniqueSearch(map);
	}

}

