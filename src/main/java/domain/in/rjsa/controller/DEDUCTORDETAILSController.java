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
public class DEDUCTORDETAILSController extends AbstractTDSController<String, DEDUCTORDETAILS, DEDUCTORDETAILSService> {
	
	
	
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
     
    
     @Override   
     public void update(LinkedHashMap<String, Object> entity) {
			Gson gson = new Gson();
			JsonElement jsonElement = gson.toJsonTree(entity);
			DeductorDetailWrapper dd = gson.fromJson(jsonElement, DeductorDetailWrapper.class);
			if(dd.getDeductorDetails()!=null && dd.getDeductorDetails().getTAN()!=null)
			{
	  		service.update(dd.getDeductorDetails());
			}
			else
			{
				DEDUCTORDETAILS deductor = new DEDUCTORDETAILS();
				deductor.setTAN(dd.getDeductorDetails().getTAN());
				deductor.setFLATORFLOAR(dd.getDeductorDetails().getFLATORFLOAR());
				service.update(deductor);
				
			}
			
			if(dd.getClientDetail()!=null && dd.getClientDetail().getTAN()!=null)
			{
	  		cdService.update(dd.getClientDetail());
			}
			else
			{
			
			}
			
			if(dd.getRespersonDetails()!=null && dd.getRespersonDetails().getTAN()!=null)
			{
	  		rService.update(dd.getRespersonDetails());
			}
			else
			{
				
			}
			
			if(dd.getGovtDetails()!=null && dd.getGovtDetails().getTAN()!=null)
			{
	  		gService.update(dd.getGovtDetails());
			}
			else
			{
				
			}
			
			if(dd.getTraces()!=null && dd.getTraces().getTAN()!=null)
			{
				tlService.update(dd.getTraces());	
			}
			else {
				
			}
			
			if(dd.getEfiling()!=null && dd.getEfiling()!=null)
			{
				efService.update(dd.getEfiling());
			}
			else {
				
			}
		}
}
