package domain.in.rjsa.service;



import java.io.InputStream;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.model.form.UploadCertificate;

public interface UploadCertificateService extends ServiceInterfaceForm<Long, UploadCertificate>{
	public UploadCertificate getByKey(Long id);
	
	void saveDocument(MultipartFile downloadFile, HashMap<String, String> lessonMap);
	
	void uploadCertificate(MultipartFile downloadFile, HashMap<String, String> lessonMap);
	
	void uploadInvoices(String fileName, HashMap<String, String> lessonMap, InputStream inputStream);
}
