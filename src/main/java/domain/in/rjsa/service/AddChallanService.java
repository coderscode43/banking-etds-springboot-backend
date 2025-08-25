package domain.in.rjsa.service;

import jakarta.servlet.http.HttpServletResponse;

import domain.in.rjsa.model.form.AddChallan;

public interface AddChallanService extends ServiceInterfaceForm<Long, AddChallan>{

	void downloadDocument(Long id, HttpServletResponse response);

	AddChallan getByCorrectionId(Long correctionRequestId);

}
