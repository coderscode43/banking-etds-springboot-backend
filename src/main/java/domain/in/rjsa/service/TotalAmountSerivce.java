package domain.in.rjsa.service;

import java.util.LinkedHashMap;

import domain.in.rjsa.model.fy.TotalAmount;

public interface TotalAmountSerivce extends ServiceInterfaceFY<Long, TotalAmount>{
	
	public String createUserExcel(LinkedHashMap<String, Object> map);

}
