package domain.in.rjsa.model.tds;

import java.text.ParseException;

import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class DEDUCTORDETAILS extends CommonModelAbstract {

	public Long id;
	public Long clientId;
	public String TAN;
	public String FLATORFLOAR;
	public String BUILDINGNAME;
	public String ROADSTREET;
	public String AREALOCALITY;
	public String CITY;
	public String STATE;
	public String PIN;
	public String STD;
	public String TELEPHONE;
	public String COMPANYEMAIL;
	public String STD_ALTERNATE;
	public String TELEPHONEALTERNATE;
	public String COMPANYEMAILALTERNATE;
	public String CHANGEADDRESSSINCELASTRETURN;
	public String GSTIN;

	public void setdata(JSONObject dataObject) throws ParseException{
		this.clientId = Long.valueOf(dataObject.get("clientId").toString());
		this.TAN = (String) dataObject.get("TAN");
		this.FLATORFLOAR = (String) dataObject.get("FLATORFLOAR");
		this.BUILDINGNAME = (String) dataObject.get("BUILDINGNAME");
		this.ROADSTREET = (String) dataObject.get("ROADSTREET");
		this.AREALOCALITY = (String) dataObject.get("AREALOCALITY");
		this.CITY = (String) dataObject.get("CITY");
		this.STATE = (String) dataObject.get("STATE");
		this.PIN = (String) dataObject.get("PIN");
		this.STD = (String) dataObject.get("STD");
		this.TELEPHONE = (String) dataObject.get("TELEPHONE");
		this.COMPANYEMAIL = (String) dataObject.get("COMPANYEMAIL");
		this.STD_ALTERNATE = (String) dataObject.get("STD_ALTERNATE");
		this.TELEPHONEALTERNATE = (String) dataObject.get("TELEPHONEALTERNATE");
		this.COMPANYEMAILALTERNATE = (String) dataObject.get("COMPANYEMAILALTERNATE");
		this.CHANGEADDRESSSINCELASTRETURN = (String) dataObject.get("CHANGEADDRESSSINCELASTRETURN");
		this.GSTIN = (String) dataObject.get("GSTIN");
	}
}
