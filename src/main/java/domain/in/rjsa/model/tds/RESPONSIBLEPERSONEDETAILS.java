package domain.in.rjsa.model.tds;

import java.text.ParseException;

import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class RESPONSIBLEPERSONEDETAILS extends CommonModelAbstract {
	public String TAN;
	public String NAMERESPONSIBLEPERSONE;
	public String PANRESPONSIBLEPERSONE;
	public String FATHERNAMERESPONSIBLEPERSONE;
	public String DESIGNATION;
	public String FLATORFLOAR;
	public String BUILDINGNAME;
	public String ROADSTREET;
	public String AREALOCALITY;
	public String CITY;
	public String STATE;
	public String PIN;
	public String STD;
	public String TELEPHONE;
	public String RESPONSIBLEPERSONEMOBILE;
	public String EMAIL;
	public String EMAILALTERNATE;
	public String STDALTERNATE;
	public String TELEPHONEALTERNATE;
	public String CHANGEADDRESSSINCELASTRETURN;

	public void setData(JSONObject dataObject) throws ParseException{
		this.TAN = (String) dataObject.get("TAN");
		this.NAMERESPONSIBLEPERSONE = (String) dataObject.get("NAMERESPONSIBLEPERSONE");
		this.PANRESPONSIBLEPERSONE = (String) dataObject.get("PANRESPONSIBLEPERSONE");
		this.FATHERNAMERESPONSIBLEPERSONE = (String) dataObject.get("FATHERNAMERESPONSIBLEPERSONE");
		this.DESIGNATION = (String) dataObject.get("DESIGNATION");
		this.FLATORFLOAR = (String) dataObject.get("FLATORFLOAR");
		this.BUILDINGNAME = (String) dataObject.get("BUILDINGNAME");
		this.ROADSTREET = (String) dataObject.get("ROADSTREET");
		this.AREALOCALITY = (String) dataObject.get("AREALOCALITY");
		this.CITY = (String) dataObject.get("CITY");
		this.STATE = (String) dataObject.get("STATE");
		this.PIN = (String) dataObject.get("PIN");
		this.STD = (String) dataObject.get("STD");
		this.TELEPHONE = (String) dataObject.get("TELEPHONE");
		this.RESPONSIBLEPERSONEMOBILE = (String) dataObject.get("RESPONSIBLEPERSONEMOBILE");
		this.EMAIL = (String) dataObject.get("EMAIL");
		this.EMAILALTERNATE = (String) dataObject.get("EMAILALTERNATE");
		this.STDALTERNATE = (String) dataObject.get("STDALTERNATE");
		this.TELEPHONEALTERNATE = (String) dataObject.get("TELEPHONEALTERNATE");
		this.CHANGEADDRESSSINCELASTRETURN = (String) dataObject.get("CHANGEADDRESSSINCELASTRETURN");
	}
}
