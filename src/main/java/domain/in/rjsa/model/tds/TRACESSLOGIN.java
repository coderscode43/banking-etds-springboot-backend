package domain.in.rjsa.model.tds;

import java.text.ParseException;

import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class TRACESSLOGIN extends CommonModelAbstract{
	public String TAN;
	public String USERNAME;
	public String PASSWORD;
	
	
    public void setData(JSONObject dataObject) throws ParseException{
        this.TAN = (String) dataObject.get("TAN");
        this.USERNAME = (String) dataObject.get("USERNAME");
        this.PASSWORD = (String) dataObject.get("PASSWORD");
    }
}
