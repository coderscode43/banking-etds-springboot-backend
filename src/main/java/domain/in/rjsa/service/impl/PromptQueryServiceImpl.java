package domain.in.rjsa.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.PromptQueryDao;
import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.excel.PromptQueryExcel;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.form.StaticDataModel;
import domain.in.rjsa.model.wrapper.FileTypeWrapper;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.CHALLANService;
import domain.in.rjsa.service.CorrectionRequestService;
import domain.in.rjsa.service.DEDUCTORDETAILSService;
import domain.in.rjsa.service.LDCService;
import domain.in.rjsa.service.LogsService;
import domain.in.rjsa.service.PanUpdateListService;
import domain.in.rjsa.service.PromptQueryService;
import domain.in.rjsa.service.Regular24QDeducteeService;
import domain.in.rjsa.service.Regular26QDeducteeService;
import domain.in.rjsa.service.Regular27EQDeducteeService;
import domain.in.rjsa.service.Regular27QDeducteeService;
import domain.in.rjsa.service.RegularReturnService;
import domain.in.rjsa.service.STATEMENTSTATUSService;
import domain.in.rjsa.service.TotalAmountSerivce;
import domain.in.rjsa.util.DownloadCertificateUtil;
import domain.in.rjsa.util.DownloadCertificateZipUtil;
import domain.in.rjsa.util.StaticData;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Transactional("transactionManager")
@Service("PromptQueryService")
public class PromptQueryServiceImpl implements PromptQueryService {

	@Autowired
	private PromptQueryDao dao;

	@Autowired
	private StaticDataDao sDao;

	@Autowired
	private BranchService branchService ;
	@Autowired
	private Regular24QDeducteeService deductee24QService;
	@Autowired
	private Regular26QDeducteeService deductee26QService;
	@Autowired
	private Regular27QDeducteeService deductee27QService;
	@Autowired
	private Regular27EQDeducteeService deductee27EQService;
	@Autowired
	private CHALLANService challanService;
	@Autowired
	private TotalAmountSerivce totalAmountSerivce;
	@Autowired
	private PanUpdateListService panUpdateListService;
	@Autowired
	private STATEMENTSTATUSService statementstatusService;
	@Autowired
	private DEDUCTORDETAILSService deductordetailsService;
	@Autowired
	private CorrectionRequestService correctionRequestService;
	@Autowired
	private RegularReturnService regularReturnService;
	@Autowired
	private LDCService ldcService;
	@Autowired
	private LogsService logsService;

	PromptQueryExcel promptQueryExcel;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String processRequest(HashMap<String, Object> data, FileTypeWrapper fileTypeWrapper) throws CustomException, Exception {
		String fileType = fileTypeWrapper.getFileType();
		String filePath = "";
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(data);

		// Get generated query
		String jsonQuery = getSqlQuery(json);
		if (!jsonQuery.isBlank()) {

			JSONObject jsonObject = new JSONObject(jsonQuery);

			JSONArray missingFields = jsonObject.getJSONArray("missing_fields");
			logger.info("Missing_fields: " + missingFields);

			StringBuilder errorMsg = new StringBuilder();

			if (!missingFields.isNull(0)) {
				errorMsg.append("Kindly Provide : ");

				for (int i = 0; i < missingFields.length(); i++) {
					if (i > 0) {
						errorMsg.append(", ");
					}
					errorMsg.append(missingFields.getString(i));
				}
				logger.info("ErrorMsg: " + errorMsg.toString());
				return errorMsg.toString();
			}

			LinkedHashMap<String, Object> extracted_fields = new LinkedHashMap<String, Object>();
			extracted_fields = mapper.readValue(jsonObject.get("extracted_fields").toString(),
					new TypeReference<LinkedHashMap<String, Object>>() {
					});
			logger.info("Extracted_fields: " + extracted_fields);

			String tableName = jsonObject.get("TABLE_NAME").toString();
			logger.info("TABLE_NAME: " + tableName);

			if (fileType.equalsIgnoreCase("EXCEL")) {
				filePath = getExcelFile(tableName, extracted_fields);
			} else if (fileType.equalsIgnoreCase("PDF")) {
				filePath = getPdfFile(extracted_fields,fileTypeWrapper);
			}

			return filePath;

		}
		return null;

	}

