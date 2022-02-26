package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.tds.GH15RETURNSTATUS;
import domain.in.rjsa.service.GH15RETURNSTATUSService;

@Controller
@RequestMapping("/apighReturnStatus")
public class GH15RETURNSTATUSController
		extends AbstractControllerTaxo<Long, GH15RETURNSTATUS, GH15RETURNSTATUSService> {

	@Autowired
	GH15RETURNSTATUSService service;

	@Override
	public GH15RETURNSTATUSService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<GH15RETURNSTATUS> getEntity() {
		// TODO Auto-generated method stub
		return GH15RETURNSTATUS.class;
	}

	@RequestMapping(value = "/list/{fy}/{branchCode}/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count(@PathVariable String fy, @PathVariable String branchCode,
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

	public List<?> getList(String fy, String branchCode, int pageNo, int resultPerPage) {
		HashMap<String, Object> constrains = new HashMap<>();
//		constrains.put("fy", fy);
//		constrains.put("branchCode", branchCode);
		return getService().findAll(constrains, pageNo, resultPerPage);
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

}
