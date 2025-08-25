package domain.in.rjsa.model.tds;

import java.text.ParseException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
//@Entity
//@Table(name = "Taxo.DEDUCTORDETAILS")
public class DEDUCTORDETAILS extends CommonModelAbstract {

	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;
	//@Column(name = "clientId")
	public Long clientId;
	//@Column(name = "TAN")
	public String TAN;
	//@Column(name = "FLATORFLOAR")
	public String FLATORFLOAR;
	//@Column(name = "BUILDINGNAME")
	public String BUILDINGNAME;
	//@Column(name = "ROADSTREET")
	public String ROADSTREET;
	//@Column(name = "AREALOCALITY")
	public String AREALOCALITY;
	//@Column(name = "CITY")
	public String CITY;
	//@Column(name = "STATE")
	public String STATE;
	//@Column(name = "PIN")
	public String PIN;
	//@Column(name = "STD")
	public String STD;
	//@Column(name = "TELEPHONE")
	public String TELEPHONE;
	//@Column(name = "COMPANYEMAIL")
	public String COMPANYEMAIL;
	//@Column(name = "STD_ALTERNATE")
	public String STD_ALTERNATE;
	//@Column(name = "TELEPHONEALTERNATE")
	public String TELEPHONEALTERNATE;
	//@Column(name = "COMPANYEMAILALTERNATE")
	public String COMPANYEMAILALTERNATE;
	//@Column(name = "CHANGEADDRESSSINCELASTRETURN")
	public String CHANGEADDRESSSINCELASTRETURN;
	//@Column(name = "GSTIN")
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
