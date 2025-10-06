package domain.in.rjsa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.email.QueryMail;
import domain.in.rjsa.email.ReminderMail;
import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.RegularReturnRemark;
import domain.in.rjsa.service.RegularReturnRemarkService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/apiregularReturnRemark")
public class RegularReturnRemarkController
		extends AbstractControllerForm<Long, RegularReturnRemark, RegularReturnRemarkService> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	RegularReturnRemarkService service;

	@Autowired
	ReminderMail reminderMail;

	@Autowired
	QueryMail queryMail;

	@Override
	public RegularReturnRemarkService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<RegularReturnRemark> getEntity() {
		// TODO Auto-generated method stub
		return RegularReturnRemark.class;
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		ermsg.setEntityName(getEntity().getSimpleName());
		try {
			logger.info("Creating new Return instance");
			adminValidation(entity);
			service.save(entity, getPrincipal());
			addLogs("Add");
//			addRemarkLogs(entity);
			ermsg.setSuccessMsg("Saved Successfully");
			return new ResponseEntity<Object>(ermsg,HttpStatus.CREATED);
		} catch (Exception e) {
			ermsg.setExceptionMsg(e.getMessage());
			return new ResponseEntity<Object>(ermsg, HttpStatus.BAD_REQUEST);
		}

	}

	// ------------------- Upload Regular Return from RO
	// ---------------------------------
	@PostMapping(value = "/addRegularReturnRO")
	public ResponseEntity<?> addRegularReturnRO(
			@RequestParam(value = "blob", required = false) List<MultipartFile> listdocs,
			@RequestParam("tdsfileblob") MultipartFile tdsfileblob,
			/* @RequestParam("reportfileblob") MultipartFile reportfile, */ @RequestParam("dec") String entity) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
			service.saveRegularReturnRO(listdocs, tdsfileblob, /* reportfile, */ entity, getPrincipal());
			return new ResponseEntity<Object>(HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			ermsg.setExceptionMsg("Error in application, Please contact your administrator");
			ermsg.setEntityName("RegularReturn");
			return new ResponseEntity<Object>(ermsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// ------------------- Add With File ---------------------------------

	@PostMapping(value = "/addWithFile")
	public ResponseEntity<?> addDocument(@RequestParam("blob") MultipartFile blob, @RequestParam("dec") String entity) {

		FieldErrorDTO ermsg = new FieldErrorDTO();
		logger.info("Creating new Return instance");
		ermsg.setEntityName(getEntity().getSimpleName());
		try {
			ObjectMapper mapper = new ObjectMapper();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map = mapper.readValue(entity, new TypeReference<LinkedHashMap<String, Object>>() {
			});
			adminValidation(map);
			getService().saveWithFile(blob, map, getPrincipal());
			addLogs("Add");
			ermsg.setSuccessMsg("Saved Successfully");
			return new ResponseEntity<Object>(ermsg,HttpStatus.CREATED);
		} catch (Exception e) {		
			ermsg.setExceptionMsg(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/downloadDoc/{id}")
	public ResponseEntity<?> downloadDocument(HttpServletResponse response, @PathVariable Long id) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
			service.downloadDocument(id, response);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			ermsg.setEntityName("Upload Document");
			ermsg.setExceptionMsg(e.getMessage());
			return new ResponseEntity<>(ermsg, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/addBulkRemark")
	public ResponseEntity<?> createBulkRemark(@RequestBody LinkedHashMap<String, Object> entity) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
			logger.info("Creating new Return instance");
			adminValidation(entity);
			service.saveBulkRemark(entity, getPrincipal());

			ermsg.setEntityName(getEntity().getSimpleName().toString());
			ermsg.setSuccessMsg("Added Successfully");
			return new ResponseEntity<Object>(ermsg, HttpStatus.CREATED);
		} catch (Exception e) {
			ermsg.setEntityName(getEntity().getSimpleName().toString());
			ermsg.setExceptionMsg(e.getMessage());
			return new ResponseEntity<Object>(ermsg, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/sendReminder")
	public ResponseEntity<?> sendReminder(@RequestBody LinkedHashMap<String, Object> entity) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {

			logger.info("Creating new Return instance");
			adminValidation(entity);
			ArrayList returnList = (ArrayList) entity.get("regularReturn");
			for (Object ele : returnList) {
				HashMap<String, String> map = new HashMap<String, String>();
				String arr[] = ele.toString().split(Pattern.quote(","), -1);
				for (String e : arr) {
					String a[] = e.split(Pattern.quote("="));
					map.put(a[0].trim().replace("{", ""), a[1].trim().replace("}", ""));
				}
				String branchCode = map.get("branchCode").toString();
				Branch branchModel = service.getBranch(Long.parseLong(branchCode));
				String emailTo = branchModel.getBranchEmail();
				map.put("emailTo", emailTo);

				String status = map.get("status").toString();
				if (status.equalsIgnoreCase("Request for data from RO")) {
					map.put("subject", "Gentle Reminder - Request for " + map.get("quarter") + ", " + map.get("form")
							+ " Return Data Files for F.Y. " + map.get("fy") + ", " + map.get("form"));
					reminderMail.sendEmail(map);
				} else if (status.equalsIgnoreCase("Waiting for Query Reply")) {
					map.put("subject",
							"Gentle Reminder - Immediate Action Required: TDS/TCS Regular Return Query for FY: "
									+ map.get("fy") + ", " + map.get("quarter") + ", " + map.get("form"));
					queryMail.sendEmail(map);
				}
			}
			ermsg.setEntityName(getEntity().getSimpleName().toString());
			ermsg.setSuccessMsg("Sent Reminder Successfully");
			return new ResponseEntity<Object>(ermsg,HttpStatus.CREATED);
		} catch (Exception e) {
			ermsg.setEntityName(getEntity().getSimpleName().toString());
			ermsg.setExceptionMsg(e.getMessage());
			return new ResponseEntity<Object>(ermsg, HttpStatus.BAD_REQUEST);
		}

	}

}
