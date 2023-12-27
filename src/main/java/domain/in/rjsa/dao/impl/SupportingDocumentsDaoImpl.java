package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.SupportingDocumentsDao;
import domain.in.rjsa.model.form.SupportingDocuments;

@Repository("supportingDocumentsDao")
public class SupportingDocumentsDaoImpl extends AbstractDaoForm<Long, SupportingDocuments> implements SupportingDocumentsDao{

	@Override
	public List<SupportingDocuments> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}


	
}

	

