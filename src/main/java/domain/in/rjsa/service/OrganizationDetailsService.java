package domain.in.rjsa.service;

import domain.in.rjsa.model.form.OrganizationDetails;

public interface OrganizationDetailsService extends ServiceInterfaceForm<Long, OrganizationDetails> {
	public OrganizationDetails getByKey(Long id);
}
