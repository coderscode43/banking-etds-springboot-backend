package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.Regular24QDeductee;

public interface Regular24QDeducteeService extends ServiceInterfaceFY<Long, Regular24QDeductee> {
	public Regular24QDeductee getByKey(Long id);

	public List<String> getPanList(String s, String fy, Long branchCode);

	public Long findallCount(HashMap<String, Object> constrains);

	public byte[] getAmountDetailsAsExcel(String quarter);

	public void mapChallan(HashMap<String, Object> data);

//	 public void updateAllowed(LinkedHashMap<String, Object> entity);
}
