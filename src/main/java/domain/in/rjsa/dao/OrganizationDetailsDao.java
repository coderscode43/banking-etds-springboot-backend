package domain.in.rjsa.dao;

import domain.in.rjsa.model.form.OrganizationDetails;

public interface OrganizationDetailsDao extends DaoInterfaceForm<Long , OrganizationDetails> {
	public OrganizationDetails getByKey(Long key) ;
}
