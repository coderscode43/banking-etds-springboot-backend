package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.DEFAULTSUMMARYDao;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.tds.DEFAULTSUMMARY;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.DEFAULTSUMMARYService;

@Transactional("tdsTxManager")
@Service("DEFAULTSUMMARYService")
public class DEFAULTSUMMARYServiceImpl extends AbstractServiceTaxo<Long, DEFAULTSUMMARY, DEFAULTSUMMARYDao>
		implements DEFAULTSUMMARYService {
	@Autowired
	DEFAULTSUMMARYDao dao;

	@Override
	public DEFAULTSUMMARYDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}

	@Override
	public void addData(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<JSONObject> jsonObject = mapper.readValue(json, new TypeReference<List<JSONObject>>() {
			});

			for (JSONObject object : jsonObject) {
				DEFAULTSUMMARY summary = new DEFAULTSUMMARY();
				summary.setdata(object);
				dao.persist(summary);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}
	}

}
