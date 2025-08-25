package domain.in.rjsa.service;

import java.util.LinkedHashMap;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.RegularReturnRemark;

public interface RegularReturnRemarkService extends ServiceInterfaceForm<Long, RegularReturnRemark> {

	public void saveWithFile(MultipartFile blob, LinkedHashMap<String, Object> map, String principal);

	public void downloadDocument(Long id, HttpServletResponse response);

	public void save( LinkedHashMap<String, Object> map, String principal);

	public void saveBulkRemark(LinkedHashMap<String, Object> entity, String principal);

	public Branch getBranch(long parseLong);

	public void saveRegularReturnRO(List<MultipartFile> listdocs,
			MultipartFile tdsfileblob, /* MultipartFile reportfile, */
			String entity, String userName);

}
