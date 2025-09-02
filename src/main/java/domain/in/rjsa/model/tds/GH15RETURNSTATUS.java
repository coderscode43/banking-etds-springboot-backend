package domain.in.rjsa.model.tds;

import java.text.ParseException;

import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class GH15RETURNSTATUS extends CommonModelAbstract {
	public Long id;
	public Long clientId;
	public String TAN;
	public String QUARTER;
	public String FORM;
	public String RT;
	public String PRN;
	public String STATUS;
	public String AS_ON_DATE;
	
	public void setdata(JSONObject dataObject) throws ParseException{
		this.TAN = dataObject.get("TAN").toString();
		this.QUARTER = dataObject.get("QUARTER").toString();
		this.FORM = dataObject.get("FORM").toString();
		this.RT = dataObject.get("RT").toString();
		this.PRN = dataObject.get("PRN").toString();
		this.STATUS = dataObject.get("STATUS").toString();
		this.AS_ON_DATE = dataObject.get("AS_ON_DATE").toString();
	}

}
