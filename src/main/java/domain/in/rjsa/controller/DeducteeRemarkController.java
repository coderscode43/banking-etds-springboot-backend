package domain.in.rjsa.controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.DeducteeRemark;
import domain.in.rjsa.service.DeducteeRemarkService;

@Controller
@RequestMapping("/apideducteeremark")
public class DeducteeRemarkController extends AbstractControllerForm<Long, DeducteeRemark, DeducteeRemarkService> {

	@Autowired
	DeducteeRemarkService service;

	@Override
	public DeducteeRemarkService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<DeducteeRemark> getEntity() {
		// TODO Auto-generated method stub
		return DeducteeRemark.class;
	}

	@Override
	@PostMapping(value = "/add")
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity) {
		service.save(entity);
		return new ResponseEntity<DeducteeRemark>(HttpStatus.OK);
	}
}
