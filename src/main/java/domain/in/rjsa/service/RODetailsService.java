package domain.in.rjsa.service;

import domain.in.rjsa.model.form.RODetails;

public interface RODetailsService extends ServiceInterfaceForm<Long,RODetails>{
	RODetails getByKey(Long id);
}
