package domain.in.rjsa.service;

import java.util.LinkedHashMap;

import domain.in.rjsa.model.fy.DeducteeRemark;

public interface DeducteeRemarkService extends ServiceInterfaceForm<Long, DeducteeRemark> {

	public void save(LinkedHashMap<String, Object> entity);
}
