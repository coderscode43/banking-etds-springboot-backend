package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.model.tds.CLIENTDETAILS;
import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
import domain.in.rjsa.model.tds.EFILLINGLOGIN;
import domain.in.rjsa.model.tds.GOVERNMENTDETAILS;
import domain.in.rjsa.model.tds.RESPONSIBLEPERSONEDETAILS;
import domain.in.rjsa.model.tds.TRACESSLOGIN;
import domain.in.rjsa.model.wrapper.DeductorDetailWrapper;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.CLIENTDETAILSService;
import domain.in.rjsa.service.DEDUCTORDETAILSService;
import domain.in.rjsa.service.EFILLINGLOGINService;
import domain.in.rjsa.service.GOVERNMENTDETAILSService;
import domain.in.rjsa.service.RESPONSIBLEPERSONEDETAILSService;
import domain.in.rjsa.service.TRACESSLOGINService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apideductorDetails")
public class DEDUCTORDETAILSController extends AbstractControllerTaxo<String, DEDUCTORDETAILS, DEDUCTORDETAILSService> {
	
	
	
@Autowired
DEDUCTORDETAILSService service;
@Autowired
RESPONSIBLEPERSONEDETAILSService rService;
@Autowired
ApplicationCache applicationCache;
@Autowired
GOVERNMENTDETAILSService gService;
@Autowired
CLIENTDETAILSService cdService;
@Autowired
BranchService bService;
@Autowired
TRACESSLOGINService tlService;
@Autowired
EFILLINGLOGINService efService;

	@Override
	public DEDUCTORDETAILSService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<DEDUCTORDETAILS> getEntity() {		
		// TODO Auto-generated method stub
		return DEDUCTORDETAILS.class;
	}
	
     @Override	
	public Object getDetail(String id, Long clientId) {
		// TODO Auto-generated method stub
    	String tan =id;
		DeductorDetailWrapper ew = new DeductorDetailWrapper();
		DEDUCTORDETAILS deductor = service.getByKey(tan);
		ew.setDeductorDetails(deductor);
		RESPONSIBLEPERSONEDETAILS reaponsible = rService.getByKey(tan);
		ew.setRespersonDetails(reaponsible);
		GOVERNMENTDETAILS govt = gService.getByKey(tan);
		ew.setGovtDetails(govt);
		CLIENTDETAILS cd = cdService.getByKey(tan);
		ew.setClientDetail(cd);
		TRACESSLOGIN tl=tlService.getByKey(tan);
		ew.setTraces(tl);
		EFILLINGLOGIN ef=efService.getByKey(tan);
		ew.setEfiling(ef);
		HashMap<String, Object> map = new HashMap<>();
		map.put("clientId", clientId);
		ew.setListBranch(bService.search(map, clientId));
		
		return ew;
	}
     
    
    
}
