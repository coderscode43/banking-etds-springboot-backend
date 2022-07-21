package domain.in.rjsa.service;

import java.util.LinkedHashMap;

import domain.in.rjsa.model.fy.Regular24QDeductee;

public interface Regular24QDeducteeService extends ServiceInterfaceFY<Long,Regular24QDeductee> {
	 public Regular24QDeductee getByKey(Long id);
	 
//	 public void updateAllowed(LinkedHashMap<String, Object> entity);
}
