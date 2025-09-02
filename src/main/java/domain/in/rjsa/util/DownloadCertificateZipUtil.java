package domain.in.rjsa.util;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadCertificateZipUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(DownloadCertificateZipUtil.class);

    // Method to create a zip file from the list of files
    public static String createZip(List<String> filePaths) {
        try {
            // Generate a temporary zip file
            Path zipFilePath = Files.createTempFile("certificates", ".zip");
            logger.info("Temporary zip file created at: " + zipFilePath.toString());

            try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath.toFile()))) {
                Set<String> addedFileNames = new HashSet<>();
                int filesAdded = 0;

                for (String filePath : filePaths) {
                    Path path = Paths.get(filePath);
                    if (Files.exists(path)) {
                        filesAdded++;

                        // Create the filename for the zip entry, handling duplicates
                        String zipEntryName = path.getFileName().toString();
                        String baseName = zipEntryName;
                        int counter = 1;

                        // If the file with the same name exists, append _1, _2, etc.
                        while (addedFileNames.contains(zipEntryName)) {
                            zipEntryName = baseName.substring(0, baseName.lastIndexOf('.')) + "_" + counter + baseName.substring(baseName.lastIndexOf('.'));
                            counter++;
                        }

                        addedFileNames.add(zipEntryName);

                        // Create a file input stream
                        try (FileInputStream fileInputStream = new FileInputStream(path.toFile())) {
                            // Create a zip entry for the file
                            ZipEntry zipEntry = new ZipEntry(zipEntryName);
                            zipOut.putNextEntry(zipEntry);

                            // Write the file data to the zip output stream
                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = fileInputStream.read(buffer)) > 0) {
                                zipOut.write(buffer, 0, length);
                            }

                            // Close the zip entry
                            zipOut.closeEntry();
                            logger.info("File added to zip: " + zipEntryName);
                        }
                    }
                }

                if (filesAdded == 0) {
                    logger.warn("No files were added to the zip archive.");
                } else {
                    logger.info(filesAdded + " files added to the zip archive.");
                }
            }

            // Now encode the created zip file to Base64 and return the encoded string
            byte[] zipBytes = Files.readAllBytes(zipFilePath);
            logger.info("Encoding zip file to Base64.");
            return Base64.getEncoder().encodeToString(zipBytes);

        } catch (IOException e) {
            logger.error("Error occurred while creating the zip file: ", e);
            return null;
        }
    }
}
