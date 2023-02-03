package domain.in.rjsa.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.dao.UploadCertificateDao;
import domain.in.rjsa.excel.UploadCertificateExcel;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.fy.UploadCertificate;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.UploadCertificateService;
import domain.in.rjsa.util.StaticData;

@Transactional("transactionManager")
@Service("uploadCertificateService")
public class UploadCertificateServiceImpl extends AbstractServiceForm<Long, UploadCertificate, UploadCertificateDao>
		implements UploadCertificateService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UploadCertificateDao dao;

	UploadCertificateExcel uploadCertificateExcel;
	public static String path;
	public String ExcelFile;

	@Override
	public UploadCertificateDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public UploadCertificate getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public void saveDocument(MultipartFile downloadFile, HashMap<String, String> lessonMap) {
		try {
			UploadCertificate uploadCertificate = new UploadCertificate();

//			uploadCertificate.setZipFile(downloadFile.getBytes());
			uploadCertificate.setFileName(downloadFile.getOriginalFilename());
			uploadCertificate.setTAN(lessonMap.get("TAN"));
			uploadCertificate.setFy(lessonMap.get("fy"));
			uploadCertificate.setQuarter(lessonMap.get("quarter"));
			uploadCertificate.setForm(lessonMap.get("form"));

			Date date = new Date();
			uploadCertificate.setUploadedTime(date);
			uploadCertificate.setUserName(getPrincipal());
			uploadCertificate.setStatus("pending");
			dao.persist(uploadCertificate);

			CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
				try {
					System.out.println("completable thread : " + Thread.currentThread().getName());
					String[] t = lessonMap.get("TAN").split("-");
					String[] f = lessonMap.get("form").split("-");
					String path = "download\\" + lessonMap.get("fy") + "\\" + lessonMap.get("quarter") + "\\" + f[0]
							+ "\\" + t[0];
					logger.info(path);
					System.out.println(path);
					unzip(downloadFile, path);
					uploadCertificate.setStatus("success");
					dao.update(uploadCertificate);
				} catch (Exception e) {

					System.out.println("completable future error :");
					uploadCertificate.setStatus("failed");
					dao.update(uploadCertificate);
					e.printStackTrace();
					throw new CustomException(e.getMessage());

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

	}

	public String createUserExcel(LinkedHashMap<String, Object> map) {
		List<UploadCertificate> listUsers = searchExcel(map);

		if (path == null || path.isEmpty()) {
			setPath();
		}

		uploadCertificateExcel = new UploadCertificateExcel();

		File file = new File(path + "static/report/");
		if (!file.exists()) {
			file.mkdirs();
		}

		String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		uploadCertificateExcel.initialise(path + "static/report/TDS-" + timestamp + "-UploadCertificate.xlsx");
		this.ExcelFile = file.getPath() + "/TDS-" + timestamp + "-UploadCertificate.xlsx";

		int row = 1;
		int part = 1;

		Workbook wb = uploadCertificateExcel.getWorkbook();
		Sheet uploadCertificatesheet = wb.getSheet("uploadCertificate-" + part);
		for (UploadCertificate uploadCertificate : listUsers) {

			Row details = uploadCertificatesheet.createRow(row);
			details.createCell(0).setCellValue(row);

			if (uploadCertificate.getFileName() == null) {
				details.createCell(1).setCellValue(" ");
			} else {
				details.createCell(1).setCellValue(uploadCertificate.getFileName());
			}
			if (uploadCertificate.getUserName() == null) {
				details.createCell(2).setCellValue(" ");
			} else {
				details.createCell(2).setCellValue(uploadCertificate.getUserName());
			}
			if (uploadCertificate.getTAN() == null) {
				details.createCell(3).setCellValue(" ");
			} else {
				details.createCell(3).setCellValue(uploadCertificate.getTAN());
			}
			if (uploadCertificate.getFy() == null) {
				details.createCell(4).setCellValue(" ");
			} else {
				details.createCell(4).setCellValue(uploadCertificate.getFy());
			}
			if (uploadCertificate.getQuarter() == null) {
				details.createCell(5).setCellValue(" ");
			} else {
				details.createCell(5).setCellValue(uploadCertificate.getQuarter());
			}
			if (uploadCertificate.getForm() == null) {
				details.createCell(6).setCellValue(" ");
			} else {
				details.createCell(6).setCellValue(uploadCertificate.getForm());
			}
			if (uploadCertificate.getUploadedTime() == null) {
				details.createCell(7).setCellValue(" ");
			} else {
				details.createCell(7)
						.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(uploadCertificate.getUploadedTime()));
			}
			if (uploadCertificate.getStatus() == null) {
				details.createCell(8).setCellValue(" ");
			} else {
				details.createCell(8).setCellValue(uploadCertificate.getStatus());
			}

			if (row > 1000000) {
				part++;
				wb = uploadCertificateExcel.getWorkbook();
				uploadCertificate = (UploadCertificate) uploadCertificateExcel
						.initializeSheet("UploadCertificate-" + part);
				row = 0;
			}
			row++;

		}
		uploadCertificateExcel.close();
		return ExcelFile;

	}

	public void setPath() {
		File myClass = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());

		try {
			path = myClass.getCanonicalPath().split("WEB-INF")[0];
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void unzip(MultipartFile zip, String path) throws Exception {
		final Path destDir = Paths.get(StaticData.CertificatePath + path);
		final Path newPath = destDir.resolve(zip.getOriginalFilename());
		zip.transferTo(newPath);
		logger.info("zip transferred in " + newPath);
//		File file = new File(newPath.toString());
//		FileOutputStream fos = new FileOutputStream(file);
//		fos.write(zip.getBytes());
//		fos.close();
		System.out.println(newPath);
		String command;
		if (StaticData.command.contains("powershell.exe")) {
			String[] c = StaticData.command.split(",");
			command = c[0] + newPath + c[1] + destDir;
			// powershell.exe Expand-Archive
			// D:\download\2022-23\Q1\Form16\ABCD12345F\AAAAU9895N_Q2_2023-24.zip,
			// -DestinationPath D:\download\2022-23\Q1\Form16\ABCD12345F
			logger.info(command);
			System.out.println(command);
		} else {
			if (StaticData.command.contains(",")) {
				String[] c = StaticData.command.split(",");
				command = c[0] + zip.getOriginalFilename() + c[1];
				// "C:\Program Files\7-Zip\7z.exe" AAAAU9895N_Q2_2023-24.zip -o./ -y -r
				logger.info(zip.getOriginalFilename());
				logger.info(command);
				System.out.println(command);
			} else {
				command = StaticData.command + zip.getOriginalFilename();
				// tar -xf AAAAU9895N_Q2_2023-24.zip
				logger.info(command);
				System.out.println(command);
			}
		}
		try {
			logger.info(command);
			Process process;
			if (StaticData.command.contains("powershell.exe")) {
				process = Runtime.getRuntime().exec(command);
				process.waitFor();
			} else {
				process = Runtime.getRuntime().exec("cmd /c start " + command, null, new File(destDir.toString()));
				process.waitFor();

			}
			logger.info("unzip in " + destDir);
			
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			ArrayList<String> procs = new ArrayList<String>();
			String line = null;
			while ((line = input.readLine()) != null) {
			    procs.add(line);
			}
			input.close();
			Boolean processFound = procs.stream().filter(row -> row.indexOf("cmd.exe") > -1).count() > 0;
			while(processFound) {
				Thread.sleep(1000);	
			}
//			taskkill \m \im 

			moveToFolder(destDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CustomException("Error while Uploading file :" + e.getMessage());
		} finally {
			String fName = newPath.getFileName().toString();
			Character c = fName.charAt(4);
			Path targetPath = Paths.get(destDir.toString() + "\\" + c +"\\"+fName);
			targetPath.toFile().delete();
		}
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

	public void moveToFolder(Path destDir) {
		File file = new File(destDir.toString());
		File newFile = null;
		if (file.listFiles() != null) {
			File[] listOfFiles = file.listFiles();
			for (File f : listOfFiles) {
				String fName = f.getName();
				Character c = fName.charAt(4);
				newFile = new File(destDir.toString() + "\\" + c);
				if (!newFile.exists()) {
					newFile.mkdirs();
				}
				try {
					Path target = Paths.get(newFile.toString() + "\\" + f.getName());
					Files.move(f.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public static void main(String[] args) {
		Path destDir = Paths.get("D:\\download\\2022-23\\Q1\\Form16A\\ABCD12345F");
	}
}
