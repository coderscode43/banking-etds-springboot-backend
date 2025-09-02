package domain.in.rjsa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.utils.IOUtils;
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
import org.springframework.web.bind.annotation.ResponseBody;

import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.fy.MisReport;
import domain.in.rjsa.service.MisReportService;
import domain.in.rjsa.util.StaticData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/apigenerateReport")
public class MisReportController extends AbstractControllerFY<Long, MisReport, MisReportService> {
	@Autowired
	MisReportService service;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public MisReportService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<MisReport> getEntity() {
		// TODO Auto-generated method stub
		return MisReport.class;
	}

	@RequestMapping(value = "/add/{fy}/{branchCode}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity,
			@PathVariable Long branchCode, @PathVariable String fy) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		logger.info("Creating new Return instance");
		adminValidation(entity);
		entity.put("branchCode", branchCode);
		entity.put("fy", fy);
		String userName = getPrincipal();
		entity.put("userName", userName);
		create(entity);
		addLogs("Add"); // ermsg.setMessage(" Saved Successfully");
		return new ResponseEntity<Object>(HttpStatus.CREATED);

	}

	@RequestMapping(value = "/files/{tan}/{form}/{fy}/{q}/{typeOfReport}", method = RequestMethod.GET, produces = "application/octet-stream")
	public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable String tan,
			@PathVariable String form, @PathVariable String fy, @PathVariable String q,
			@PathVariable String typeOfReport) {

		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
			String userName = getPrincipal();
			if (userName == null || "anonymousUser".equals(userName)) {
				String auth = request.getHeader("directDownloadAuth");
				if (auth != null) {
					if (auth.equals(StaticData.directDownloadAuth)) {
						logger.info("Direct download certificate through auth");
					} else {
						throw new Exception("Invalid AUTH");
					}
				} else {
					throw new Exception("Invalid AUTH");
				}
			}
			tan = tan.trim();
			// verify regx tan or if All TAN
			List<String> tanList = Arrays.asList(StaticData.Tan);
			logger.info(tanList + "^" + tan);
			if (tanList.contains(tan) || tan.equalsIgnoreCase("ALL TAN")) {
				tan = tan;
			} else {
				logger.info("Invalid TAN");
				throw new Exception("Invalid TAN.");
			}
			// verify regX fy
			List<String> yearList = Arrays.asList(StaticData.financialYear);
			if (yearList.contains(fy)) {
				fy = fy;
			} else {
				logger.info("Invalid Financial Year");
				throw new Exception("Invalid Financial Year.");
			}
			
			//If Type of report is Annual Report quarter and typeOfForm is empty
			if (!typeOfReport.equals("Annual Report")) {
				// verify q
				List<String> qList = Arrays.asList(StaticData.Quarter);
				if (qList.contains(q) || q.equalsIgnoreCase("ALL QUARTER")) {
					q = q;
				} else {
					logger.info("Invalid Quarter");
					throw new Exception("Invalid Quarter.");
				}
				// verify form
				List<String> fList = Arrays.asList(StaticData.typeOfForm);
				for (String f : fList) {
					if (f.split(Pattern.quote("-"))[0].equalsIgnoreCase(form.split(Pattern.quote("-"))[0])) {
						form = form;
						break;
					} else if (StaticData.formType.contains(form.split(Pattern.quote("-"))[0])) {

					} else {
						logger.info("Invalid certificate");
						throw new Exception("Invalid Certificate.");
					}
				}
			}
			
//				String ay = fyToAy(fy);

			List<String> filesToSend = getAllFiles(fy, q, form, tan, typeOfReport);
			System.out.println(filesToSend);
			if (filesToSend.isEmpty()) {
				// return no certificate found
				throw new Exception("No Files found.");
			} else {
				// create zip and send the file also delete the file after sending
				String[] t = tan.split(Pattern.quote("-"), -1);
				String[] f = form.split(Pattern.quote("-"), -1);
				response.setStatus(HttpServletResponse.SC_OK);
				response.addHeader("Content-Disposition", " attachment; filename=" + typeOfReport + "_" + t[0] + "_"
						+ f[0] + "_" + fy + "_" + q + ".zip");
				response.setHeader("Content-Type", "application/zip");

				ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
				int i = filesToSend.size();
				for (String filePath : filesToSend) {
					File file = new File(filePath);
					// new zip entry and copying inputstream with file to zipOutputStream, after all
					// closing streams
					zipOutputStream.putNextEntry(
							new ZipEntry(file.getName()));
					FileInputStream fileInputStream = new FileInputStream(file);
					IOUtils.copy(fileInputStream, zipOutputStream);
					fileInputStream.close();
					zipOutputStream.closeEntry();
					i--;
					logger.info(filePath);
				}
				zipOutputStream.close();
				System.out.println("File Download");
				addLogs("Certificate");
			}

		} catch (Exception e) {
			// send dto object with error
			logger.info(e.getMessage());
			ermsg.setMessage("File not found for this PAN Number");
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

	private List<String> getAllFiles(String fy, String q, String form, String tan, String typeOfReport) {
		List<String> filePaths = new ArrayList<String>();

		String path = StaticData.TaxAuditReportPath;
		String[] f = form.split("-");
		String[] t = tan.split("-");
		System.out.println(f[0].trim() + "&&" + t[0]);

		if (tan.equals("ALL TAN") && q.equals("ALL QUARTER")) {
			for (String q1 : StaticData.Quarter) {
				for (String t1 : StaticData.Tan) {
					t = t1.split("-");
					String filePath = path + fy + "/" + q1 + "/" + f[0].trim() + "/" + t[0] + "/" + typeOfReport;
					logger.info(filePath);
					addFileIfExist(filePath, filePaths);
				}
			}
		} else if (tan.equals("ALL TAN")) {
			for (String t1 : StaticData.Tan) {
				t = t1.split("-");
				String filePath = ""; 
				if (typeOfReport.equals("Annual Report")) {
					filePath = path + fy + "/" + typeOfReport + "/" + t[0];
				}
				else {
					filePath = path + fy + "/" + q + "/" + f[0].trim() + "/" + t[0] + "/" + typeOfReport;
				}
				logger.info(filePath);
				addFileIfExist(filePath, filePaths);
			}

		} else if (q.equals("ALL QUARTER")) {
			for (String q1 : StaticData.Quarter) {
				String filePath = path + fy + "/" + q1 + "/" + f[0].trim() + "/" + t[0] + "/" + typeOfReport;
				logger.info(filePath);
				addFileIfExist(filePath, filePaths);
			}
		} else {
			String filePath = ""; 
			if (typeOfReport.equals("Annual Report")) {
				filePath = path + fy + "/" + typeOfReport + "/" + t[0];
			}
			else {
				filePath = path + fy + "/" + q + "/" + f[0].trim() + "/" + t[0] + "/" + typeOfReport;
			}
 			logger.info(filePath);
			addFileIfExist(filePath, filePaths);
		}
		if (filePaths.isEmpty()) {
			if (tan.equals("ALL TAN") && q.equals("ALL QUARTER")) {
				for (String q1 : StaticData.Quarter) {
					for (String t1 : StaticData.Tan) {
						t = t1.split("-");
						String filePath = path + fy + "/" + q1 + "/" + f[0].trim() + "/" + t[0] + "/" + typeOfReport;
						logger.info(filePath);
						addFileIfExist(filePath, filePaths);
					}
				}
			} else if (tan.equals("ALL TAN")) {
				for (String t1 : StaticData.Tan) {
					t = t1.split("-");
					String filePath = ""; 
					if (typeOfReport.equals("Annual Report")) {
						filePath = path + fy + "/" + typeOfReport + "/" + t[0];
					}
					else {
						filePath = path + fy + "/" + q + "/" + f[0].trim() + "/" + t[0] + "/" + typeOfReport;
					}
					logger.info(filePath);
					addFileIfExist(filePath, filePaths);
				}

			} else if (q.equals("ALL QUARTER")) {
				for (String q1 : StaticData.Quarter) {
					String filePath = path + fy + "/" + q1 + "/" + f[0].trim() + "/" + t[0] + "/" + typeOfReport;
					logger.info(filePath);
					addFileIfExist(filePath, filePaths);
				}
			} else {
				String filePath = ""; 
				if (typeOfReport.equals("Annual Report")) {
					filePath = path + fy + "/" + typeOfReport + "/" + t[0];
				}
				else {
					filePath = path + fy + "/" + q + "/" + f[0].trim() + "/" + t[0] + "/" + typeOfReport;
				}
				logger.info(filePath);
				addFileIfExist(filePath, filePaths);
			}
		}

		return filePaths;
	}

	private void addFileIfExist(String filePath, List<String> filePaths) {
		File file = new File(filePath);
		if (file.exists() && file.listFiles().length > 0) {
			for (File f : file.listFiles()) {
				System.out.println(f.getAbsolutePath());
				logger.info(f.getAbsolutePath());
				filePaths.add(f.getAbsolutePath());
			}
		}
	}
}
