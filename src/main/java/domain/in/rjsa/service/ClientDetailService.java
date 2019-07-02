package domain.in.rjsa.service;

import domain.in.rjsa.model.form.ClientDetail;

public interface ClientDetailService extends ServiceTDSInterface<String, ClientDetail>{
	

public ClientDetail getByKey(Long id);

}
