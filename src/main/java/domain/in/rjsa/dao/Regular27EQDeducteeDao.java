package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.fy.Regular27EQDeductee;

public interface Regular27EQDeducteeDao extends DaoInterfaceFY<Long,  Regular27EQDeductee> {
	
	Long findSearchCount(LinkedHashMap<String, Object> entity);

	List<Regular27EQDeductee> search(HashMap entity, int pageNo, int noOfResult);
	
	public Long findallCount(HashMap<String,Object> constrains);
	
	public List<Regular27EQDeductee> searchExcel(HashMap map);
	
	public List<String> getPanList(String s, String fy, Long branchCode);

}

