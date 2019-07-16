package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.FileDetailDao;
import domain.in.rjsa.model.form.FileDetail;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.FileDetailService;
@Transactional("transactionManager")
@Service("fileDetailService")
public class FileDetailServiceImpl extends AbstractService<Long, FileDetail, FileDetailDao> implements FileDetailService{
@Autowired
FileDetailDao dao;
	@Override
	public FileDetailDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
