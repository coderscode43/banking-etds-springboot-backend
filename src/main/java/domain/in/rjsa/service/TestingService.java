package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.Testing;

public interface TestingService extends ServiceFYInterface<Long, Testing>{
	public Testing getByKey(Long id);
	
}
