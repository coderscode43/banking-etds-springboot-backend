package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.form.RODetails;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.RODetailsService;

@Controller
@RequestMapping("/apiRODetails")
public class RODetailsController extends AbstractControllerForm<Long, RODetails, RODetailsService> {
	@Autowired
	RODetailsService service;
	@Autowired
	BranchService bService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public RODetailsService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<RODetails> getEntity() {
		// TODO Auto-generated method stub
		return RODetails.class;
	}

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

	// ------------------- Search Entities ---------------------------------

	@RequestMapping(value = "/search/{fy}/{branchCode}/{pageNo}/{resultPerPage}/{json}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable String fy, @PathVariable Long branchCode, @PathVariable String json,
			@PathVariable int pageNo, @PathVariable int resultPerPage) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map = mapper.readValue(json, new TypeReference<LinkedHashMap<String, Object>>() {
			});
			if (map.containsKey("branchCode")) {
				branchCode = Long.valueOf(map.get("branchCode").toString());
				map.put("branchCode", branchCode);
			}
			if (map.containsKey("roCode")) {
				Long roCode = Long.valueOf(map.get("roCode").toString());
				map.put("roCode", roCode);
			}
			map.put("fy", fy);

			if (!"admin".equals(getBranchCode())) {
				Long b = 1L;
				try {
					b = Long.valueOf(getBranchCode());
				} catch (Exception e) {
					// TODO: handle exception
				}
				map.put("branchCode", b);
			} else {
				map.put("branchCode", branchCode);
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
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public HashMap<String, Object> getDetail(Long id) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<>();
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
		map.put("roDetails", getService().uniqueSearch(constrains));
		constrains.remove("id");
		RODetails roDetail = service.getByKey(id);
		constrains.put("roCode", roDetail.getRoCode());
		map.put("branch", bService.findAll(constrains, 0, 100));
		return map;
	}

}
