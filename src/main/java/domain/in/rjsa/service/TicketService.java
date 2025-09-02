package domain.in.rjsa.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import domain.in.rjsa.model.fy.Tickets;
import jakarta.servlet.http.HttpServletResponse;

public interface TicketService extends ServiceInterfaceForm<Long, Tickets>{
	
	public Tickets getByKey(Long id);

	List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage);

	public Map<String, Long> getStatusDetails(Long branchCode, boolean isAdmin);

	public void addTicketWithFile(MultipartFile file, LinkedHashMap<String, Object> entity);

	public void downloadDocument(Long id, HttpServletResponse response);
}
