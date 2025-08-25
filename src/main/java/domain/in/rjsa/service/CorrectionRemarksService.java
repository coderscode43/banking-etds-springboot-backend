package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.model.form.CorrectionRemarks;

public interface CorrectionRemarksService extends ServiceInterfaceForm<Long, CorrectionRemarks> {

	
	public List<CorrectionRemarks> findForm(HashMap<String, Object> constrains);

	public void SaveRemarkWithDocument(MultipartFile downloadFile, Long branchCode, Long crId, String remark,String pricipal, String status,String fy, String quarter);

	public void downloadDocument(Long id, HttpServletResponse response);

	public void saveRemark(CorrectionRemarks entity,String principal);


}
