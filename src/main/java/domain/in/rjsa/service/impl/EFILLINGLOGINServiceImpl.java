package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.EFILLINGLOGINDao;
import domain.in.rjsa.model.tds.EFILLINGLOGIN;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.EFILLINGLOGINService;

@Transactional("tdsTxManager")
@Service("EFILLINGLOGINService")
public class EFILLINGLOGINServiceImpl extends AbstractServiceTaxo<String, EFILLINGLOGIN, EFILLINGLOGINDao> implements EFILLINGLOGINService{
@Autowired
EFILLINGLOGINDao dao;
	@Override
	public EFILLINGLOGINDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public EFILLINGLOGIN getByKey(String tan) {
		// TODO Auto-generated method stub
		return dao.getByKey(tan);
	}

}
