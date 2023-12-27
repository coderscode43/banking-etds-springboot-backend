package domain.in.rjsa.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.CorrectionRequestAmountDetailsDao;
import domain.in.rjsa.model.form.CorrectionRequestAmountDetails;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.CorrectionRequestAmountDetailsService;

@Transactional("transactionManager")
@Service("correctionRequestAmountDetailsService")
public class CorrectionRequestAmpuntDetailsServiceImpl extends AbstractServiceForm<Long, CorrectionRequestAmountDetails, CorrectionRequestAmountDetailsDao> implements CorrectionRequestAmountDetailsService{

	
	@Autowired
	CorrectionRequestAmountDetailsDao dao;
	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CorrectionRequestAmountDetailsDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRemark(LinkedHashMap<String, Object> entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResolve(CorrectionRequestAmountDetails remark, String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CorrectionRequestAmountDetails> findForm(HashMap<String, Object> constrains) {
		// TODO Auto-generated method stub
		return dao.findForm(constrains);
	}

}
