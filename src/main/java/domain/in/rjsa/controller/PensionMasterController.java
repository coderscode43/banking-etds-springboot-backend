package domain.in.rjsa.controller;

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
import domain.in.rjsa.model.form.PensionMaster;
import domain.in.rjsa.service.PensionMasterService;
import domain.in.rjsa.util.StaticData;
@Controller
@RequestMapping("/apipensionMaster")
public class PensionMasterController extends AbstractBranchControllerForm<Long, PensionMaster, PensionMasterService>{
	@Autowired
	PensionMasterService service;
	@Override
	public PensionMasterService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<PensionMaster> getEntity() {
		// TODO Auto-generated method stub
		return PensionMaster.class;
	}
	
	 
	 @RequestMapping(value = "/approve/{clientId}", method = RequestMethod.PUT)
		public ResponseEntity<?> approveUpdate(@RequestBody LinkedHashMap<String, Object> entity,
				HttpServletResponse response, UriComponentsBuilder ucBuilder) {

			Gson gson = new Gson();
			Login l = applicationCache.getLoginDetail(getPrincipal());
			if (entity.get("pensionerId") == null || entity.get("pensionerId").equals("")) {
				entity.put("status", StaticData.documentStatus.get(1));
				JsonElement jsonElement = gson.toJsonTree(entity);
				service.update(gson.fromJson(jsonElement, PensionMaster.class));
				addLogsU(entity);

				entity.remove("id");
				entity.remove("pensionerId");
				jsonElement = gson.toJsonTree(entity);
				service.save(gson.fromJson(jsonElement, PensionMaster.class));
				return new ResponseEntity<String>(HttpStatus.ACCEPTED);
			} else {
				entity.put("status", StaticData.documentStatus.get(1));
				JsonElement jsonElement = gson.toJsonTree(entity);
				service.update(gson.fromJson(jsonElement, PensionMaster.class));
				entity.remove("status");

				entity.put("id", entity.remove("pensionerId"));
				jsonElement = gson.toJsonTree(entity);
				service.update(gson.fromJson(jsonElement, PensionMaster.class));

				PensionMaster status = new PensionMaster();
				status.setClientId(l.getClientId());
				status.setStatus(entity.get("status").toString());
				status.setPensionerId(Long.valueOf(entity.get("pensionerId").toString()));
				status.setPensionerCode(entity.get("pensionerCode").toString());
				status.setPensionerId(Long.valueOf(entity.get("pensionerId").toString()));
				status.setPensionerName(entity.get("pensionerName").toString());
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

				PensionMaster doc = service.getByKey(Long.valueOf(entity.get("id").toString()));
				doc.setStatus(StaticData.documentStatus.get(2));
				service.update(doc);

				PensionMaster status = new PensionMaster();
				status.setClientId(l.getClientId());
				status.setStatus(entity.get("status").toString());
				status.setPensionerId(Long.valueOf(entity.get("pensionerId").toString()));
				service.save(status);

				addLogsU(entity);

				ermsg.setMessage("Updated Successfully");
				return new ResponseEntity<String>(HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	 
}
