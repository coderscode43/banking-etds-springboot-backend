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
//@Table(name = "Taxo.REQUESTSTATUS")
public class REQUESTSTATUS extends CommonModelAbstract{

	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "ID")
	public Long ID;
	//@Column(name = "TAN")
	public String TAN;
	//@Column(name = "FY")
	public String FY;
	//@Column(name = "QUARTER")
	public String QUARTER;	
	//@Column(name = "FORM")
	public String FORM;
	//@Column(name = "RETURNTYPE")
	public String RETURNTYPE;
	//@Column(name = "REQUESTDATE")
	public String REQUESTDATE;
	//@Column(name = "REQUEST_NUMBER")
	public String REQUEST_NUMBER;
	//@Column(name = "STATUS")
	public String STATUS;
	//@Column(name = "REQUEST_TYPE")
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
