package domain.in.rjsa.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.ADDITIONALDETAILSDao;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.tds.ADDITIONALDETAILS;
import domain.in.rjsa.service.ADDITIONALDETAILSService;
import domain.in.rjsa.service.AbstractServiceTaxo;

@Transactional("transactionManager")
@Service("ADDITIONALDETAILSService")

public class ADDITIONALDETAILSServiceImpl extends AbstractServiceTaxo<String, ADDITIONALDETAILS, ADDITIONALDETAILSDao>
		implements ADDITIONALDETAILSService {

	ADDITIONALDETAILSDao dao;

	@Override
	public void save(ADDITIONALDETAILS entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteT(Long key) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ADDITIONALDETAILS> findAll(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long findallCount(HashMap<String, Object> constrains) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ADDITIONALDETAILS uniqueSearch(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> ajax(String name, String term) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ADDITIONALDETAILS getByKey(Long key) {
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
	public ADDITIONALDETAILSDao getPrimaryDao() {
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
				ADDITIONALDETAILS AD = new ADDITIONALDETAILS();
				AD.setdata(object);
				dao.persist(AD);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}
	}

}
