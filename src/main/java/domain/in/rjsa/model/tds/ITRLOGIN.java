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
//@Table(name = "Taxo.ITRLOGIN")
public class ITRLOGIN extends CommonModelAbstract{
	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "TAN")
	public String TAN;
	//@Column(name = "USERNAME")
	public String USERNAME;
	//@Column(name = "PASSWORD")
	public String PASSWORD;
	//@Column(name = "ITDREIN")
	public String ITDREIN;


    public void setdata(JSONObject dataObject) throws ParseException{
        this.TAN = (String) dataObject.get("TAN");
        this.USERNAME = (String) dataObject.get("USERNAME");
        this.PASSWORD = (String) dataObject.get("PASSWORD");
        this.ITDREIN = (String) dataObject.get("ITDREIN");
    }

}
