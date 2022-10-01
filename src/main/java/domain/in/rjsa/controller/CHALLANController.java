package domain.in.rjsa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.in.rjsa.model.tds.CHALLAN;
import domain.in.rjsa.service.CHALLANService;
import domain.in.rjsa.util.StaticData;

@Controller
@RequestMapping("/apichallan")
public class CHALLANController extends AbstractControllerTaxo<String, CHALLAN, CHALLANService> {

	@Autowired
	CHALLANService service;

	@Override
	public CHALLANService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<CHALLAN> getEntity() {
		// TODO Auto-generated method stub
		return CHALLAN.class;
	}

	public List<?> getSearch(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return getService().search(map, pageNo, resultPerPage);
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());

	// get file from system folder
	@RequestMapping(value = "/files/{tan}/{certificate}/{fy}/{q}/{pan}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> download(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String tan, @PathVariable String certificate, @PathVariable String fy, @PathVariable String q,
			@PathVariable String pan) {
		try {
			logger.info("Get Certificate for  " + pan);
			response.setContentType("application/zip, application/octet-stream");			
			String Ay = fyToAy(fy);
 			String pdfFileName = pan + "_" + q + "_" + Ay + ".pdf";
			System.out.println(pdfFileName);
			service.setStaticData();
			String path = StaticData.CertificatePath;
			String[] c = certificate.split("-");
			String[] t = tan.split("-");
			String zipPath = path + "download/" + fy + "/" + q + "/" + c[0] + "/"
					+ t[0] + "/" + pdfFileName ;
			System.out.println(zipPath);
			logger.info(zipPath);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + pdfFileName + "\"");
			File file = new File(zipPath);
			if (!file.exists()) {
				return new ResponseEntity<>("File Not Found" + zipPath, HttpStatus.BAD_REQUEST);
			}

			FileInputStream fileInputStream;
			PrintWriter out = response.getWriter();
			fileInputStream = new FileInputStream(zipPath);
			int i;
			while ((i = fileInputStream.read()) != -1) {
				out.write(i);
			}
			fileInputStream.close();
			out.close();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in downloading ", e);
			return new ResponseEntity<>("File Not Found" + System.getProperty("user.home") + "/download/" + fy + "/" + q
					+ "/" + certificate + "/" + tan + "/" + pan + "_" + q + "_" + fy + ".pdf", HttpStatus.BAD_REQUEST);

		}
	}
	
	public String fyToAy(String fy) {
		String Ay= null;
		String[] yr = fy.split("-");
		int y1 = Integer.parseInt(yr[0]);
		int y2 = Integer.parseInt(yr[1]);
		
		int Ay1 = y1+1;
		int Ay2 = y2+1;
		return Ay1+"-"+Ay2;
		
	}

	// get file from project folder
//	@RequestMapping(value = "/files/{deductee}/{certificate}/{fy}/{q}/{pan}", method = RequestMethod.GET)
//	public ResponseEntity<InputStreamResource> getFile(HttpServletRequest request, HttpServletResponse response,
//			@PathVariable String deductee, @PathVariable String certificate, @PathVariable String fy,
//			@PathVariable String q, @PathVariable String pan) {
//		try {
//			String pdfFileName = pan;
//			File file = new File(getClass().getClassLoader()
//					.getResource("file/" + fy + "/" + q + "/" + deductee + "/" + pdfFileName + ".pdf").getPath());
//			HttpHeaders respHeaders = new HttpHeaders();
//			MediaType mediaType = MediaType.parseMediaType("application/pdf");
//			respHeaders.setContentType(mediaType);
//			respHeaders.setContentLength(file.length());
//			respHeaders.setContentDispositionFormData("attachment", file.getName());
//			InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
//			return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
//		}
//	}

}
