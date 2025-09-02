package domain.in.rjsa.service;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.model.form.Remark;
import jakarta.validation.Valid;

public interface RemarkServiceNew extends ServiceInterfaceForm<Long,Remark>{
		
	Remark searchForm(Long trackId);

	public List<Remark> findTransaction(HashMap<String, Object> constrains, int pageNo, int noOfResult);

	void addRemarkWithDocument(MultipartFile downloadFile, String principal, LinkedHashMap<String, String> lessonMap) throws IOException;

	void addRemarkWithoutDocument(String principal, @Valid LinkedHashMap<String, String> entity);

	void updateRemarkWithFile(MultipartFile downloadFile, String principal, LinkedHashMap<String, String> lessonMap) throws IOException;

	void updateRemarkWithoutDocument(String principal, @Valid LinkedHashMap<String, String> entity);
}
