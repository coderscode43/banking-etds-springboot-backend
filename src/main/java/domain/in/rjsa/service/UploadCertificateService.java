package domain.in.rjsa.service;



import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.model.fy.UploadCertificate;

public interface UploadCertificateService extends ServiceInterfaceForm<Long, UploadCertificate>{
	public UploadCertificate getByKey(Long id);
	
	void saveDocument(MultipartFile downloadFile, HashMap<String, String> lessonMap);
	
}
