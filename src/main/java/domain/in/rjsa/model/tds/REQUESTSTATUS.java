package domain.in.rjsa.model.tds;

import java.text.ParseException;

import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;
@Data
public class REQUESTSTATUS extends CommonModelAbstract{

	public Long ID;
	public String TAN;
	public String FY;
	public String QUARTER;	
	public String FORM;
	public String RETURNTYPE;
	public String REQUESTDATE;
	public String REQUEST_NUMBER;
	public String STATUS;
	public String REQUEST_TYPE;
	
	
	public void setData(JSONObject dataObject) throws ParseException{
        this.TAN = (String) dataObject.get("TAN");
        this.FY = (String) dataObject.get("FY");
        this.QUARTER = (String) dataObject.get("QUARTER");
        this.FORM = (String) dataObject.get("FORM");
        this.RETURNTYPE = (String) dataObject.get("RETURNTYPE");
        this.REQUESTDATE = (String) dataObject.get("REQUESTDATE");
        this.REQUEST_NUMBER = (String) dataObject.get("REQUEST_NUMBER");
        this.STATUS = (String) dataObject.get("STATUS");
        this.REQUEST_TYPE = (String) dataObject.get("REQUEST_TYPE");
    }
}
