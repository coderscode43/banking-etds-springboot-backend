package domain.in.rjsa.service;

import org.json.simple.JSONObject;

import domain.in.rjsa.model.tds.CLIENTDETAILS;

public interface CLIENTDETAILSService extends ServiceInterfaceTaxo<String, CLIENTDETAILS>{

	String save(JSONObject jsonObject);
	 
}