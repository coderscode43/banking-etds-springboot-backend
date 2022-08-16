package domain.in.rjsa.dao;

import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.fy.PanUpdateList;

public interface PanUpdateListDao extends DaoInterfaceFY<Long,   PanUpdateList>{
	List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage);

}
