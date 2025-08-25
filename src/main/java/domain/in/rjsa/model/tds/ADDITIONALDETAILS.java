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
//@Table(name = "Taxo.ADDITIONALDETAILS")
public class ADDITIONALDETAILS extends CommonModelAbstract{
    
	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "TAN")
	public String TAN;
	//@Column(name = "STATENAME")
	public String STATENAME;
	//@Column(name = "HASTHESTATEMENTHASBEENFILEDEARLIER")
	public String HASTHESTATEMENTHASBEENFILEDEARLIER;
	//@Column(name = "PRN_PREVIOUS")
	public String PRN_PREVIOUS;
	//@Column(name = "REMARK")
	public String REMARK;
	//@Column(name = "TDSCIRCLE1")
	public String TDSCIRCLE1;
	//@Column(name = "TDSCIRCLE2")
	public String TDSCIRCLE2;
	//@Column(name = "TDSCIRCLECITY")
	public String TDSCIRCLECITY;
	//@Column(name = "TDSCIRCLEPIN")
	public String TDSCIRCLEPIN;
	
	
    public void setdata(JSONObject dataObject) throws ParseException{
        this.TAN = (String) dataObject.get("TAN");
        this.STATENAME = (String) dataObject.get("STATENAME");
        this.HASTHESTATEMENTHASBEENFILEDEARLIER = (String) dataObject.get("HASTHESTATEMENTHASBEENFILEDEARLIER");
        this.PRN_PREVIOUS = (String) dataObject.get("PRN_PREVIOUS");
        this.REMARK = (String) dataObject.get("REMARK");
        this.TDSCIRCLE1 = (String) dataObject.get("TDSCIRCLE1");
        this.TDSCIRCLE2 = (String) dataObject.get("TDSCIRCLE2");
        this.TDSCIRCLECITY = (String) dataObject.get("TDSCIRCLECITY");
        this.TDSCIRCLEPIN = (String) dataObject.get("TDSCIRCLEPIN");
    }
}
