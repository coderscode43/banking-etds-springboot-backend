package domain.in.rjsa.model.tds;

import java.text.ParseException;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
//@Entity
//@Table(name = "Taxo.CHALLAN")
public class CHALLAN extends CommonModelAbstract{

	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "CIN")
	public String CIN;	
	//@Column(name = "TAN")
	public String TAN;
	//@Column(name = "AVAILABLE_BALENCE")
	public String AVAILABLE_BALENCE;
	//@Column(name = "AMOUNT_OF_CLALLAN")
	public String AMOUNT_OF_CLALLAN;
	
	//@Column(name = "DATE_OF_DEPOSITION")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
	public Date DATE_OF_DEPOSITION;
	
	//@Column(name = "AS_ON_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
	public Date AS_ON_DATE;
	
	//@Column(name = "CHALLAN_MISMATCH")
	public String CHALLAN_MISMATCH;
	//@Column(name = "SECTION")
	public String SECTION;
	//@Column(name = "MINORHEAD")
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
