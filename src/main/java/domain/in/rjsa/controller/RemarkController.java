package domain.in.rjsa.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;

import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.fy.Remarks;
import domain.in.rjsa.service.RemarkService;

@Controller
@RequestMapping("/apiremark")
public class RemarkController extends AbstractControllerFY<Long, Remarks, RemarkService> {

	@Autowired
	RemarkService service;
	

	@Override
	public RemarkService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Remarks> getEntity() {
		// TODO Auto-generated method stub
		return Remarks.class;
	}

	@RequestMapping(value = "/add/{fy}/{branchCode}/{type}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity,
			@PathVariable String type) {
		try {
			// FieldErrorDTO ermsg=new FieldErrorDTO();
			logger.info("Creating new Return instance");
			String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			entity.put("dateTime", timeStamp);
			// Login l = applicationCache.getLoginDetail(getPrincipal());
			entity.put("userName", getPrincipal());
			entity.put("status", type);
			adminValidation(entity);
			String jsonElement = mapper.writeValueAsString(entity);

			service.setResolve(mapper.readValue(jsonElement, getEntity()), type);
			addRemarkLogs(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/addRemarkWithDocument", method = RequestMethod.POST)
	public ResponseEntity<?> addDocument(@RequestParam("downloadFile") MultipartFile downloadFile,
			@RequestParam("branchCode") Long branchCode, @RequestParam("deducteeId") Long deducteeId,
			@RequestParam("remark") String remark,@RequestParam("deducteeForm") String deducteeForm,
			@RequestParam("fy") String fy,@RequestParam("form") String form,@RequestParam("quarter") String quarter) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
//			if (downloadFile.getOriginalFilename().endsWith(".zip")) {
			// lessonMap.put("uploadedTime", uploadedTime);
			service.SaveRemarkWithDocument(downloadFile, branchCode, deducteeForm, remark, getPrincipal(), deducteeId, fy,quarter,form);
			addLogs("Add");
			return new ResponseEntity<>(HttpStatus.OK);
//			} else {
//				throw new CustomException("Invalid File Type");
//			}
		} catch (Exception e) {
			e.printStackTrace();
			ermsg.setEntityName("Upload Document");
			ermsg.setExceptionMsg(e.getMessage());
			return new ResponseEntity<>(ermsg, HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/downloadDoc/{id}", method = RequestMethod.GET)
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

	private final Logger logger = LoggerFactory.getLogger(getClass());

}
