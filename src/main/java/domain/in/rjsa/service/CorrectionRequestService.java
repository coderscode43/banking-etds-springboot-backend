package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.model.form.CorrectionRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CorrectionRequestService extends ServiceInterfaceForm<Long, CorrectionRequest> {

	public CorrectionRequest getByKey(Long id);

	List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage);

	public Map<String, Long> getStatusDetails(Long branchCode, boolean isAdmin);

	public void saveAmount(LinkedHashMap<String, Object> entity);

	public Long autoGenerateTicketNumber();

	public List<?> getList(HashMap<String, Object> constrains, int pageNo, int resultPerPage);

	public Object getDetail(HashMap<String, Object> constrains, Long id, String principal, Long branchCode);

//	public void saveFile(MultipartFile blob, LinkedHashMap<String, Object> entity);
	public void saveFile(MultipartFile blob, String entity, Long ticketNumber);

	public void downloadDocument(Long id, HttpServletResponse response);

	public void saveMultiFile(List<MultipartFile> blob, MultipartFile blob2, String entity, Long ticketNumber);

	public void saveCorrection(LinkedHashMap<String, Object> entity, String string);

	public void sendMail(CorrectionRequest cr);

}
