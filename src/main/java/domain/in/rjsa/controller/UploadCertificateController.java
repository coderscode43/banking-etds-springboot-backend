package domain.in.rjsa.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.fy.UploadCertificate;
import domain.in.rjsa.service.UploadCertificateService;

@Controller
@RequestMapping("/apiuploadCertificate")
public class UploadCertificateController extends AbstractControllerForm<Long, UploadCertificate, UploadCertificateService>{

	@Autowired
	UploadCertificateService service;
	
	static final org.slf4j.Logger logger = LoggerFactory.getLogger(UploadCertificateController.class);

//	@RequestMapping(value = "/{fy}/{q}/{type}", method = RequestMethod.POST)
//	public void getStatus(HttpServletRequest request, HttpServletResponse response,@RequestParam("file") MultipartFile file,
//			@PathVariable("fy") String fy, @PathVariable("q") String q, @PathVariable("type") String type) throws IOException {
//		
//		String zipPath = System.getProperty("user.home") + "/download/" + fy + "/" + q + "/" + type+ "/" + file;
//		File doc = new File(zipPath);
//		OutputStream out = new FileOutputStream(doc);
//		out.close();
//
//	}
	
	
	
	
	
	

//	public void save(LinkedHashMap<String, Object> map, MultipartFile file) {
//		UploadCertificate doc = new UploadCertificate();
//		Login l = applicationCache.getLoginDetail(getPrincipal());
//		doc.setTAN(file.getOriginalFilename());
//		try {
//			doc.setZipFile(file.getBytes());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		getService().save(doc);
//	}

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
