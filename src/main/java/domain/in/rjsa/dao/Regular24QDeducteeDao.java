package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.Regular24QDeductee;

public interface Regular24QDeducteeDao extends DaoInterfaceFY<Long,  Regular24QDeductee> {
	public List<Regular24QDeductee> searchExcel(HashMap map);
}
