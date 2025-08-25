package domain.in.rjsa.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.GH15RETURNSTATUSDao;
import domain.in.rjsa.dao.LogsDao;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.model.tds.GH15RETURNSTATUS;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.GH15RETURNSTATUSService;

@Transactional("tdsTxManager")
@Service("GH15RETURNSTATUSService")
public class GH15RETURNSTATUSServiceImpl extends AbstractServiceTaxo<Long, GH15RETURNSTATUS, GH15RETURNSTATUSDao>
		implements GH15RETURNSTATUSService {

	@Autowired
	GH15RETURNSTATUSDao dao;

	@Autowired
	LogsDao logsDao;

	@Override
	public GH15RETURNSTATUSDao getPrimaryDao() {
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
	public String save(JSONObject jsonObject) {
		StringBuilder sb = new StringBuilder();
		int successCounter = 0;

		JSONArray jsonArray = (JSONArray) jsonObject.get("data");

		try {
			for (Object object : jsonArray) {
				JSONObject dataObject = (JSONObject) object;
				GH15RETURNSTATUS gh15ReturnStatus = new GH15RETURNSTATUS();

				gh15ReturnStatus.setdata(dataObject);
				dao.persist(gh15ReturnStatus);

				successCounter++;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}

		if (sb.length() == 0) {
			Logs logs = new Logs();
			logs.setEntity("GH15RETURNSTATUS");
			logs.setAction("Add");
			logs.setDetails("Data added in GH15RETURNSTATUS");
			logs.setLogsDate(new Date());
			logsDao.persist(logs);

			return successCounter + " Data Added Successfully!";
		} else {
			return successCounter + sb.toString();
		}
	}

	@Override
	public void addData(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<JSONObject> jsonObject = mapper.readValue(json, new TypeReference<List<JSONObject>>() {
			});

			for (JSONObject object : jsonObject) {
				GH15RETURNSTATUS gh = new GH15RETURNSTATUS();
				gh.setdata(object);
				dao.persist(gh);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}
	}

}
