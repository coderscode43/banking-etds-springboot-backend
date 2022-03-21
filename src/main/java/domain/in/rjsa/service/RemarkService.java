package domain.in.rjsa.service;

import java.util.LinkedHashMap;

import domain.in.rjsa.model.fy.Remark;

public interface RemarkService extends ServiceInterfaceFY<Long, Remark>{

	
	public void saveRemark(LinkedHashMap<String, Object> entity);

	public void setResolve(LinkedHashMap<String, Object> entity, String type);


}
