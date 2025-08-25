package domain.in.rjsa.model.tds;

import java.text.ParseException;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
//@Entity
//@Table(name = "Taxo.LDC")
public class LDC extends CommonModelAbstract{
//	//@Id
//	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
//	//@GenericGenerator(name = "native", strategy = "native")
//	//@Column(name = "id")
//	public Long id;
	//@Id
	//@Column(name = "LDC_NUMBER")
	public String LDC_NUMBER;
	//@Column(name = "NAME")
	public String NAME;
	//@Column(name = "TAN")
	public String TAN;
	//@Column(name = "PAN")
	public String PAN;
	//@Column(name = "FY")
	public String FY;
    //@Temporal(TemporalType.DATE)
	//@Column(name = "VALID_FROM")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date VALID_FROM;
    //@Temporal(TemporalType.DATE)
	//@Column(name = "VALID_TO")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date VALID_TO;
	//@Column(name = "SECTION_CODE")
	public String SECTION_CODE;
	//@Column(name = "NATURE_OF_PAYMENT")
	public String NATURE_OF_PAYMENT;
	//@Column(name = "LDC_RATE")
	public String LDC_RATE;
	//@Column(name = "CERTIFICATE_LIMIT")
	public String CERTIFICATE_LIMIT;
	//@Column(name = "AMOUNT_CONSUMED")
	public String AMOUNT_CONSUMED;
    //@Temporal(TemporalType.DATE)
	//@Column(name = "ISSUE_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Calcutta")
	public Date ISSUE_DATE;
	//@Column(name = "CANCEL_DATE")
	public String CANCEL_DATE;
    //@Temporal(TemporalType.DATE)
	//@Column(name = "AS_ON_DATE")
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
