package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.H15;

public interface H15Dao extends DaoInterfaceFY<Long,   H15>{
	List<H15> search(HashMap entity, int pageNo, int noOfResult);
}
