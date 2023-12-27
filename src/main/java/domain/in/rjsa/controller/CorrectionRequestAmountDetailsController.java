package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.CorrectionRequestAmountDetails;
import domain.in.rjsa.service.CorrectionRequestAmountDetailsService;

@Controller
@RequestMapping("/apicorrectionRequestAmountDetails")
public class CorrectionRequestAmountDetailsController extends AbstractControllerForm<Long, CorrectionRequestAmountDetails, CorrectionRequestAmountDetailsService>{

	
	@Autowired
	CorrectionRequestAmountDetailsService service;
	
	@Override
	public CorrectionRequestAmountDetailsService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<CorrectionRequestAmountDetails> getEntity() {
		// TODO Auto-generated method stub
		return CorrectionRequestAmountDetails.class;
	}
}
