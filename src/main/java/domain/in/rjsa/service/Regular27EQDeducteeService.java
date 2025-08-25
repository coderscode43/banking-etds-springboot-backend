package domain.in.rjsa.service;


import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.Regular27EQDeductee;

public interface Regular27EQDeducteeService extends ServiceInterfaceFY<Long,Regular27EQDeductee> {
	
	public Regular27EQDeductee getByKey(Long id);

	public void updateAllowed(Regular27EQDeductee entity);
	
	public Long findallCount(HashMap<String, Object> constrains);

	public List<String> getPanList(String q, String fy, Long branchCode);

}
