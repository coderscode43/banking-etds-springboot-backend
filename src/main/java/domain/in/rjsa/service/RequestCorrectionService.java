package domain.in.rjsa.service;

import domain.in.rjsa.model.form.RequestCorrection;

public interface RequestCorrectionService extends ServiceInterfaceForm<Long,RequestCorrection>{
	RequestCorrection getByKey(Long id); 
}
