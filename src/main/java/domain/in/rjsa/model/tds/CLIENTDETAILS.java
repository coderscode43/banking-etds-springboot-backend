package domain.in.rjsa.model.tds;

import java.text.ParseException;

import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class CLIENTDETAILS extends CommonModelAbstract {

	public String TAN;

	public String NAME;

	public String BRANCH;

	public String PAN;

	public String TYPE;

	public String EMAIL;

	public void setdata(JSONObject dataObject) throws ParseException{
		this.TAN = dataObject.get("TAN").toString();
		this.NAME = dataObject.get("NAME").toString();
		this.BRANCH = dataObject.get("BRANCH").toString();
		this.PAN = dataObject.get("PAN").toString();
		this.TYPE = dataObject.get("TYPE").toString();
		this.EMAIL = dataObject.get("EMAIL").toString();
	}
}
