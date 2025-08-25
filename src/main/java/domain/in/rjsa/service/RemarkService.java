package domain.in.rjsa.service;

import java.util.LinkedHashMap;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.model.fy.Remarks;

public interface RemarkService extends ServiceInterfaceFY<Long, Remarks>{

	
	public void saveRemark(LinkedHashMap<String, Object> entity);

	public void setResolve(Remarks remarks, String type);

	public void SaveRemarkWithDocument(MultipartFile downloadFile, Long branchCode, String deducteeForm, String remark,
			String principal, Long deducteeId, String fy,String quarter,String form);
	public void downloadDocument(Long id, HttpServletResponse response);
}
