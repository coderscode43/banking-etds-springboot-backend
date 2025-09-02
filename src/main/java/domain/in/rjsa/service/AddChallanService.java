package domain.in.rjsa.service;

import domain.in.rjsa.model.form.AddChallan;
import jakarta.servlet.http.HttpServletResponse;

public interface AddChallanService extends ServiceInterfaceForm<Long, AddChallan>{

	void downloadDocument(Long id, HttpServletResponse response);

	AddChallan getByCorrectionId(Long correctionRequestId);

}
