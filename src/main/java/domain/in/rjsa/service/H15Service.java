package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.H15;

public interface H15Service extends ServiceInterfaceFY<Long, H15> {
	 public H15 getByKey(Long id);

	public List<?> findAll(HashMap<String, Object> constrains, int pageNo, int resultPerPage);

}
