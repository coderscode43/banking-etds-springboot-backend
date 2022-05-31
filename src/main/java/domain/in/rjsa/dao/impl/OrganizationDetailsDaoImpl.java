package domain.in.rjsa.dao.impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.OrganizationDetailsDao;
import domain.in.rjsa.model.form.OrganizationDetails;
@Repository("OrganizationDetailsDao")
public class OrganizationDetailsDaoImpl extends AbstractDaoForm<Long, OrganizationDetails> implements OrganizationDetailsDao {

	@Override
	public List<OrganizationDetails> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
	

	
