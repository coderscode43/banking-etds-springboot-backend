package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.PensionMasterDao;
import domain.in.rjsa.model.form.PensionMaster;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.PensionMasterService;
@Transactional("transactionManager")
@Service("pensionMasterService")
public class PensionMasterServiceImpl extends AbstractServiceForm<Long, PensionMaster, PensionMasterDao>
implements PensionMasterService{
	@Autowired
	PensionMasterDao dao;
	
	@Override
	public PensionMaster getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public PensionMasterDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
