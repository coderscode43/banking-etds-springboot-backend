package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.MisReport;

public interface MisReportService extends ServiceInterfaceForm<Long, MisReport> {
	public MisReport getByKey(Long id);
}
