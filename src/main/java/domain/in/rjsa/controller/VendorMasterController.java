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
public class VendorMasterController extends AbstractBranchController<Long, VendorMaster, VendorMasterService>{
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
			if (entity.get("branchId") == null || entity.get("branchId").equals("")) {
				entity.put("status", StaticData.documentStatus.get(1));
				JsonElement jsonElement = gson.toJsonTree(entity);
				service.update(gson.fromJson(jsonElement, VendorMaster.class));
				addLogsU(entity);
                entity.remove("id");
				entity.remove("branchId");
				entity.remove("vendorCode");
				jsonElement = gson.toJsonTree(entity);
				service.save(gson.fromJson(jsonElement, VendorMaster.class));
				return new ResponseEntity<String>(HttpStatus.ACCEPTED);
			} else {
				entity.put("status", StaticData.documentStatus.get(1));
				JsonElement jsonElement = gson.toJsonTree(entity);
				service.update(gson.fromJson(jsonElement, VendorMaster.class));
				entity.remove("status");

				entity.put("id", entity.remove("branchId"));
				jsonElement = gson.toJsonTree(entity);
				service.update(gson.fromJson(jsonElement, VendorMaster.class));

				VendorMaster status = new VendorMaster();
				status.setClientId(l.getClientId());
				status.setStatus(entity.get("status").toString());
				status.setBranchId(Long.valueOf(entity.get("branchId").toString()));
				status.setVendorCode(entity.get("vendorCode").toString());
				status.setVendorId(Long.valueOf(entity.get("vendorId").toString()));
				status.setVendorCode(entity.get("vendorCode").toString());
				status.setVendorName(entity.get("vendorName").toString());
				status.setAddress(entity.get("address").toString());
				status.setMaker(entity.get("maker").toString());
				status.setChecker(entity.get("checker").toString());
				

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
				    entity.remove("id");
					entity.remove("branchId");
					entity.remove("vendorCode");
				service.update(doc);

				VendorMaster status = new VendorMaster();
				status.setClientId(l.getClientId());
				status.setStatus(entity.get("status").toString());
				status.setBranchId(Long.valueOf(entity.get("branchId").toString()));
				status.setVendorCode(entity.get("vendorCode").toString());
				status.setVendorId(Long.valueOf(entity.get("vendorId").toString()));
				status.setVendorCode(entity.get("vendorCode").toString());
				status.setVendorName(entity.get("vendorName").toString());
				status.setAddress(entity.get("address").toString());
				status.setMaker(entity.get("maker").toString());
				status.setChecker(entity.get("checker").toString());
				service.save(status);

				addLogsU(entity);

				ermsg.setMessage("Updated Successfully");
				return new ResponseEntity<String>(HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}

	 
	 

}
