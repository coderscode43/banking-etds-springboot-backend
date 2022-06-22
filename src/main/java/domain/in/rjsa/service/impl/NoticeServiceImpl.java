package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.NoticeDao;
import domain.in.rjsa.model.form.Notice;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.NoticeService;

@Transactional
@Service("NoticeService")
public class NoticeServiceImpl extends AbstractServiceForm<Long, Notice, NoticeDao> implements NoticeService{

	@Autowired
	NoticeDao dao;
	
	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoticeDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	

	
}
