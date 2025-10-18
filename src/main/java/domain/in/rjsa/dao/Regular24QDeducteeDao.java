package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.dto.TDSAmountDto;
import domain.in.rjsa.model.fy.Regular24QDeductee;

public interface Regular24QDeducteeDao extends DaoInterfaceFY<Long, Regular24QDeductee> {

	public List<Regular24QDeductee> searchExcel(HashMap map);

	List<Regular24QDeductee> search(HashMap entity, int pageNo, int noOfResult);

	public List<String> getPanList(String s, String fy, Long branchCode);

	public List<TDSAmountDto> getAmountDetails(String quarter);

	public List<Regular24QDeductee> getDetails(HashMap<String, Object> data);
}
