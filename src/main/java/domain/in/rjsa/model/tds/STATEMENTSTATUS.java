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
//@Table(name = "Taxo.STATEMENTSTATUS")
public class STATEMENTSTATUS extends CommonModelAbstract{
	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;
	//@Column(name = "TAN")
	public String TAN;
	//@Column(name = "FORM")
	public String FORM;
	//@Column(name = "QUARTER")
	public String QUARTER;
//	//@Temporal(TemporalType.DATE)
//	//@Column(name = "AS_ON_DATE")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
//	public Date AS_ON_DATE;
	//@Column(name = "AS_ON_DATE")
	public String AS_ON_DATE;
	//@Column(name = "FY")
	public String FY;
	//@Column(name = "STATUS")
	public String STATUS;
	
	//@Column(name = "RT")
	public String RT;
	
	
    public void setData(JSONObject dataObject) throws ParseException{
        this.TAN = (String) dataObject.get("TAN");
        this.FORM = (String) dataObject.get("FORM");
        this.QUARTER = (String) dataObject.get("QUARTER");
        this.AS_ON_DATE = (String) dataObject.get("AS_ON_DATE");
        this.FY = (String) dataObject.get("FY");
        this.STATUS = (String) dataObject.get("STATUS");
        this.RT = (String) dataObject.get("RT");
    }
	
}
