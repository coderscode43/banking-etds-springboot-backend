package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.Regular27EQDeductee;

public interface Regular27EQDeducteeDao extends DaoInterfaceFY<Long,  Regular27EQDeductee> {
	List<Regular27EQDeductee> search(HashMap entity, int pageNo, int noOfResult);

}

