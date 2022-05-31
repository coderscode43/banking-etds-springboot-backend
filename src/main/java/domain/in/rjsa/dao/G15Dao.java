package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.G15;

public interface G15Dao extends DaoInterfaceFY<Long,   G15>{
	List<G15> search(HashMap entity, int pageNo, int noOfResult);
}
