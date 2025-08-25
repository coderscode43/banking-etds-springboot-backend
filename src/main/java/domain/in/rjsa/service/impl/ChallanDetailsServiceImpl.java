package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.ChallanDetailsDao;
import domain.in.rjsa.dao.LogsDao;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.fy.ChallanDetails;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.ChallanDetailsService;

@Transactional("transactionManager")
@Service("challanDetailsService")
public class ChallanDetailsServiceImpl extends AbstractServiceFY<Long, ChallanDetails, ChallanDetailsDao>
		implements ChallanDetailsService {

	@Autowired
	ChallanDetailsDao dao;

	@Autowired
	LogsDao logsDao;

	@Override
	public List<ChallanDetails> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChallanDetailsDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public void addData(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<JSONObject> jsonObject =  mapper.readValue(json, new TypeReference <List<JSONObject>>() {
			});
			
			for (JSONObject object : jsonObject) {
				ChallanDetails challanDetails = new ChallanDetails();
				challanDetails.setData(object);
				dao.persist(challanDetails);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}
	}

}
