package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.form.RegularReturn;
import domain.in.rjsa.service.RegularReturnRemarkService;
import domain.in.rjsa.service.RegularReturnService;

@Controller
@RequestMapping("/apiregularReturn")
public class RegularReturnController extends AbstractControllerForm<Long, RegularReturn, RegularReturnService> {

	@Autowired
	RegularReturnService service;

	@Autowired
	RegularReturnRemarkService rService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public RegularReturnService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<RegularReturn> getEntity() {
		// TODO Auto-generated method stub
		return RegularReturn.class;
	}

	// ------------------- Get Detail ---------------------------------

	@RequestMapping(value = "/detail/{fy}/{roCode}/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController(@PathVariable Long id, @PathVariable String fy,
			@PathVariable String roCode) {
		// verify the clientId authorization
		try {
			return new ResponseEntity<>(getDetail(id, fy, roCode), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	public HashMap<String, Object> getDetail(Long id) {
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
		HashMap<String, Object> map = new HashMap<>();
		map.put("RR", getService().uniqueSearch(constrains));
		constrains.remove("id", id);
		constrains.put("regularReturnId", id);
		map.put("RRR", rService.search(constrains));
		return map;
	}

	public Object getDetail(Long id, String fy, String roCode) {
		// TODO Auto-generated method stub
		HashMap<String, Object> constrains = new HashMap<>();
		if (!"admin".equals(getBranchCode())) {
			String b = "";
			try {
				b = getBranchCode();
			} catch (Exception e) {
				// TODO: handle exception
			}
			constrains.put("branchCode", b);
		}
		return getService().getDetail(constrains, id, getPrincipal(), roCode);

	}

	// ------------------- Get List ---------------------------------
	@RequestMapping(value = "/list/{fy}/{branchCode}/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(@PathVariable String fy, @PathVariable String branchCode,
			HttpServletRequest request) {
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
		constrains.put("branchCode", branchCode);
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

	@RequestMapping(value = "/list/{fy}/{branchCode}/get/{pageNo}/{resultPerPage}", method = RequestMethod.GET)
	public ResponseEntity<?> listAll(@PathVariable String fy, @PathVariable String branchCode,
			HttpServletRequest request, @PathVariable int pageNo, @PathVariable int resultPerPage) {
		try {

			if (!"admin".equals(getBranchCode())) {
				Long b = 1L;
				try {
					b = Long.valueOf(getBranchCode());
				} catch (Exception e) {
					// TODO: handle exception
				}
				branchCode = b.toString();
			} else {
			}

			List<?> list = getList(fy, branchCode, pageNo, resultPerPage);

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<?> getList(String fy, String branchCode, int pageNo, int resultPerPage) {
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
		constrains.put("branchCode", branchCode);
		return getService().getList(constrains, pageNo, resultPerPage);
	}

	// ------------------- Get Search ---------------------------------
	@RequestMapping(value = "/search/{fy}/{branchCode}/{pageNo}/{resultPerPage}/{json}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable String fy, @PathVariable Long branchCode, @PathVariable int pageNo,
			@PathVariable int resultPerPage, @PathVariable String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map = mapper.readValue(json, new TypeReference<LinkedHashMap<String, Object>>() {
			});
			adminValidation(map);
			map.put("branchCode", branchCode.toString());
			Long count = getService().findallCount(map);
			List<?> list = getSearch(map, 0, 100);
			ListCount send = new ListCount();
			send.setCount(count);
			send.setEntities(list);
			return new ResponseEntity<>(send, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public void create(LinkedHashMap<String, Object> entity) {
		RegularReturn rr = null;
		try {
			String jsonNode = mapper.writeValueAsString(entity);
			rr = mapper.readValue(jsonNode, getEntity());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getService().addRegularReturn(rr, getPrincipal());
	}

}
