package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.Remarks;

public interface RemarkDao extends DaoInterfaceFY<Long, Remarks>{
	List<Remarks> search(HashMap entity, int pageNo, int noOfResult);
}
