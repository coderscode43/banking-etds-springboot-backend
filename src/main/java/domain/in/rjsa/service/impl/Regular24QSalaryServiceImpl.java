package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.Regular24QSalaryDao;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.fy.Regular24QSalary;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.Regular24QSalaryService;

@Transactional("transactionManager")
@Service("regular24QSalaryService")
public class Regular24QSalaryServiceImpl extends AbstractServiceFY<Long, Regular24QSalary, Regular24QSalaryDao>
		implements Regular24QSalaryService {

	@Autowired
	Regular24QSalaryDao dao;

	@Override
	public Regular24QSalaryDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Regular24QSalary getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
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
			List<JSONObject> jsonObject =  mapper.readValue(json, new TypeReference <List<JSONObject>>() {
			});
			
			for (JSONObject object : jsonObject) {
				Regular24QSalary salary = new Regular24QSalary();
				salary.setData(object);
				dao.persist(salary);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}
	}

}
