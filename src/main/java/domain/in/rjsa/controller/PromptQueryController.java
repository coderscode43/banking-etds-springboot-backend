package domain.in.rjsa.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.wrapper.FileTypeWrapper;
import domain.in.rjsa.service.ProcessingStatusService;
import domain.in.rjsa.service.PromptQueryService;

@Controller
@RequestMapping("/apipromptQuery")
public class PromptQueryController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

	@Autowired
	private PromptQueryService service;

	@Autowired
	private ProcessingStatusService processingStatusService;
	
	
	@PostMapping(value = "/TextToSQL")
	public ResponseEntity<Map<String, String>> processPromptQuery(@RequestBody String data) {
	    logger.info("Request received, processing will start asynchronously");

	    HashMap<String, Object> map = parse(data);
	    FileTypeWrapper fileTypeWrapper = new FileTypeWrapper("EXCEL");

	    if (map.get("query").toString().toLowerCase().contains("certificate")) {
	        fileTypeWrapper.setFileType("PDF");
	    }

	    String requestId = UUID.randomUUID().toString();
	    processingStatusService.setStatus(requestId, "Processing");

	    CompletableFuture.runAsync(() -> {
	        try {
	            logger.info("Processing in background with requestId: " + requestId);
	            String encodedFile = service.processRequest(map, fileTypeWrapper);

	            if (encodedFile.contains("ErrorMsg")) {
	                processingStatusService.setStatus(requestId, "Error: " + encodedFile);
	            } else {
	                HashMap<String, String> response = new HashMap<>();
	                handleFileResponse(encodedFile, response, fileTypeWrapper); // Use handleFileResponse
	                
	                processingStatusService.setStatus(requestId, "Completed");
	                processingStatusService.setFileData(requestId, response); // Store response data
	            }
	        } catch (Exception e) {
	            logger.error("Error processing request", e);
	            processingStatusService.setStatus(requestId, "Error: Processing failed");
	        }
	    }, executorService);


	    Map<String, String> response = new HashMap<>();
	    response.put("requestId", requestId);
	    response.put("status", "Processing started");

	    return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/status/{requestId}")
	public ResponseEntity<?> getProcessingStatus(@PathVariable String requestId) {
	    String status = processingStatusService.getStatus(requestId);

	    if ("Completed".equals(status)) {
	        HashMap<String, String> response = processingStatusService.getFileData(requestId);
	        processingStatusService.removeStatus(requestId);
	        return ResponseEntity.ok(response);
	    } else if (status.startsWith("Error:")) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
	    }

	    return ResponseEntity.ok(Collections.singletonMap("status", status));
	}


	private void handleFileResponse(String encodedFile, HashMap<String, String> response, FileTypeWrapper fileTypeWrapper) {
	    String fileType = fileTypeWrapper.getFileType();

	    try {
	        if (fileType.equalsIgnoreCase("EXCEL")) {
	            response.put("fileType", "EXCEL");
	        } else if (fileType.equalsIgnoreCase("PDF")) {
	            response.put("fileType", "PDF");
	        } else if (fileType.equalsIgnoreCase("ZIP")) {
	            response.put("fileType", "ZIP");
	        }
	        response.put("status", "Completed");
	        response.put("encodedFile", encodedFile);
	    } catch (Exception e) {
	        logger.error("Could not prepare file response", e);
	        response.put("error", "Could not read file");
	    }
	}


	private HashMap<String, Object> parse(String data) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			map = mapper.readValue(data, getTypeRef());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	private TypeReference<HashMap<String, Object>> getTypeRef() {
		return new TypeReference<HashMap<String, Object>>() {
		};
	}

	private FieldErrorDTO setExeptionMsg(String exceptionMsg) {
		FieldErrorDTO errmsg = new FieldErrorDTO();
		errmsg.setEntityName("PromptQuery");
		errmsg.setExceptionMsg(exceptionMsg);

		return errmsg;
	}

}
