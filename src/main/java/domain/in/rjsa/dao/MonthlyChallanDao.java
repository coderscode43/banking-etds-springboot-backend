package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.MonthlyChallan;

public interface MonthlyChallanDao extends DaoInterfaceFY<Long, MonthlyChallan > {
	//public MonthlyChallan getByKey(Long key) ;
	List<MonthlyChallan> search(HashMap entity, int pageNo, int noOfResult);
}
