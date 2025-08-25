package domain.in.rjsa.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.CLIENTDETAILSDao;
import domain.in.rjsa.dao.LogsDao;
import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.model.tds.CLIENTDETAILS;
import domain.in.rjsa.model.tds.GH15RETURNSTATUS;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.CLIENTDETAILSService;

@Transactional("transactionManager")
@Service("CLIENTDETAILSService")
public class CLIENTDETAILSServiceImpl extends AbstractServiceTaxo<String, CLIENTDETAILS, CLIENTDETAILSDao>
		implements CLIENTDETAILSService {
	@Autowired
	CLIENTDETAILSDao dao;

	@Autowired
	LogsDao logsDao;

	@Override
	public CLIENTDETAILSDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(JSONObject dataObject) {
		StringBuilder sb = new StringBuilder();
		int successCounter = 0;

		try {
			CLIENTDETAILS clientDetails = new CLIENTDETAILS();
			clientDetails.setdata(dataObject);
			dao.persist(clientDetails);

			successCounter++;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}

		if (sb.length() == 0) {
			Logs logs = new Logs();
			logs.setEntity("CLIENTDETAILS");
			logs.setAction("Add");
			logs.setDetails("Data added in CLIENTDETAILS");
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
			List<JSONObject> jsonObject =  mapper.readValue(json, new TypeReference <List<JSONObject>>() {
			});
			
			for (JSONObject object : jsonObject) {
				CLIENTDETAILS clientDetails = new CLIENTDETAILS();
				clientDetails.setdata(object);
				dao.persist(clientDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Could Not Persist Data");
		}
	}

}
