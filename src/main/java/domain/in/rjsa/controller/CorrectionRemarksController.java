package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;

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

import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.CorrectionRemarks;
import domain.in.rjsa.service.CorrectionRemarksService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/apicorrectionRemark")
public class CorrectionRemarksController
		extends AbstractControllerForm<Long, CorrectionRemarks, CorrectionRemarksService> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CorrectionRemarksService service;

	@Override
	public CorrectionRemarksService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<CorrectionRemarks> getEntity() {
		// TODO Auto-generated method stub
		return CorrectionRemarks.class;
	}

	@PostMapping(value = "/addRemark")
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {

			logger.info("Creating new Return instance");
			adminValidation(entity);
			String json = mapper.writeValueAsString(entity);
			CorrectionRemarks correctionRemarks = mapper.readValue(json, CorrectionRemarks.class);
			service.saveRemark(correctionRemarks, getPrincipal());
			addLogs("Add");
//		addRemarkLogs(entity);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			ermsg.setEntityName(getEntity().getSimpleName().toString());
			ermsg.setExceptionMsg(e.getMessage());
			return new ResponseEntity<Object>(ermsg, HttpStatus.BAD_REQUEST);

		}

	}

	@PostMapping(value = "/addRemarkWithDocument")
	public ResponseEntity<?> addDocument(@RequestParam("downloadFile") MultipartFile downloadFile,
			@RequestParam("branchCode") Long branchCode, @RequestParam("crId") Long crId,
			@RequestParam("remark") String remark, @RequestParam("status") String status,
			@RequestParam("fy") String fy,@RequestParam("quarter") String quarter) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
//			if (downloadFile.getOriginalFilename().endsWith(".zip")) {
			// lessonMap.put("uploadedTime", uploadedTime);
			HashMap<String, Object> entity = new HashMap<>();
			service.SaveRemarkWithDocument(downloadFile, branchCode, crId, remark, getPrincipal(), status,fy,quarter);
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

}
