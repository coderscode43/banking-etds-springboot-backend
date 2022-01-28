package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.AdminDao;
import domain.in.rjsa.model.form.Admin;
import domain.in.rjsa.service.AbstractTDSService;
import domain.in.rjsa.service.AdminService;
@Transactional("transactionManager")
@Service("adminService")
public class AdminServiceImpl extends AbstractTDSService<Long, Admin,AdminDao> implements AdminService {
	@Autowired
	AdminDao dao;

	@Override
	public AdminDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	

}
