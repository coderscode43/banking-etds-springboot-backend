package domain.in.rjsa.model.fy;


import java.text.ParseException;

import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class Ldcused extends CommonModelAbstract{
	public Long id;
	public String LDC_NUMBER;
	public String MONTH;
	public String PAN;
	public String TAN;
	public Double USEDAMT;
	
	public void setData(JSONObject dataObject) throws ParseException{ 
		
		this.LDC_NUMBER =  dataObject.get("LDC_NUMBER").toString();
		this.MONTH =  dataObject.get("MONTH").toString();
		this.PAN =  dataObject.get("PAN").toString();
		this.TAN =  dataObject.get("TAN").toString();
		this.USEDAMT =  Double.valueOf(dataObject.get("USEDAMT").toString());
	}
    
}
