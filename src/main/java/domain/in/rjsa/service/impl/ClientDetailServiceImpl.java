package domain.in.rjsa.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.ClientDetailDao;
import domain.in.rjsa.model.form.ClientDetail;
import domain.in.rjsa.model.fy.FileDetail;
import domain.in.rjsa.service.ClientDetailService;
@Transactional("transactionManager")
@Service("clientDetailService")
public class ClientDetailServiceImpl implements ClientDetailService{
	@Autowired
	ClientDetailDao dao;
	public ClientDetailDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public ClientDetail getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
	@Override
	public void save(ClientDetail entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(ClientDetail entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteT(String key) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<ClientDetail> findAll(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Long findallCount(HashMap<String, Object> constrains) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ClientDetail> search(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ClientDetail uniqueSearch(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> ajax(String name, String term, HashMap<String, Object> constrains) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void saveFile(FileDetail file, LinkedHashMap<String, Object> map, Class<ClientDetail> entity) {
		// TODO Auto-generated method stub
		
	}

}
