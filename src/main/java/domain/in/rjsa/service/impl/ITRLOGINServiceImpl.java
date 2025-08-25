package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.ITRLOGINDao;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.tds.ITRLOGIN;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.ITRLOGINService;

@Transactional("transactionManager")
@Service("ITRLOGINService")
public class ITRLOGINServiceImpl extends AbstractServiceTaxo<Long, ITRLOGIN, ITRLOGINDao> implements ITRLOGINService {

	@Autowired
	ITRLOGINDao dao;
	
	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map);
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITRLOGINDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public void addData(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<JSONObject> jsonObject = mapper.readValue(json, new TypeReference<List<JSONObject>>() {
			});

			for (JSONObject object : jsonObject) {
				ITRLOGIN itr = new ITRLOGIN();
				itr.setdata(object);
				dao.persist(itr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}
	}
}