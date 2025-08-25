package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.fy.Regular26QDeductee;

public interface Regular26QDeducteeDao extends DaoInterfaceFY<Long,Regular26QDeductee> {

	Long findSearchCount(LinkedHashMap<String, Object> entity);

	List<Regular26QDeductee> search(HashMap entity, int pageNo, int noOfResult);
	
	public Long findallCount(HashMap<String,Object> constrains);
	
	public List<Regular26QDeductee> searchExcel(HashMap map);
	
	public List<String> getPanList(String s, String fy, Long branchCode);


}
