package domain.in.rjsa.model.tds;

import java.text.ParseException;

import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class ADDITIONALDETAILS extends CommonModelAbstract{
    
	public String TAN;
	public String STATENAME;
	public String HASTHESTATEMENTHASBEENFILEDEARLIER;
	public String PRN_PREVIOUS;
	public String REMARK;
	public String TDSCIRCLE1;
	public String TDSCIRCLE2;
	public String TDSCIRCLECITY;
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
