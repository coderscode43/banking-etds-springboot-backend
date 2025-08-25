package domain.in.rjsa.service;


import java.util.List;

import domain.in.rjsa.model.fy.Regular27QDeductee;

public interface Regular27QDeducteeService extends ServiceInterfaceFY<Long,Regular27QDeductee> {
	 public Regular27QDeductee getByKey(Long id);

	public void updateAllowed(Regular27QDeductee entity);

	public List<String> getPanList(String q, String fy, Long branchCode);
}
