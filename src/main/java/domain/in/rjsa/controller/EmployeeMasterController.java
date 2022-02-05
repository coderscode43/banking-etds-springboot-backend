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
import domain.in.rjsa.model.form.EmployeeMaster;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.service.EmployeeMasterService;
import domain.in.rjsa.util.StaticData;
@Controller
@RequestMapping("/apiemployeeMaster")
public class EmployeeMasterController extends AbstractBranchControllerForm<Long, EmployeeMaster, EmployeeMasterService>{
	@Autowired
	EmployeeMasterService service;
	@Override
	public EmployeeMasterService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<EmployeeMaster> getEntity() {
		// TODO Auto-generated method stub
		return EmployeeMaster.class;
	}
	
	@RequestMapping(value = "/approve/{clientId}", method = RequestMethod.PUT)
	public ResponseEntity<?> approveUpdate(@RequestBody LinkedHashMap<String, Object> entity,
			HttpServletResponse response, UriComponentsBuilder ucBuilder) {

		Gson gson = new Gson();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		if (entity.get("employeeId") == null || entity.get("employeeId").equals("")) {
			entity.put("status", StaticData.documentStatus.get(1));
			JsonElement jsonElement = gson.toJsonTree(entity);
			service.update(gson.fromJson(jsonElement, EmployeeMaster.class));
			addLogsU(entity);

			entity.remove("id");
			entity.remove("employeeId");
			jsonElement = gson.toJsonTree(entity);
			service.save(gson.fromJson(jsonElement, EmployeeMaster.class));
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} else {
			entity.put("status", StaticData.documentStatus.get(1));
			JsonElement jsonElement = gson.toJsonTree(entity);
			service.update(gson.fromJson(jsonElement, EmployeeMaster.class));
			entity.remove("status");

			entity.put("id", entity.remove("employeeId"));
			jsonElement = gson.toJsonTree(entity);
			service.update(gson.fromJson(jsonElement, EmployeeMaster.class));

			EmployeeMaster status = new EmployeeMaster();
			status.setClientId(l.getClientId());
			status.setStatus(entity.get("status").toString());
			status.setEmployeeId(Long.valueOf(entity.get("id").toString()));
			

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

			EmployeeMaster doc = service.getByKey(Long.valueOf(entity.get("id").toString()));
			doc.setStatus(StaticData.documentStatus.get(2));
			service.update(doc);

			EmployeeMaster status = new EmployeeMaster();
			status.setClientId(l.getClientId());
			status.setStatus(entity.get("status").toString());
			status.setEmployeeId(Long.valueOf(entity.get("id").toString()));
			service.save(status);

			addLogsU(entity);

			ermsg.setMessage("Updated Successfully");
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
