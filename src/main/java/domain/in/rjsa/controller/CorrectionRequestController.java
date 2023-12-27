package domain.in.rjsa.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.model.form.CorrectionRemarks;
import domain.in.rjsa.model.form.CorrectionRequest;
import domain.in.rjsa.model.form.CorrectionRequestAmountDetails;
import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.form.UserDetails;
import domain.in.rjsa.service.CorrectionRemarksService;
import domain.in.rjsa.service.CorrectionRequestAmountDetailsService;
import domain.in.rjsa.service.CorrectionRequestService;

@Controller
@RequestMapping("/apicorrectionRequest")
public class CorrectionRequestController
		extends AbstractControllerForm<Long, CorrectionRequest, CorrectionRequestService> {

	@Autowired
	CorrectionRequestService service;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CorrectionRemarksService rService;
	@Autowired
	CorrectionRequestAmountDetailsService crService;

	@Override
	public CorrectionRequestService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<CorrectionRequest> getEntity() {
		// TODO Auto-generated method stub
		return CorrectionRequest.class;
	}

	// ------------------- Get List ---------------------------------
	@RequestMapping(value = "/list/{fy}/{branchCode}/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(@PathVariable String fy, @PathVariable Long branchCode, HttpServletRequest request) {
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

	public List<?> getList(String fy, Long branchCode, int pageNo, int resultPerPage) {
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
		// constrains.put("fy", fy);
		constrains.put("branchCode", branchCode);
		return getService().getList(constrains, pageNo, resultPerPage);
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
		return getService().getList(constrains, pageNo, resultPerPage);
	}

	// ------------------- Get Detail ---------------------------------

	@RequestMapping(value = "/detail/{fy}/{branchCode}/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController(@PathVariable Long id, @PathVariable String fy,
			@PathVariable Long branchCode) {
		// verify the clientId authorization
		try {
			return new ResponseEntity<>(getDetail(id, fy, branchCode), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	public Object getDetail(Long id, String fy, Long branchCode) {
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
		return getService().getDetail(constrains, id, getPrincipal(), branchCode);

	}

	// ------------------- Get Add ---------------------------------
	@RequestMapping(value = "/add/{fy}/{branchCode}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity, @PathVariable String fy,
			@PathVariable String branchCode) {
		logger.info("Creating new Return instance");

		adminValidation(entity);
		create(entity);
		addLogs("Add");
		// ermsg.setMessage(" Saved Successfully");
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	public void create(LinkedHashMap<String, Object> entity) {
		Gson gson = new Gson();
		// Login l = applicationCache.getLoginDetail(getPrincipal());
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		entity.put("date", timeStamp);
		service.autoGenerateTicketNumber(entity);
		entity.put("makerBy", getPrincipal());
		entity.put("status", "Pending Checker Approval");
		entity.put("rejectStatus", "0");
		JsonElement jsonElement = gson.toJsonTree(entity);
		getService().save(gson.fromJson(jsonElement, getEntity()));
		getService().saveAmount(entity);

	}

	// ------------------- Get Search ---------------------------------
	@RequestMapping(value = "/search/{fy}/{branchCode}/{pageNo}/{resultPerPage}/{json}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable String fy, @PathVariable Long branchCode, @PathVariable int pageNo,
			@PathVariable int resultPerPage, @PathVariable String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map = mapper.readValue(json, new TypeReference<Map<String, String>>() {
			});
			adminValidation(map);
			// map.put("fy", fy);
			map.put("branchCode", branchCode);
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
}
