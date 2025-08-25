package domain.in.rjsa.model.fy;


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
//@Table(name = "AAACN4165C_2324.LDCUSED")
public class Ldcused extends CommonModelAbstract{
	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;
	//@Id
	//@Column(name = "LDC_NUMBER")
	public String LDC_NUMBER;
	//@Column(name = "MONTH")
	public String MONTH;
	//@Column(name = "PAN")
	public String PAN;
	//@Column(name = "TAN")
	public String TAN;
	//@Column(name = "USEDAMT")
	public Double USEDAMT;
	
	public void setData(JSONObject dataObject) throws ParseException{ 
		
		this.LDC_NUMBER =  dataObject.get("LDC_NUMBER").toString();
		this.MONTH =  dataObject.get("MONTH").toString();
		this.PAN =  dataObject.get("PAN").toString();
		this.TAN =  dataObject.get("TAN").toString();
		this.USEDAMT =  Double.valueOf(dataObject.get("USEDAMT").toString());
	}
    
}
