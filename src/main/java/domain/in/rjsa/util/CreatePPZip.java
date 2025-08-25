
package domain.in.rjsa.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class CreatePPZip {
	// This will create password protected zip files

	private static final String EXTENSION = "zip";

	public void pack(File f, String destFolder, String password, String fileName) throws ZipException {
		ZipParameters zipParameters = new ZipParameters();
		zipParameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
		zipParameters.setEncryptFiles(true);
		zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
		// zipParameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
		zipParameters.setPassword(password);
		String destinationZipFilePath = destFolder + "\\" + fileName + "." + EXTENSION;
		ZipFile zipFile = new ZipFile(destinationZipFilePath);
		zipFile.addFile(f, zipParameters);

	}

	public void pack(ArrayList<File> f, String destFolder, String password, String fileName) throws ZipException {
		ZipParameters zipParameters = new ZipParameters();
		zipParameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
		zipParameters.setEncryptFiles(true);
		zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
		// zipParameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
		zipParameters.setPassword(password);
		String destinationZipFilePath = destFolder + "\\" + fileName + "." + EXTENSION;
		ZipFile zipFile = new ZipFile(destinationZipFilePath);
		for(File file : f) {
			zipFile.addFile(file, zipParameters);
		}
		

	}

	public void unpack(String sourceZipFilePath, String extractedZipFilePath) throws ZipException {
		// this is pending
		ZipFile zipFile = new ZipFile(sourceZipFilePath + "." + EXTENSION);

		if (zipFile.isEncrypted()) {
			// zipFile.setPassword(password);
		}

		zipFile.extractAll(extractedZipFilePath);
	}


//	String pdfFilefolder = "D:\\Documents\\Email\\FY 22-23\\Q4\\ONGC\\Form 16A Q3 ONGC";
//	String zipFileFolder = "D:\\Documents\\Email\\FY 22-23\\Q4\\ONGC\\Form 16A Q3 ONGC"
//			+ "\\ZipFiles";
//	String q = "Q3";
//	String fy = "2023-24";
//	int locationPassword = 0;
//	String insideFilename = "form.16A";
//	File file = new File(pdfFilefolder);
//	for (File f : file.listFiles()) {
//		if (f.getName().endsWith("pdf")) {
//			String[] arr = f.getName().split(Pattern.quote("_"), -1);
//			String password = arr[locationPassword].trim().toUpperCase();
//			String fileName = password+"_"+q+"_"+fy;
//			File destFile = new File(zipFileFolder+"\\FORM16A.pdf");
//			
//			try {
//				FileUtils.copyFile(f, destFile);
//				new CreatePPZip().pack(destFile,zipFileFolder, password,fileName);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//		}
//
//	}

	public static void main(String[] args) throws IOException {
		String pdfFilefolder1 = "D:\\Documents\\Email\\FY 22-23\\Q4\\ONGC\\Form 16 Q3";
		String pdfFilefolder2 = "D:\\Documents\\Email\\FY 22-23\\Q4\\ONGC\\Form 16 Q4";
		String zipFileFolder = "D:\\Documents\\Email\\FY 22-23\\Q4\\ONGC\\Form 16A ONGC";
//		String q = "Q3";
		String fy = "2023-24";
		int locationPassword = 0;
		String insideFilename = "form.16A";
		File file1 = new File(pdfFilefolder1);
		for (File f1 : file1.listFiles()) {
			if (f1.getName().endsWith("pdf")) {
				try {
					String[] arr = f1.getName().split(Pattern.quote("_"), -1);
					String pan = arr[locationPassword];
					File destFile = new File(zipFileFolder + "\\" + pan + "\\" + insideFilename + "_Q3.pdf");
					FileUtils.copyFile(f1, destFile);
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		System.out.println("Folder 1 Done!! ");
		File file2 = new File(pdfFilefolder2);
		for (File f2 : file2.listFiles()) {
			if (f2.getName().endsWith("pdf")) {
				try {
					String[] arr = f2.getName().split(Pattern.quote("_"), -1);
					String pan = arr[locationPassword];
					File destFile = new File(zipFileFolder + "\\" + pan + "\\FORM16A_Q4.pdf");
					FileUtils.copyFile(f2, destFile);
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		System.out.println("Folder 2 Done!! ");
		File file3 = new File(zipFileFolder);
		ArrayList<File> filesToSend = new ArrayList<>();
		for (File f3 : file3.listFiles()) {
			filesToSend = getAllFiles(f3);
			String password = f3.getName().trim().toUpperCase();
			String fileName = f3.getName() + "_" + fy;
			try {
				File path = new File(zipFileFolder + "\\ZipFiles");
				if (!path.exists()) {
					path.mkdir();
				}
				new CreatePPZip().pack(filesToSend, path.toString(), password, fileName);
				
			} catch (ZipException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Zipfiles Done!! ");

	}

	private static ArrayList<File> getAllFiles(File destFile) {
		// TODO Auto-generated method stub
		ArrayList<File> filePaths = new ArrayList<File>();
		for (File f : destFile.listFiles()) {
			addFileIfExist(f, filePaths);
		}
		return filePaths;
	}

	private static void addFileIfExist(File filePath, List<File> filePaths) {
		if (filePath.exists()) {
//			System.out.println(filePath);
//			logsger.info(filePath);
			filePaths.add(filePath);
		}
	}
}
