package domain.in.rjsa.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.model.form.StaticDataModel;

public class DownloadCertificateUtil {

	@Autowired
	private static StaticDataDao sDao;

	private static final Logger logger = LoggerFactory.getLogger(DownloadCertificateUtil.class);

	public static String downloadPdfWithDirectPath(String path, String TAN, String PAN, String FINANCIAL_YEAR,
			String QUARTER, String TYPE_OF_CERTIFICATE) {

		// Validate TAN
		String tanPattern = "(?i)([A-Z]{4}[0-9]{5}[A-Z]{1})"; // Regex for TAN
		Pattern tanPatternCompiled = Pattern.compile(tanPattern);
		Matcher tanMatcher = tanPatternCompiled.matcher(TAN);
		if (!tanMatcher.matches()) {
			logger.info("TAN: " + TAN + " : Does not match the pattern!!");
			return "Invalid TAN";
		}

		// Validate PAN
		String panPattern = "(?i)([A-Z]{5}[0-9]{4}[A-Z]{1})"; // Regex for PAN
		Pattern panPatternCompiled = Pattern.compile(panPattern);
		Matcher panMatcher = panPatternCompiled.matcher(PAN);
		if (!panMatcher.matches()) {
			logger.info("PAN: " + PAN + " : Does not match the pattern!!");
			return "Invalid PAN";
		}

		// Creating pdfFileName using the extracted variables
		String AY = fyToAy(FINANCIAL_YEAR);
		String pdfFileName = PAN.toUpperCase() + "_" + QUARTER.toUpperCase() + "_" + AY + ".pdf";
		logger.info("Pdf - " + pdfFileName);

		String filePath = path + "download/" + FINANCIAL_YEAR + "/" + QUARTER.toUpperCase() + "/" + TYPE_OF_CERTIFICATE
				+ "/" + TAN.toUpperCase() + "/" + pdfFileName;
		logger.info("Full file path - " + filePath);

		byte[] fileBytes = null;
		try {
			fileBytes = Files.readAllBytes(Paths.get(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Base64.getEncoder().encodeToString(fileBytes);
	}

	public static List<String> downloadPdfWithoutDirectPath(String path, String TAN, String PAN, String FINANCIAL_YEAR,
			String QUARTER, String TYPE_OF_CERTIFICATE) {
		setStaticData();
		List<String> filePaths = new ArrayList<>();
		String AY = fyToAy(FINANCIAL_YEAR);
		if (TAN.equals("ALL_TAN") && QUARTER.equals("ALL_QUARTER")) {
			for (String q : StaticData.Quarter) {
				for (String t : StaticData.Tan) {
					String pdfFileName = PAN + "_" + q + "_" + AY + ".pdf";
					String[] tan = t.split("-");
					String filePath = path + "download/" + FINANCIAL_YEAR + "/" + q + "/" + TYPE_OF_CERTIFICATE + "/"
							+ tan[0] + "/" + pdfFileName;
					addFileIfExist(filePath, filePaths);
				}
			}
		} else if (TAN.equals("ALL_TAN")) {
			for (String t : StaticData.Tan) {
				String pdfFileName = PAN + "_" + QUARTER + "_" + AY + ".pdf";
				String[] tan = t.split("-");
				String filePath = path + "download/" + FINANCIAL_YEAR + "/" + QUARTER + "/" + TYPE_OF_CERTIFICATE + "/"
						+ tan[0] + "/" + pdfFileName;
				addFileIfExist(filePath, filePaths);
			}

		} else if (QUARTER.equals("ALL_QUARTER")) {
			for (String q : StaticData.Quarter) {
				String pdfFileName = PAN + "_" + q + "_" + AY + ".pdf";
				pdfFileName = PAN + "_" + AY + ".pdf";
				String filePath = path + "download/" + FINANCIAL_YEAR + "/" + q + "/" + TYPE_OF_CERTIFICATE + "/" + TAN
						+ "/" + pdfFileName;
				addFileIfExist(filePath, filePaths);
			}
		}
		logger.info("FilePaths : " + filePaths.toString());
		return filePaths;
	}

	private static void addFileIfExist(String filePath, List<String> filePaths) {
		if (new File(filePath).exists()) {
			logger.info(filePath);
			filePaths.add(filePath);
		}
	}

	private static String fyToAy(String fy) {
		String[] yr = fy.split("-");
		int y1 = Integer.parseInt(yr[0]);
		int y2 = Integer.parseInt(yr[1]);
		int Ay1 = y1 + 1;
		int Ay2 = y2 + 1;
		return Ay1 + "-" + Ay2;
	}

	private static void setStaticData() {
		if (StaticData.ClientName == null) {
			HashMap<String, Object> sd = new HashMap<String, Object>();
			List<StaticDataModel> list = sDao.findall(sd, 0, 100);
			String[] stringArray;
			String xString;
			for (StaticDataModel list1 : list) {
				String key = list1.getKey();
				switch (key) {
				case "Tan":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.Tan = stringArray;
					break;
				case "Quarter":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.Quarter = stringArray;
					break;
				default:
					break;
				}
			}
		}
	}
}
