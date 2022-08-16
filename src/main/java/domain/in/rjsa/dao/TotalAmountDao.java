package domain.in.rjsa.dao;

import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.fy.TotalAmount;

public interface TotalAmountDao extends DaoInterfaceFY<Long,  TotalAmount>{

	List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage);

}
