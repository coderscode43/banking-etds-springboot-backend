package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.Regular24QSalary;

public interface Regular24QSalaryDao extends DaoInterfaceFY<Long, Regular24QSalary> {

	List<Regular24QSalary> search(HashMap entity, int pageNo, int noOfResult);
}
