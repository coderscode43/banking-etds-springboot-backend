package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.RequestCorrection;
import domain.in.rjsa.service.RequestCorrectionService;

@Controller
@RequestMapping("/apirequestCorrection")
public class RequestCorrectionController extends AbstractController<Long, RequestCorrection, RequestCorrectionService>{
 @Autowired
 RequestCorrectionService service;
	@Override
	public RequestCorrectionService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<RequestCorrection> getEntity() {
		// TODO Auto-generated method stub
		return RequestCorrection.class;
	}

}
