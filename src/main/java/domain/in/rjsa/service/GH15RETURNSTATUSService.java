package domain.in.rjsa.service;

import org.json.simple.JSONObject;

import domain.in.rjsa.model.tds.GH15RETURNSTATUS;

public interface GH15RETURNSTATUSService extends ServiceInterfaceTaxo<Long, GH15RETURNSTATUS> {

	String save(JSONObject jsonObject);

}
