package domain.in.rjsa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.service.ChallanDetailsService;

@Controller
@RequestMapping("/apiChallanDetails")
public class ChallanDetailsController {

	@Autowired
	ChallanDetailsService service;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
}
