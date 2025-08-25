package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.SupportingDocumentsDao;
import domain.in.rjsa.model.form.SupportingDocuments;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.SupportingDocumentsService;

@Transactional("transactionManager")
@Service("supportingDocumentsService")
public class SupportingDocumentsServiceImpl extends AbstractServiceForm<Long, SupportingDocuments, SupportingDocumentsDao> implements SupportingDocumentsService{

	
	@Autowired
	SupportingDocumentsDao dao;
	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SupportingDocumentsDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

}
