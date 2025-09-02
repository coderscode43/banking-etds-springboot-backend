package domain.in.rjsa.model.tds;

import java.text.ParseException;

import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;
@Data
public class STATEMENTSTATUS extends CommonModelAbstract{
	public Long id;
	public String TAN;
	public String FORM;
	public String QUARTER;
//	//	//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
//	public Date AS_ON_DATE;
	public String AS_ON_DATE;
	public String FY;
	public String STATUS;
	
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