	private String getPdfFile(LinkedHashMap<String, Object> extracted_fields, FileTypeWrapper fileTypeWrapper) {
		setStaticData();
		String path = StaticData.CertificatePath;
		logger.info("Path - " + path);

		// Initialize variables
		String TAN = extracted_fields.get("TAN") != null ? extracted_fields.get("TAN").toString() : "";
		String PAN = extracted_fields.get("PAN") != null ? extracted_fields.get("PAN").toString() : "";
		String TYPE_OF_CERTIFICATE = extracted_fields.get("CERTIFICATE_SUBTYPE") != null ? extracted_fields.get("CERTIFICATE_SUBTYPE").toString().replace(" ", "") : "";
		String QUARTER = extracted_fields.get("QUARTER") != null ? extracted_fields.get("QUARTER").toString() : "";
		String FINANCIAL_YEAR = extracted_fields.get("FINANCIAL_YEAR") != null ? extracted_fields.get("FINANCIAL_YEAR").toString() : "";

		
		// Check for empty fields
		String[] fields = {TAN, PAN, TYPE_OF_CERTIFICATE, QUARTER, FINANCIAL_YEAR};
		String[] fieldNames = {"TAN","PAN","TYPE OF CERTIFICATE", "QUARTER", "FINANCIAL YEAR"};
		StringBuilder missingFields = new StringBuilder(); 

		for (int i = 0; i < fields.length; i++) {
		    if (fields[i].equals("")) {
		        if (missingFields.length() > 0) {
		            missingFields.append(", "); 
		        }
		        missingFields.append(fieldNames[i]);
		    }
		}
		if (missingFields.length() > 0) {
		    return missingFields.toString() + " is missing";
		}
		
		String filePath = "";
		
		//For ZIP Testing
		TAN = "ALL_TAN";
		QUARTER = "ALL_QUARTER";
		//For Testing
		
		if(TAN.equalsIgnoreCase("ALL_TAN") || QUARTER.equalsIgnoreCase("ALL_QUARTER")) {
			List<String> filesToSend =  DownloadCertificateUtil.downloadPdfWithoutDirectPath(path, TAN, PAN, FINANCIAL_YEAR, QUARTER,TYPE_OF_CERTIFICATE);
			if (filesToSend.isEmpty()) {
				return "No certificate found.";
			}
			filePath = DownloadCertificateZipUtil.createZip(filesToSend);
			fileTypeWrapper.setFileType("ZIP");

		}else {
			filePath = DownloadCertificateUtil.downloadPdfWithDirectPath(path, TAN, PAN, FINANCIAL_YEAR, QUARTER,TYPE_OF_CERTIFICATE);
		}
		
		return filePath;
	}


