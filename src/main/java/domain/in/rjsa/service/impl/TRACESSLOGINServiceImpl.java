package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.TRACESSLOGINDao;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.tds.TRACESSLOGIN;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.TRACESSLOGINService;

@Transactional("tdsTxManager")
@Service("TRACESSLOGINService")
public class TRACESSLOGINServiceImpl extends AbstractServiceTaxo<String, TRACESSLOGIN, TRACESSLOGINDao>
		implements TRACESSLOGINService {
	@Autowired
	TRACESSLOGINDao dao;

	@Override
	public TRACESSLOGINDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public TRACESSLOGIN getByKey(String tan) {
		// TODO Auto-generated method stub
		return dao.getByKey(tan);
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addData(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<JSONObject> jsonObject = mapper.readValue(json, new TypeReference<List<JSONObject>>() {
			});

			for (JSONObject object : jsonObject) {
				TRACESSLOGIN traces = new TRACESSLOGIN();
				traces.setData(object);
				dao.persist(traces);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}
	}

}