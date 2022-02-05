package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.Remarks;

public interface RemarksService extends ServiceInterfaceFY<Long, Remarks>{
	public Remarks getByKey(Long id);
}