	private String getExcelFile(String tableName, LinkedHashMap<String, Object> extracted_fields) {
		try {
			String excelFilePath = "";
			switch (tableName) {
			case "BRANCH":
				excelFilePath = branchService.createUserExcel(extracted_fields);
				break;
			case "REGULAR24QDEDUCTEE":
				excelFilePath = deductee24QService.createUserExcel(extracted_fields);
				break;
			case "REGULAR26QDEDUCTEE":
				excelFilePath = deductee26QService.createUserExcel(extracted_fields);
				break;
			case "REGULAR27QDEDUCTEE":
				excelFilePath = deductee27QService.createUserExcel(extracted_fields);
				break;
			case "REGULAR27EQDEDUCTEE":
				excelFilePath = deductee27EQService.createUserExcel(extracted_fields);
				break;
			case "CHALLAN":
				excelFilePath = challanService.createUserExcel(extracted_fields);
				break;
			case "TOTALAMOUNT":
				excelFilePath = totalAmountSerivce.createUserExcel(extracted_fields);
				break;
			case "PANUPDATELIST":
				excelFilePath = panUpdateListService.createUserExcel(extracted_fields);
				break;
			case "STATEMENTSTATUS":
				excelFilePath = statementstatusService.createUserExcel(extracted_fields);
				break;
			case "DEDUCTORDETAILS":
				excelFilePath = deductordetailsService.createUserExcel(extracted_fields);
				break;
			case "CORRECTIONREQUEST":
				excelFilePath = correctionRequestService.createUserExcel(extracted_fields);
				break;
			case "REGULARRETURN":
				excelFilePath = regularReturnService.createUserExcel(extracted_fields);
				break;
			case "LDC":
				excelFilePath = ldcService.createUserExcel(extracted_fields);
				break;
			case "LOGS":
				excelFilePath = logsService.createUserExcel(extracted_fields);
				break;
			default:
				break;
			}

			logger.info("Excel file path: " + excelFilePath);
			// Read the file into byte array
			byte[] fileBytes = Files.readAllBytes(Paths.get(excelFilePath));

			// Encode the byte array to Base64
			return Base64.getEncoder().encodeToString(fileBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getSqlQuery(String json) {
		String sqlQuery = "";
		try {
			OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60, TimeUnit.SECONDS)
					.readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build();

			// create RequestBody
			RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json"));

			// Build the request
			Request request = new Request.Builder().url("http://192.168.0.110:5000/process_query").post(requestBody)
					.build();

			// Execute the request
			try (Response response = client.newCall(request).execute()) {
				logger.info(response.toString());
				if (!response.isSuccessful()) {
					throw new IOException("Unexpected code " + response);
				} else {
					sqlQuery = response.body().string().toString();
				}
			}

		} catch (ConnectException e) {
			throw new CustomException("Server not Reachable");
		} catch (Exception e) {
			if (e.getMessage().contains("connect timed out") || e.getMessage().contains("timeout")) {
				throw new CustomException("Server not Reachable");
			}
		}
		return sqlQuery;
	}

//	public String formatList(List<HashMap<String, Object>> list, String fileType, String userText) throws IOException {
//		Set<String> keySet = new LinkedHashSet<String>();
//		String encodedFile = "";
//
//		if (fileType.equalsIgnoreCase("PDF")) {
//			encodedFile = generatePdf(userText, keySet);
//		}
//
//		if (list != null && !list.isEmpty()) {
//			keySet = list.get(0).keySet();
//
//			if (fileType.equalsIgnoreCase("EXCEL")) {
//				encodedFile = generateExcel(list, keySet);
//			} else if (fileType.equalsIgnoreCase("CSV")) {
//				encodedFile = generateCsv(list, keySet);
//			}
//		}
//		return encodedFile;
//
//	}

	public String formatList(List<HashMap<String, Object>> list, String fileType) throws IOException {
		Set<String> keySet = new LinkedHashSet<String>();
		String encodedFile = "";

		if (list != null && !list.isEmpty()) {
			keySet = list.get(0).keySet();

			if (fileType.equalsIgnoreCase("EXCEL")) {
				encodedFile = generateExcel(list, keySet);
			} else if (fileType.equalsIgnoreCase("CSV")) {
				encodedFile = generateCsv(list, keySet);
			}
		}
		return encodedFile;

	}

//	private String generatePdf(String userText, Set<String> keySet) {
//
//		String filePath = getCertificatePath(userText);
//
//		// Check if the file exists at the given path
//		File pdfFile = new File(filePath);
//		if (pdfFile.exists() && pdfFile.isFile()) {
//			try {
//				// Read the file into a byte array
//				byte[] fileBytes = Files.readAllBytes(pdfFile.toPath());
//
//				// Encode the byte array into a Base64 string
//				return encodeToBase64(fileBytes);
//			} catch (IOException e) {
//				// Handle the exception (file not readable, IO error, etc.)
//				e.printStackTrace();
//				return null;
//			}
//		} else {
//			// Handle the case where the file doesn't exist
//			System.out.println("File not found: " + filePath);
//			return null;
//		}
//	}

//	public String getCertificatePath(String userText) {
////	    setStaticData();
////
//		String path = StaticData.CertificatePath;
////	    // Printing user input for debugging
////	    System.out.println("User input: " + userText);
////
////	    // Initialize variables
////	    String tan = "";
////	    String form = "";
//		String pan = "";
////	    String quarter = "";
////	    String financialYear = "";
////
////	    // Regular expressions for extracting values from the input string (case-insensitive)
////	    String tanPattern = "(?i)([A-Z]{4}[0-9]{5}[A-Z]{1})";  // Fixed regex for TAN
//		String panPattern = "(?i)([A-Z]{5}[0-9]{4}[A-Z]{1})"; // Fixed regex for PAN
////	    String quarterPattern = "(?i)(Q[1-4]|first|second|third|fourth)\\s?(quarter)?"; // Handle both Q1 and first quarter
////	    String financialYearPattern = "(?i)fy\\s?(\\d{4}-\\d{2})"; // Fixed regex to handle space after 'fy'
////	    String formPattern = "(?i)(Form\\s?16A|Form\\s?16|Form\\s?27D)"; // To capture one of the 3 form types (case-insensitive)
////
////	    // Extract tan using regex
////	    Pattern tanRegex = Pattern.compile(tanPattern);
////	    Matcher tanMatcher = tanRegex.matcher(userText);
////	    if (tanMatcher.find()) {
////	        tan = tanMatcher.group(1);
////	        System.out.println("TAN: " + tan);
////	    }
////
////	    // Extract pan using regex
//		Pattern panRegex = Pattern.compile(panPattern);
//		Matcher panMatcher = panRegex.matcher(userText);
//		if (panMatcher.find()) {
//			pan = panMatcher.group(1);
//			System.out.println("PAN: " + pan);
//		}
////
////	    // Extract quarter using regex
////	    Pattern quarterRegex = Pattern.compile(quarterPattern);
////	    Matcher quarterMatcher = quarterRegex.matcher(userText);
////	    if (quarterMatcher.find()) {
////	        quarter = quarterMatcher.group(1);
////	        System.out.println("Quarter: " + quarter);
////
////	        // Handle cases where "first", "second" etc. are found and map them to Q1, Q2, Q3, Q4
////	        if (quarter.equalsIgnoreCase("first")) {
////	            quarter = "Q1";
////	        } else if (quarter.equalsIgnoreCase("second")) {
////	            quarter = "Q2";  // This will handle "second" correctly
////	        } else if (quarter.equalsIgnoreCase("third")) {
////	            quarter = "Q3";
////	        } else if (quarter.equalsIgnoreCase("fourth")) {
////	            quarter = "Q4";
////	        }
////	    }
////
////	    // Extract financial year using regex
////	    Pattern financialYearRegex = Pattern.compile(financialYearPattern);
////	    Matcher financialYearMatcher = financialYearRegex.matcher(userText);
////	    if (financialYearMatcher.find()) {
////	        financialYear = financialYearMatcher.group(1);
////	        System.out.println("Financial Year: " + financialYear);
////	    }
////
////	    // Extract form type using regex
////	    Pattern formRegex = Pattern.compile(formPattern);
////	    Matcher formMatcher = formRegex.matcher(userText);
////	    if (formMatcher.find()) {
////	        form = formMatcher.group(1);
////	        System.out.println("Form Type: " + form);
////	    }
////
////	    // Now create the pdfFileName using the extracted variables
////	    String pdfFileName = pan.toUpperCase() + "_" + quarter.toUpperCase() + "_" + financialYear + ".pdf";
////	    System.out.println("Pdf - " + pdfFileName);
////
////	    String filePath = path + "download/" + financialYear + "/" + quarter.toUpperCase() + "/" + form + "/" + tan.toUpperCase() + "/"
////	            + pdfFileName;
////	    System.out.println("Path - " + filePath);
//
//		String filePath = path;
//		String filename = "";
//
//		if (pan.equalsIgnoreCase("AAPFG1742E") || pan.equalsIgnoreCase("AABFV4478L")) {
//			filename = pan.toUpperCase() + "_Q1_2024-25.pdf";
//			filePath = filePath + "download/2023-24/Q1/Form16A/AHMT05074G/" + filename;
//		}
//
//		if (pan.equalsIgnoreCase("AADHV7680J") || pan.equalsIgnoreCase("AADTA6039Q")) {
//			filename = pan.toUpperCase() + "_Q2_2024-25.pdf";
//			filePath = filePath + "download/2023-24/Q2/Form16A/AHMT05074G/" + filename;
//		}
//
////		if (pan.equalsIgnoreCase("AAGPT8254P") || pan.equalsIgnoreCase("AFPPA9174F")) {
////			filename = pan.toUpperCase() + "_Q1_2024-25.pdf";
////			filePath = filePath + "download/2023-24/Q1/Form16A/AHMT05074G/" + filename;
////		}
////
////		if (pan.equalsIgnoreCase("FQKPK0019N") || pan.equalsIgnoreCase("BKQPJ5119L")) {
////			filename = pan.toUpperCase() + "_Q1_2024-25.pdf";
////			filePath = filePath + "download/2023-24/Q1/Form16A/AHMT05074G/" + filename;
////		}
//
//		return filePath;
//	}

	// Generate Excel
	private String generateExcel(List<HashMap<String, Object>> list, Set<String> keySet) {
		promptQueryExcel = new PromptQueryExcel();
		promptQueryExcel.initialise(keySet);

		Workbook wb = promptQueryExcel.getWorkbook();
		Sheet sheet = wb.getSheet("Sheet-1");

		int rowCount = 1;
		int part = 1;
		int srNo = 1;
		int entryCount = 0;

		for (HashMap<String, Object> data : list) {
			entryCount++;

			Row row = sheet.createRow(rowCount);
			row.createCell(0).setCellValue(srNo);

			int cellNum = 1;
			for (String key : keySet) {
				if (key.equalsIgnoreCase("ID")) {
					continue;
				}
				if (data.get(key) == null) {
					row.createCell(cellNum).setCellValue(" ");
				} else {
					row.createCell(cellNum).setCellValue(data.get(key).toString());
				}

				cellNum++;
			}

			rowCount++;
			srNo++;

			if (entryCount == 1000000) {
				part++;
				sheet = promptQueryExcel.setColumnNames("Sheet-" + part, keySet);
				rowCount = 1;
				entryCount = 0;
			}
		}

		byte[] filebytes = promptQueryExcel.close();
		return encodeToBase64(filebytes);
	}

	// Generate CSV
	private String generateCsv(List<HashMap<String, Object>> list, Set<String> keySet) throws IOException {
		String encodedFile = "";

		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				PrintWriter writer = new PrintWriter(outputStream, true)) {

			// Write the headers
			writer.println(String.join(",", keySet));

			// Write the rows
			Object value = null;
			for (HashMap<String, Object> map : list) {
				StringBuilder sb = new StringBuilder();

				for (String key : keySet) {
					Pattern pattern = Pattern.compile(".*Date.*", Pattern.CASE_INSENSITIVE);
					Matcher matcher = pattern.matcher(key);

					if (map.get(key) != null && matcher.matches()) {
						value = new SimpleDateFormat("dd-MM-yyyy").format(map.get(key));
					} else {
						value = map.get(key);
					}

					// Handle null values and escape quotes
					String valueStr = (value == null) ? "" : value.toString().replace("\"", "\"\"");
					sb.append("\"").append(valueStr).append("\"").append(",");
				}

				// Remove trailing comma
				if (sb.length() > 0) {
					sb.setLength(sb.length() - 1);
				}
				writer.println(sb.toString());
			}

			encodedFile = encodeToBase64(outputStream.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodedFile;
	}

	private String encodeToBase64(byte[] fileBytes) {
		String base64String = "";
		if (fileBytes.length > 0) {
			base64String = Base64.getEncoder().encodeToString(fileBytes);
		}
		return base64String;
	}

	private void setStaticData() {
		if (StaticData.ClientName == null) {
			HashMap<String, Object> sd = new HashMap<String, Object>();
			List<StaticDataModel> list = sDao.findall(sd, 0, 100);
			String[] stringArray;
			String xString;
			for (StaticDataModel list1 : list) {
				String key = list1.getKey();
				switch (key) {
				case "CertificatePath":
					xString = list1.getValue();
					StaticData.CertificatePath = xString;
					break;

				default:
					break;
				}
			}
		}
	}

}
