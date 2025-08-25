package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.Regular27QDeductee;

public interface Regular27QDeducteeDao extends DaoInterfaceFY<Long,  Regular27QDeductee> {
	public List<Regular27QDeductee> searchExcel(HashMap map);
	List<Regular27QDeductee> search(HashMap entity, int pageNo, int noOfResult);
	public List<String> getPanList(String s, String fy, Long branchCode);
}
