package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.REQUESTSTATUSDao;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.tds.REQUESTSTATUS;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.REQUESTSTATUSService;

@Transactional("tdsTxManager")
@Service("REQUESTSTATUSService")
public class REQUESTSTATUSServiceImpl extends AbstractServiceTaxo<String, REQUESTSTATUS, REQUESTSTATUSDao>
		implements REQUESTSTATUSService {
	
	REQUESTSTATUSDao dao;

	@Override
	public void save(REQUESTSTATUS entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteT(Long key) {
		// TODO Auto-generated method stub

	}

	@Override
	public REQUESTSTATUS getByKey(Long key) {
		// TODO Auto-generated method stub
		return null;
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
	public REQUESTSTATUSDao getPrimaryDao() {
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
				REQUESTSTATUS reqStatus = new REQUESTSTATUS();
				reqStatus.setData(object);
				dao.persist(reqStatus);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}
	}
	
}
