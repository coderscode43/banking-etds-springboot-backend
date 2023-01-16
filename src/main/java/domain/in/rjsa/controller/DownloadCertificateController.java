package domain.in.rjsa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.util.StaticData;

@Controller
@RequestMapping("/apidownloadCertificate")
public class DownloadCertificateController {
	private static final String EXTENSION = "zip";
	private final Logger logger = LoggerFactory.getLogger(getClass());

	// get file from system folderz
	@RequestMapping(
			value = "/files/{tan}/{certificate}/{fy}/{q}/{pan}", 
			method = RequestMethod.GET ,produces="application/octet-stream" )
	public void download(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@PathVariable String tan,
			@PathVariable String certificate, 
			@PathVariable String fy,
			@PathVariable String q,
			@PathVariable String pan) {
		
		
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
			String userName = getPrincipal();
			if(userName==null||"anonymousUser".equals(userName)) {
				String auth = request.getHeader("directDownloadAuth");
				if(auth!=null) { 
					if(auth.equals(StaticData.directDownloadAuth)) {
						logger.info("Direct download certificate through auth");
					}else {
						throw new Exception("Invalid AUTH");
					}
				}else {
					throw new Exception("Invalid AUTH");
				}
			}
			pan = pan.toUpperCase().trim();
			tan = tan.trim();
			// verify regx pan
			if (Pattern.matches("^[A-Z]{5}[0-9]{4}[A-Z]{1}$", pan) || pan.equalsIgnoreCase("ALL PAN")) {
				pan = pan;
			} else {
				throw new Exception("Invalid PAN.");
			}
			// verify regx tan or if All TAN
			
			List<String> tanList = Arrays.asList(StaticData.Tan);
			
			if (tanList.contains(tan) || tan.equalsIgnoreCase("ALL TAN")) {
				tan = tan;
			} else {
				throw new Exception("Invalid TAN.");
			}
			// verify regX fy
			List<String> yearList = Arrays.asList(StaticData.financialYear);
			if (yearList.contains(fy)) {
				fy = fy;
			} else {
				throw new Exception("Invalid Financial Year.");
			}
			// vwerify q
			List<String> qList = Arrays.asList(StaticData.Quarter);
			if (qList.contains(q) || q.equalsIgnoreCase("ALL QUARTER")) {
				q = q;
			} else {
				throw new Exception("Invalid Quarter.");
			}
			// verify certificate
			List<String> cList = Arrays.asList(StaticData.typeOfCertificate);
			if (cList.contains(certificate)) {
				certificate = certificate;
			} else if(StaticData.certificateType.contains(certificate)){
				
			}else {
				throw new Exception("Invalid Certificate.");
			}
			String ay = fyToAy(fy);

			List<String> filesToSend = getAllFiles(fy, ay, q, certificate, tan, pan);
			
			if (filesToSend.isEmpty()) {
				// return no certificate found
				throw new Exception("No certificate found.");
			} else {
				// create zip and send the file also delete the file after sending
				response.setStatus(HttpServletResponse.SC_OK);
				response.addHeader("Content-Disposition", " attachment; filename="+pan+"_"+fy+"_"+q+ ".zip");
				response.setHeader("Content-Type", "application/zip");
				
				ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
				int i = filesToSend.size();
				for (String filePath : filesToSend) {
					File file = new File(filePath);
					// new zip entry and copying inputstream with file to zipOutputStream, after all closing streams
					zipOutputStream.putNextEntry( new ZipEntry( file.getName().split( Pattern.quote("."), -1)[0] + "_" + i + ".pdf" ) );
					FileInputStream fileInputStream = new FileInputStream(file);
					IOUtils.copy(fileInputStream, zipOutputStream);
					fileInputStream.close();
					zipOutputStream.closeEntry();
					i--;														
				}
				zipOutputStream.close();
			}
		} catch (Exception e) {
			// send dto object with error
			ermsg.setMessage("File not found");
			ermsg.setExceptionMsg(e.getMessage());
			ermsg.setEntityName("Certificate");
			response.setStatus(400);
			ResponseEntity re = new ResponseEntity<Object>(ermsg, HttpStatus.BAD_REQUEST);
			try {
				response.getWriter().write(re.getBody().toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private List<String> getAllFiles(String fy, String ay, String q, String certificate, String tan, String pan) {

		List<String> filePaths = new ArrayList<String>();

		String path = StaticData.CertificatePath; //?????
		String[] c = certificate.split("-");
		String[] t = tan.split("-");
		
	

		if (tan.equals("ALL TAN") && q.equals("ALL QUARTER")) {
			for (String q1 : StaticData.Quarter) {
				for (String t1 : StaticData.Tan) {
					t = t1.split("-");
					String pdfFileName = pan + "_" + q1 + "_" + ay + ".pdf";
					String filePath = path + "download/" + fy + "/" + q1 + "/" + c[0] + "/" + t[0] + "/" + pdfFileName;
					addFileIfExist(filePath, filePaths);
				}
			}
		} else if (tan.equals("ALL TAN")) {
			for (String t1 : StaticData.Tan) {
				t = t1.split("-");
				String pdfFileName = pan + "_" + q + "_" + ay + ".pdf";
				String filePath = path + "download/" + fy + "/" + q + "/" + c[0] + "/" + t[0] + "/" + pdfFileName;
				addFileIfExist(filePath, filePaths);
			}

		}else if(q.equals("ALL QUARTER")){
			for (String q1 : StaticData.Quarter) {
				String pdfFileName = pan + "_" + q1 + "_" + ay + ".pdf";
				String filePath = path + "download/" + fy + "/" + q1 + "/" + c[0] + "/" + t[0] + "/" + pdfFileName;
				addFileIfExist(filePath, filePaths);
			}
		} 
		else {
			String pdfFileName = pan + "_" + q + "_" + ay + ".pdf";
			String filePath = path + "download/" + fy + "/" + q + "/" + c[0] + "/" + t[0] + "/" + pdfFileName;
			
			addFileIfExist(filePath, filePaths);
		}
	
	
		
		return filePaths;
	}

	private void addFileIfExist(String filePath, List<String> filePaths) {
		if (new File(filePath).exists()) {
			filePaths.add(filePath);
		}
	}

	public String fyToAy(String fy) {
		String Ay = null;
		String[] yr = fy.split("-");
		int y1 = Integer.parseInt(yr[0]);
		int y2 = Integer.parseInt(yr[1]);

		int Ay1 = y1 + 1;
		int Ay2 = y2 + 1;
		return Ay1 + "-" + Ay2;

	}

	public String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;

	}

}
