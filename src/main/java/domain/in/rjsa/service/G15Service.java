package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.G15;

public interface G15Service extends ServiceInterfaceFY<Long, G15> {
	 public G15 getByKey(Long id);

	public List<?> findAll(HashMap<String, Object> constrains, int pageNo, int resultPerPage);

}
