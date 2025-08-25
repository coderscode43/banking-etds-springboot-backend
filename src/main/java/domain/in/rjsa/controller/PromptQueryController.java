package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.service.PromptQueryService;

@Controller
@RequestMapping("/apipromptQuery")
public class PromptQueryController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

	@Autowired
	private PromptQueryService service;

//	private LongPollingController() {
//		startMonitoring();
//	}
//
//	private void startMonitoring() {
//		Runnable monitor = () -> {
//			while (true) {
//				logger.info("Pool Size: " + executorService.getPoolSize());
//				logger.info("Active Threads: " + executorService.getActiveCount());
//				logger.info("Task Count: " + executorService.getTaskCount());
//				logger.info("Completed Tasks: " + executorService.getCompletedTaskCount());
//				System.out.println("------------------------------------------------------");
//				try {
//					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					// TODO: handle exception
//					Thread.currentThread().interrupt();
//					break;
//				}
//			}
//		};
//
//		new Thread(monitor).start();
//	}

	/*
	 * HTTP worker threads released immediately by passing on the work to the non
	 * HTTP thread. The worker threads are able to handle additional HTTP request
	 * without waiting for the long-running process to finish the work.
	 *
	 * The servlet thread will remain in a waiting state until you call
	 * deferredResult.setResult(response) or deferredResult.setErrorResult(...).
	 * 
	 * also we can use thenApply or thenCompose accordingly as per need. Note that
	 * thenAccept() does not return a value; it is designed solely for consuming the
	 * result.
	 * 
	 * You can initiate asynchronous processing with
	 * CompletableFuture.supplyAsync(), or by using executorService.execute() /
	 * submit.
	 * 
	 * If an exception occurs in supplyAsync, we can directly call
	 * setErrorResult(...), but this approach is not recommended. Instead, it’s
	 * better to use an exceptionally block to handle errors.
	 * 
	 */

	@RequestMapping(value = "/TextToSQL", method = RequestMethod.POST)
	public DeferredResult<ResponseEntity<?>> processPromptQuery(@RequestBody String data) {
		logger.info("Request processing started");

		HashMap<String, Object> map = parse(data);
		String fileType = map.get("fileType").toString();

		// free the HTTP thread and run the processing on a new worker thread
		DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>(900000L); // 15m

		// timeout callback
		deferredResult.onTimeout(() -> {
			deferredResult.setErrorResult(
					ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(setExeptionMsg("Request timmeout")));
		});

		// pass a service method to an ExecutorService to manage concurrency.
		CompletableFuture.supplyAsync(() -> {
			logger.info("Processing on separate thread");

			String encodedFile = "";
			try {
				encodedFile = service.processRequest(map);

			} catch (CustomException e) {
				logger.error("A custom exception occurred", e);
				throw new CompletionException(e);
			} catch (Exception e) {
				logger.error("An error occurred", e);
				throw new CompletionException(e);
			}
			return encodedFile;

		}, executorService).thenAccept(encodedFile -> {
			if (encodedFile != null && encodedFile.length() > 0) {
				handleFileResponse(encodedFile, deferredResult, fileType);

			} else {
				deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(setExeptionMsg("File Path is empty.")));
			}

		}).exceptionally(ex -> {
			logger.info("An Exception occurred in supplyAsync", ex);
			if (ex.getCause() instanceof CustomException) {
				deferredResult.setErrorResult(
						ResponseEntity.status(HttpStatus.BAD_REQUEST).body(setExeptionMsg(ex.getCause().getMessage())));
			} else {
				deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(setExeptionMsg("An error occurred")));
			}
			return null;
		});
		logger.info("Servlet thread freed");

		return deferredResult;
	}

	private void handleFileResponse(String encodedFile, DeferredResult<ResponseEntity<?>> deferredResult,
			String fileType) {
		try {
			HttpHeaders headers = new HttpHeaders();
			if (fileType.equalsIgnoreCase("EXCEL")) {
				headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");

			} else if (fileType.equalsIgnoreCase("CSV")) {
				headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");
			}

			deferredResult.setResult(ResponseEntity.status(HttpStatus.OK)
					.body(Map.of("fileType", fileType, "encodedFile", encodedFile)));
		} catch (Exception e) {
			logger.error("Could not read file", e);
			e.printStackTrace();

			deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(setExeptionMsg("Could not read file")));
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
