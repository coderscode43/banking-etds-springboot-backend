package domain.in.rjsa.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.PromptQueryDao;
import domain.in.rjsa.excel.PromptQueryExcel;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.wrapper.FileTypeWrapper;
import domain.in.rjsa.service.PromptQueryService;
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

	PromptQueryExcel promptQueryExcel;

	@Override
	public String processRequest(HashMap<String, Object> data, FileTypeWrapper fileTypeWrapper) throws CustomException, Exception {
		String fileType = data.get("fileType").toString();
		data.remove("fileType");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(data);

		// Get generated query
		String jsonQuery = getSqlQuery(json);
		if (!jsonQuery.isBlank()) {
			Map<String, String> map = new HashMap<String, String>();
			map = mapper.readValue(jsonQuery, new TypeReference<HashMap<String, String>>() {
			});

			if (map.get("query").equalsIgnoreCase("I dont know!")) {
				throw new CustomException("I dont know!");

			} else {
				// Get data from database
				List<HashMap<String, Object>> list = dao.getData(map.get("query"));
				return formatList(list, fileType);
			}
		}
		return null;

	}

	private String getSqlQuery(String json) {
		String sqlQuery = "";
		try {
			OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60, TimeUnit.SECONDS)
					.readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build();

			// create RequestBody
			RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json"));

			// Build the request
			Request request = new Request.Builder().url("http://192.168.0.135:5002/apiTextToSQL").post(requestBody)
					.build();

			// Execute the request
			try (Response response = client.newCall(request).execute()) {
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

}
