package domain.in.rjsa.model.tds;

import java.text.ParseException;
import java.util.Date;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class CHALLAN extends CommonModelAbstract{

	public String CIN;	
	public String TAN;
	public String AVAILABLE_BALENCE;
	public String AMOUNT_OF_CLALLAN;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
	public Date DATE_OF_DEPOSITION;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
	public Date AS_ON_DATE;
	
	public String CHALLAN_MISMATCH;
	public String SECTION;
	public String MINORHEAD;

	
	   public void setdata(JSONObject dataObject) throws ParseException{
	        this.CIN = (String) dataObject.get("CIN");
	        this.TAN = (String) dataObject.get("TAN");
	        this.AVAILABLE_BALENCE = (String) dataObject.get("AVAILABLE_BALENCE");
	        this.AMOUNT_OF_CLALLAN = (String) dataObject.get("AMOUNT_OF_CLALLAN");
	        this.CHALLAN_MISMATCH = (String) dataObject.get("CHALLAN_MISMATCH");
	        this.SECTION = (String) dataObject.get("SECTION");
	        this.MINORHEAD = (String) dataObject.get("MINORHEAD");

	        this.DATE_OF_DEPOSITION = returnDate(dataObject.get("DATE_OF_DEPOSITION").toString());
	        this.AS_ON_DATE = returnDate(dataObject.get("AS_ON_DATE").toString());
	    }
}
