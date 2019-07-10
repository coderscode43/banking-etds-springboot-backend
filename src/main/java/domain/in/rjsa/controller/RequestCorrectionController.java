package domain.in.rjsa.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Regular24Q4Challan;
import domain.in.rjsa.model.form.Regular24Q4Deductee;
import domain.in.rjsa.model.form.Regular24Q4Salary;
import domain.in.rjsa.model.form.Regular26QChallan;
import domain.in.rjsa.model.form.Regular26QDeductee;
import domain.in.rjsa.model.form.Regular27QChallan;
import domain.in.rjsa.model.form.Regular27QDeductee;
import domain.in.rjsa.model.form.RequestCorrection;
import domain.in.rjsa.model.tds.CLIENTDETAILS;
import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
import domain.in.rjsa.model.tds.GOVERNMENTDETAILS;
import domain.in.rjsa.model.tds.RESPONSIBLEPERSONEDETAILS;
import domain.in.rjsa.model.wrapper.RequestCorrectionWrapper;
import domain.in.rjsa.service.CLIENTDETAILSService;
import domain.in.rjsa.service.DEDUCTORDETAILSService;
import domain.in.rjsa.service.GOVERNMENTDETAILSService;
import domain.in.rjsa.service.RESPONSIBLEPERSONEDETAILSService;
import domain.in.rjsa.service.Regular24Q4ChallanService;
import domain.in.rjsa.service.Regular24Q4DeducteeService;
import domain.in.rjsa.service.Regular24Q4SalaryService;
import domain.in.rjsa.service.Regular26QChallanService;
import domain.in.rjsa.service.Regular26QDeducteeService;
import domain.in.rjsa.service.Regular27QChallanService;
import domain.in.rjsa.service.Regular27QDeducteeService;
import domain.in.rjsa.service.RequestCorrectionService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apirequestCorrection")
public class RequestCorrectionController extends AbstractController<Long, RequestCorrection, RequestCorrectionService>{
 @Autowired
 RequestCorrectionService service;
 @Autowired
 Regular26QDeducteeService r26QService;
 
 @Autowired
 Regular26QChallanService r26QChallanService;
 
 @Autowired
 Regular24Q4ChallanService r24Q4ChallanService;
 
 @Autowired
 Regular24Q4DeducteeService r24Q4DeducteeService;
 
 @Autowired
 Regular24Q4SalaryService r24Q4SalaryService;
 
 @Autowired
 DEDUCTORDETAILSService dservice;
 @Autowired
 RESPONSIBLEPERSONEDETAILSService rService;
	
	@Autowired
	ApplicationCache applicationCache;
	
	@Autowired
	GOVERNMENTDETAILSService gService;
	@Autowired
	CLIENTDETAILSService cdService;
	
	@Autowired
	Regular27QDeducteeService r27QdService;
	
	@Autowired
	Regular27QChallanService r27QcService;
 
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
		Branch b= applicationCache.getBranch(id);
		String tan=b.getTan();
		LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
		//	constrains.put("clientId", l.getClientId());
			constrains.put("id", id);
			List<Regular26QDeductee> reg26Qd = r26QService.search(constrains,l.getClientId());		
			ew.setRegular26QDeducteeList(reg26Qd);
			
			List<Regular26QChallan> reg26Qc = r26QChallanService.search(constrains,l.getClientId());		
			ew.setRegular26QChallanList(reg26Qc);
			
			List<Regular24Q4Challan> reg24Qc = r24Q4ChallanService.search(constrains,l.getClientId());		
			ew.setRegular24Q4ChallanList(reg24Qc);
			
			List<Regular24Q4Deductee> reg24Qd = r24Q4DeducteeService.search(constrains,l.getClientId());		
			ew.setRegular24Q4DeducteeList(reg24Qd);
			
			List<Regular24Q4Salary> reg24Qs = r24Q4SalaryService.search(constrains,l.getClientId());		
			ew.setRegular24Q4SalaryList(reg24Qs);
			
			List<Regular27QChallan> reg27Qc = r27QcService.search(constrains,l.getClientId());		
			ew.setRegular27QChallan(reg27Qc);
			
			List<Regular27QDeductee> reg27Qd = r27QdService.search(constrains,l.getClientId());		
			ew.setRegular27QDeductee(reg27Qd);
			
			DEDUCTORDETAILS deductor = dservice.getByKey(tan);
			ew.setDeductorDetails(deductor);
			RESPONSIBLEPERSONEDETAILS reaponsible = rService.getByKey(tan);
			ew.setRespersonDetails(reaponsible);
			GOVERNMENTDETAILS govt = gService.getByKey(tan);
			ew.setGovtDetails(govt);
			CLIENTDETAILS cd = cdService.getByKey(tan);
			ew.setClientDetail(cd);
		
		    RequestCorrection reqCorr = service.getByKey(id);
		    ew.setReqCorrection(reqCorr);
	
		
		return ew;
	}

}
