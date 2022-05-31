package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.Remark;

public interface RemarkDao extends DaoInterfaceFY<Long, Remark>{
	List<Remark> search(HashMap entity, int pageNo, int noOfResult);
}
