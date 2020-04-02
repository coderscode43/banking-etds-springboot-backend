package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Form15GHDao;
import domain.in.rjsa.model.form.Form15GH;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Form15GHService;

@Transactional
@Service("form15GHService")
public class Form15GHServiceImpl extends AbstractService<Long, Form15GH, Form15GHDao> implements Form15GHService {

	@Autowired
	Form15GHDao dao;

	@Override
	public Form15GHDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}


}
