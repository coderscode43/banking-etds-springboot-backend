package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.PANITR2324Dao;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.tds.PANITR2324;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.PANITR2324Service;

@Transactional("transactionManager")
@Service("PANITR2324Service")
public class PANITR2324ServiceImpl extends AbstractServiceTaxo<Long, PANITR2324, PANITR2324Dao>
		implements PANITR2324Service {

	@Autowired
	PANITR2324Dao dao;

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
	public PANITR2324Dao getPrimaryDao() {
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
				PANITR2324 pan = new PANITR2324();
				pan.setData(object);
				dao.persist(pan);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}

	}

}
