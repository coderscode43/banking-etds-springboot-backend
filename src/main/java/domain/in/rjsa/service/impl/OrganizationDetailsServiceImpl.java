package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.OrganizationDetailsDao;
import domain.in.rjsa.model.form.OrganizationDetails;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.OrganizationDetailsService;

@Transactional("transactionManager")
@Service("organizationDetailsService")
public class OrganizationDetailsServiceImpl extends AbstractServiceForm<Long, OrganizationDetails, OrganizationDetailsDao> 
implements OrganizationDetailsService {

	@Autowired
	OrganizationDetailsDao dao;

	@Override
	public OrganizationDetails getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public OrganizationDetailsDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	

}
