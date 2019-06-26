package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Pensioner;
import domain.in.rjsa.model.form.Vendor;

public interface PensionerService extends ServiceInterface<Long,Pensioner>{

	
	
	Pensioner getByKey(Long id);

}
