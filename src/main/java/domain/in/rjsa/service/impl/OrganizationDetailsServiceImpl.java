package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

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

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
