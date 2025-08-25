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
//@Table(name = "Taxo.GH15RETURNSTATUS")
public class GH15RETURNSTATUS extends CommonModelAbstract {
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;
	//@Column(name = "clientId")
	public Long clientId;
	//@Column(name = "TAN")
	public String TAN;
	//@Column(name = "QUARTER")
	public String QUARTER;
	//@Column(name = "FORM")
	public String FORM;
	//@Column(name = "RT")
	public String RT;
	//@Column(name = "PRN")
	public String PRN;
	//@Column(name = "STATUS")
	public String STATUS;
	//@Column(name = "AS_ON_DATE")
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
