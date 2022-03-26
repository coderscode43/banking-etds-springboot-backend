package domain.in.rjsa.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/apiUpload")
public class UploadCertificateController {

	
	static final org.slf4j.Logger logger = LoggerFactory.getLogger(UploadCertificateController.class);

	@RequestMapping(value = "/{fy}/{q}/{type}", method = RequestMethod.POST)
	public void getStatus(HttpServletRequest request, HttpServletResponse response,@RequestParam("file") MultipartFile file,
			@PathVariable("fy") String fy, @PathVariable("q") String q, @PathVariable("type") String type) throws IOException {
		
		String zipPath = System.getProperty("user.home") + "/download/" + fy + "/" + q + "/" + type+ "/" + file;
		File doc = new File(zipPath);
		OutputStream out = new FileOutputStream(doc);
		out.close();

	}
}
