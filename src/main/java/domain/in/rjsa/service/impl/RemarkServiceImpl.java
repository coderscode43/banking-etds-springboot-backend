package domain.in.rjsa.service.impl;


import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.dao.RemarkDao;
import domain.in.rjsa.model.fy.Remark;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.RemarkService;

@Transactional("transactionManager")
@Service("remarkService")
public class RemarkServiceImpl extends AbstractServiceFY<Long, Remark,RemarkDao> implements RemarkService{
	
	@Autowired
	RemarkDao dao;
	@Override
	public RemarkDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public void saveRemark(LinkedHashMap<String, Object> entity){
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(entity);
		getPrimaryDao().persist(gson.fromJson(jsonElement, getEntity()));
	}
	
	public Class<Remark> getEntity() {
		// TODO Auto-generated method stub
		return Remark.class;
	}
	
}
