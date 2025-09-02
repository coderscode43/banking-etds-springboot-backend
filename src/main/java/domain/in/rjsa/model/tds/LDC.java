package domain.in.rjsa.model.tds;

import java.text.ParseException;
import java.util.Date;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class LDC extends CommonModelAbstract{
//	//	//	//	//	public Long id;
	public String LDC_NUMBER;
	public String NAME;
	public String TAN;
	public String PAN;
	public String FY;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date VALID_FROM;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date VALID_TO;
	public String SECTION_CODE;
	public String NATURE_OF_PAYMENT;
	public String LDC_RATE;
	public String CERTIFICATE_LIMIT;
	public String AMOUNT_CONSUMED;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Calcutta")
	public Date ISSUE_DATE;
	public String CANCEL_DATE;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Calcutta")
	public Date AS_ON_DATE;
    
    
    public void setData(JSONObject dataObject) throws ParseException{
        this.LDC_NUMBER = (String) dataObject.get("LDC_NUMBER");
        this.NAME = (String) dataObject.get("NAME");
        this.TAN = (String) dataObject.get("TAN");
        this.PAN = (String) dataObject.get("PAN");
        this.FY = (String) dataObject.get("FY");
        this.SECTION_CODE = (String) dataObject.get("SECTION_CODE");
        this.NATURE_OF_PAYMENT = (String) dataObject.get("NATURE_OF_PAYMENT");
        this.LDC_RATE = (String) dataObject.get("LDC_RATE");
        this.CERTIFICATE_LIMIT = (String) dataObject.get("CERTIFICATE_LIMIT");
        this.AMOUNT_CONSUMED = (String) dataObject.get("AMOUNT_CONSUMED");
        this.CANCEL_DATE = (String) dataObject.get("CANCEL_DATE");
        
        this.VALID_FROM = returnDate(dataObject.get("VALID_FROM").toString());
        this.VALID_TO = returnDate(dataObject.get("VALID_TO").toString());
        this.ISSUE_DATE = returnDate(dataObject.get("ISSUE_DATE").toString());
        this.AS_ON_DATE = returnDate(dataObject.get("AS_ON_DATE").toString());
    }
    
}
