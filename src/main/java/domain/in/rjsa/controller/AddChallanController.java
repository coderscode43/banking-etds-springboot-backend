package domain.in.rjsa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.AddChallan;
import domain.in.rjsa.service.AddChallanService;
import domain.in.rjsa.service.CorrectionRemarksService;
import domain.in.rjsa.service.CorrectionRequestAmountDetailsService;
import domain.in.rjsa.service.Regular24QDeducteeService;
import domain.in.rjsa.service.Regular26QDeducteeService;
import domain.in.rjsa.service.Regular27EQDeducteeService;
import domain.in.rjsa.service.Regular27QDeducteeService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/apiaddChallan")
public class AddChallanController
		extends AbstractControllerForm<Long, AddChallan, AddChallanService> {

	@Autowired
	AddChallanService service;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CorrectionRemarksService rService;
	@Autowired
	CorrectionRequestAmountDetailsService crService;
	@Autowired
	Regular24QDeducteeService r24qService;
	@Autowired
	Regular26QDeducteeService r26qService;
	@Autowired
	Regular27EQDeducteeService r27eqService;
	@Autowired
	Regular27QDeducteeService r27qService;

	@Autowired
	AddChallanService challanService;

	@Override
	public AddChallanService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<AddChallan> getEntity() {
		// TODO Auto-generated method stub
		return AddChallan.class;
	}

	@RequestMapping(value = "/downloadDoc/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> downloadDocument(HttpServletResponse response, @PathVariable Long id) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
			service.downloadDocument(id, response);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			ermsg.setEntityName("Upload Document");
			ermsg.setExceptionMsg(e.getMessage());
			return new ResponseEntity<>(ermsg, HttpStatus.BAD_REQUEST);
		}

	}
}
