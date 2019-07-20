package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Pensioner;
import domain.in.rjsa.model.wrapper.PensionerDetailWrapper;

public interface PensionerService extends ServiceInterface<Long,Pensioner>{

	public Pensioner getByKey(Long id);

	public void saveEntity(PensionerDetailWrapper pensioner);

}
