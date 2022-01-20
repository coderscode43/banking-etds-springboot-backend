package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.VendorMaster;
import domain.in.rjsa.model.wrapper.VendorMasterWrapper;
import domain.in.rjsa.service.VendorMasterService;
import domain.in.rjsa.util.StaticData;
@Controller
@RequestMapping("/apivendorMaster")
public class VendorMasterController extends AbstractController<Long, VendorMaster, VendorMasterService>{
	@Autowired
	VendorMasterService service;
	@Override
	public VendorMasterService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<VendorMaster> getEntity() {
		// TODO Auto-generated method stub
		return VendorMaster.class;
	}
	
	 @Override	
		public Object getDetail(Long id, Long clientId) {
			// TODO Auto-generated method stub
		    VendorMasterWrapper ew = new VendorMasterWrapper();
		    VendorMaster vendorMaster = service.getByKey(id);
		    ew.setVendorMaster(vendorMaster);
	    	HashMap<String, Object> map = new HashMap<>();
			map.put("clientId", clientId);
			ew.setListVendorMaster(service.search(map, clientId));
			return ew;
		}
	 
	 
	 
	 @RequestMapping(value = "/approve/{clientId}", method = RequestMethod.PUT)
		public ResponseEntity<?> approveUpdate(@RequestBody LinkedHashMap<String, Object> entity,
				HttpServletResponse response, UriComponentsBuilder ucBuilder) {

			Gson gson = new Gson();
			Login l = applicationCache.getLoginDetail(getPrincipal());
			if (entity.get("vendorId") == null || entity.get("vendorId").equals("")) {
				entity.put("status", StaticData.documentStatus.get(1));
				JsonElement jsonElement = gson.toJsonTree(entity);
				service.update(gson.fromJson(jsonElement, VendorMaster.class));
				addLogsU(entity);

				entity.remove("id");
				entity.remove("vendorId");
				jsonElement = gson.toJsonTree(entity);
				service.save(gson.fromJson(jsonElement, VendorMaster.class));
				return new ResponseEntity<String>(HttpStatus.ACCEPTED);
			} else {
				entity.put("status", StaticData.documentStatus.get(1));
				JsonElement jsonElement = gson.toJsonTree(entity);
				service.update(gson.fromJson(jsonElement, VendorMaster.class));
				entity.remove("status");

				entity.put("id", entity.remove("vendorId"));
				jsonElement = gson.toJsonTree(entity);
				service.update(gson.fromJson(jsonElement, VendorMaster.class));

				VendorMaster status = new VendorMaster();
				status.setClientId(l.getClientId());
				status.setStatus(entity.get("status").toString());
				status.setVendorId(Long.valueOf(entity.get("id").toString()));
				

				return new ResponseEntity<String>(HttpStatus.ACCEPTED);
			}
		}
	 
	 @RequestMapping(value = "/reject/{clientId}", method = RequestMethod.PUT)
		public ResponseEntity<?> rejectUpdate(@RequestBody LinkedHashMap<String, Object> entity,
				HttpServletResponse response, UriComponentsBuilder ucBuilder) {
			FieldErrorDTO ermsg = new FieldErrorDTO();
			Login l = applicationCache.getLoginDetail(getPrincipal());
			Long clientId = Long.valueOf(entity.get("clientId").toString());
			if (clientId == l.getClientId()) {

				VendorMaster doc = service.getByKey(Long.valueOf(entity.get("id").toString()));
				doc.setStatus(StaticData.documentStatus.get(2));
				service.update(doc);

				VendorMaster status = new VendorMaster();
				status.setClientId(l.getClientId());
				status.setStatus(entity.get("status").toString());
				status.setVendorId(Long.valueOf(entity.get("id").toString()));
				service.save(status);

				addLogsU(entity);

				ermsg.setMessage("Updated Successfully");
				return new ResponseEntity<String>(HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	 
	 

}
