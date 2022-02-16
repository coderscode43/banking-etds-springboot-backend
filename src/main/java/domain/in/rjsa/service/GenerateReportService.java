package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.GenerateReport;

public interface GenerateReportService extends ServiceInterfaceFY<Long,GenerateReport> {
	 public GenerateReport getByKey(Long id);
}
