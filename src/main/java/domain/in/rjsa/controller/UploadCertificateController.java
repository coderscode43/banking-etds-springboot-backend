package domain.in.rjsa.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.UploadCertificate;
import domain.in.rjsa.service.UploadCertificateService;

@Controller
@RequestMapping("/apiuploadCertificate")
public class UploadCertificateController extends AbstractControllerForm<Long, UploadCertificate, UploadCertificateService>{

	@Autowired
	UploadCertificateService service;
	
	private ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
	
	private static final long MAX_FILE_SIZE_BYTES = 10485760; // 10MB in bytes
	
	static final org.slf4j.Logger logger = LoggerFactory.getLogger(UploadCertificateController.class);

	@RequestMapping(value = "/addFile", method = RequestMethod.POST)
	public ResponseEntity<?> addDocument(
			@RequestParam("zipFile") MultipartFile downloadFile,
			@RequestParam("TAN") String TAN,
			@RequestParam("fy") String fy, 
			@RequestParam("quarter") String quarter, 
			@RequestParam("form") String form
			
		//	@RequestParam("uploadedTime") String uploadedTime
			) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
			if (downloadFile.getOriginalFilename().endsWith(".zip")) {
				HashMap<String, String> lessonMap = new HashMap<String, String>();
				lessonMap.put("TAN", TAN);
				lessonMap.put("fy", fy);
				lessonMap.put("quarter", quarter);
				lessonMap.put("form", form);
				// lessonMap.put("uploadedTime", uploadedTime);
				service.saveDocument(downloadFile, lessonMap);
				HashMap<String, Object> entity = new HashMap<>();
				addLogs("Add");
				return new ResponseEntity<>(HttpStatus.OK);

			} else {
				
				throw new CustomException("Invalid File Type");
			}

		} catch (Exception e) {
			e.printStackTrace();
			ermsg.setEntityName("Upload Certificate");
			ermsg.setExceptionMsg(e.getMessage());
			
			return new ResponseEntity<>(ermsg,HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/uploadCertificate", method = RequestMethod.POST)
	public ResponseEntity<?> uploadCertificate(@RequestParam("downloadFile") MultipartFile downloadFile,
			@RequestParam("tan") String tan, @RequestParam("typeofCertificate") String typeofCertificate,
			@RequestParam("fy") String fy, @RequestParam("quarter") String quarter) {
		try {
			if (downloadFile.getOriginalFilename().endsWith(".pdf")
					|| downloadFile.getOriginalFilename().endsWith(".zip")) {
				if (downloadFile.getOriginalFilename().endsWith(".pdf")
						&& downloadFile.getSize() > MAX_FILE_SIZE_BYTES) {
					FieldErrorDTO dto = new FieldErrorDTO();
					dto.setEntityName("Certificate");
					dto.setExceptionMsg("File size must not exceed 10MB");
					return new ResponseEntity<Object>(dto, HttpStatus.FORBIDDEN);
				} else {
					HashMap<String, String> lessonMap = new HashMap<String, String>();
					lessonMap.put("tan", tan);
					lessonMap.put("typeofCertificate", typeofCertificate);
					lessonMap.put("fy", fy);
					lessonMap.put("quarter", quarter);
					service.uploadCertificate(downloadFile, lessonMap);
				}
			} else {
				FieldErrorDTO dto = new FieldErrorDTO();
				dto.setEntityName("Certificate");
				dto.setExceptionMsg("Invalid File Type");
				return new ResponseEntity<Object>(dto, HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<>("Error in application. Please contact system administration",
					HttpStatus.BAD_REQUEST);
		}
	}
	
	// ========================== Upload Zip with Thread
	@RequestMapping(value = "/uploadZip", method = RequestMethod.POST)
	public ResponseEntity<?> uploadZip(@RequestParam("downloadFile") MultipartFile downloadFile,
			@RequestParam("tan") String tan, @RequestParam("typeofCertificate") String typeofCertificate,
			@RequestParam("fy") String fy, @RequestParam("quarter") String quarter) {
		logger.info("Upload Invoices request received for User - " + getPrincipal());
		FieldErrorDTO responseDTO = new FieldErrorDTO();
		responseDTO.setEntityName("Upload Invoices");

		try {
			if (downloadFile == null || downloadFile.isEmpty()) {
				responseDTO.setExceptionMsg("No file uploaded or file is empty.");
				return ResponseEntity.badRequest().body(responseDTO);
			}

			String fileName = downloadFile.getOriginalFilename();
			if (fileName == null
					|| !(fileName.toLowerCase().endsWith(".zip")) && !(fileName.toLowerCase().endsWith(".pdf"))) {
				responseDTO.setExceptionMsg("Invalid File Format. Only .zip and .pdf is allowed.");
				return ResponseEntity.badRequest().body(responseDTO);
			}
			
			InputStream inputStream = downloadFile.getInputStream();
			SecurityContext context = SecurityContextHolder.getContext();

			UploadCertificate uploadCertificate = new UploadCertificate();
			uploadCertificate.setFileName(downloadFile.getOriginalFilename());
			uploadCertificate.setTAN(tan);
			uploadCertificate.setFy(fy);
			uploadCertificate.setQuarter(quarter);
			uploadCertificate.setForm(typeofCertificate);
			uploadCertificate.setUploadedTime(new Date());
			uploadCertificate.setUserName(getPrincipal());
			uploadCertificate.setStatus("pending");
			service.save(uploadCertificate);

			// Run async
			CompletableFuture.runAsync(() -> {
				try {
					logger.info("ZIP file processing started in background thread");
					SecurityContextHolder.setContext(context);
					HashMap<String, String> lessonMap = new HashMap<String, String>();
					lessonMap.put("tan", tan);
					lessonMap.put("typeofCertificate", typeofCertificate);
					lessonMap.put("fy", fy);
					lessonMap.put("quarter", quarter);
					service.uploadInvoices(fileName, lessonMap, inputStream);
					uploadCertificate.setStatus("success");
					service.update(uploadCertificate);
				} catch (Exception e) {
					logger.error("Error while processing ZIP file | Filename - " + fileName, e);
					uploadCertificate.setStatus("failed");
					service.update(uploadCertificate);
				} finally {
					try {
						SecurityContextHolder.clearContext();
						inputStream.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, executorService);

			// Return success immediately
			Map<String, String> successMsg = new HashMap<>();
			successMsg.put("message", "Uploading in progress. Please check after sometime.");
			return ResponseEntity.ok(successMsg);

		} catch (Exception e) {
			logger.error("Unexpected error during file upload", e);
			responseDTO.setExceptionMsg("Unexpected error occurred");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
	}

	@Override
	public UploadCertificateService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<UploadCertificate> getEntity() {
		// TODO Auto-generated method stub
		return UploadCertificate.class;
	}
}
