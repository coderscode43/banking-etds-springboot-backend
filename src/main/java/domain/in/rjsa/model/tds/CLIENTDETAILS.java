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
//@Table(name = "Taxo.CLIENTDETAILS")
public class CLIENTDETAILS extends CommonModelAbstract {

	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "TAN")
	public String TAN;

	//@Column(name = "NAME")
	public String NAME;

	//@Column(name = "BRANCH")
	public String BRANCH;

	//@Column(name = "PAN")
	public String PAN;

	//@Column(name = "TYPE")
	public String TYPE;

	//@Column(name = "EMAIL")
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
