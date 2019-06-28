package domain.in.rjsa.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Regular26QDeductee;
import domain.in.rjsa.model.form.RequestCorrection;
import domain.in.rjsa.model.wrapper.RequestCorrectionWrapper;
import domain.in.rjsa.service.Regular26QDeducteeService;
import domain.in.rjsa.service.RequestCorrectionService;

@Controller
@RequestMapping("/apirequestCorrection")
public class RequestCorrectionController extends AbstractController<Long, RequestCorrection, RequestCorrectionService>{
 @Autowired
 RequestCorrectionService service;
 @Autowired
 Regular26QDeducteeService r26QSercive;
 
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
	
	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		RequestCorrectionWrapper ew = new RequestCorrectionWrapper();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		Branch b=applicationCache.getBranch(id);
		LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
		constrains.put("clientId", l.getClientId());
		constrains.put("challanSrNo", id);
		List<Regular26QDeductee> r26Q=r26QSercive.search(constrains);
		ew.setRegular26QDeducteeList(r26Q);
		
		RequestCorrection reqCorr = service.getByKey(id);
		ew.setReqCorrection(reqCorr);
		
		return ew;
	}

}
