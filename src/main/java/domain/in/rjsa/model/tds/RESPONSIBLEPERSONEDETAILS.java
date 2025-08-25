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
//@Table(name = "Taxo.RESPONSIBLEPERSONEDETAILS")
public class RESPONSIBLEPERSONEDETAILS extends CommonModelAbstract {
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "TAN")
	public String TAN;
	//@Column(name = "NAMERESPONSIBLEPERSONE")
	public String NAMERESPONSIBLEPERSONE;
	//@Column(name = "PANRESPONSIBLEPERSONE")
	public String PANRESPONSIBLEPERSONE;
	//@Column(name = "FATHERNAMERESPONSIBLEPERSONE")
	public String FATHERNAMERESPONSIBLEPERSONE;
	//@Column(name = "DESIGNATION")
	public String DESIGNATION;
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
	//@Column(name = "RESPONSIBLEPERSONEMOBILE")
	public String RESPONSIBLEPERSONEMOBILE;
	//@Column(name = "EMAIL")
	public String EMAIL;
	//@Column(name = "EMAILALTERNATE")
	public String EMAILALTERNATE;
	//@Column(name = "STDALTERNATE")
	public String STDALTERNATE;
	//@Column(name = "TELEPHONEALTERNATE")
	public String TELEPHONEALTERNATE;
	//@Column(name = "CHANGEADDRESSSINCELASTRETURN")
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
