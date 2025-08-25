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
//@Table(name = "Taxo.DEFAULTSUMMARY")
public class DEFAULTSUMMARY extends CommonModelAbstract{

	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;

	//@Column(name = "TAN")
	public String TAN;
	//@Column(name = "FY")
	public String FY;
	//@Column(name = "QUARTER")
	public String QUARTER;
	//@Column(name = "FORM")
	public String FORM;
	//@Column(name = "STATUS")
	public String STATUS;
	//@Column(name = "AS_ON_DATE")
	public String AS_ON_DATE;
	//@Column(name = "NET_PAY")
	public String NET_PAY;
	//@Column(name = "TOTAL")
	public String TOTAL;
	//@Column(name = "SHORT_PAYMENT")
	public String SHORT_PAYMENT;
	//@Column(name = "INTEREST_SHORT_PAYMENT")
	public String INTEREST_SHORT_PAYMENT;
	//@Column(name = "SHORT_DEDUCTION")
	public String SHORT_DEDUCTION;
	//@Column(name = "INTEREST_SHORT_DEDUCTION")
	public String INTEREST_SHORT_DEDUCTION;
	//@Column(name = "ADDITIONAL_LATE_PAYMENT")
	public String ADDITIONAL_LATE_PAYMENT;
	//@Column(name = "INTEREST_ON_LATE_PAYMENT")
	public String INTEREST_ON_LATE_PAYMENT;
	//@Column(name = "LATE_FILING")
	public String LATE_FILING;
	//@Column(name = "INTERST_LATE_DEDUCTION")
	public String INTERST_LATE_DEDUCTION;
	//@Column(name = "INTERST")
	public String INTERST;
	//@Column(name = "ADDITIONAL_LATE_FILING")
	public String ADDITIONAL_LATE_FILING;
	//@Column(name = "ADDITIONAL_LATE_DEDUCTION")
	public String ADDITIONAL_LATE_DEDUCTION;
	//@Column(name = "ORDER_PASSED_DATE")
	public String ORDER_PASSED_DATE;
	//@Column(name = "DEDUCTEES_WITHOUT_PAN")
	public String DEDUCTEES_WITHOUT_PAN;
	//@Column(name = "DEDUCTEES_WITH_INVALID_PAN")
	public String DEDUCTEES_WITH_INVALID_PAN;
	
	
	public void setdata(JSONObject dataObject) throws ParseException{
        this.TAN = (String) dataObject.get("TAN");
        this.FY = (String) dataObject.get("FY");
        this.QUARTER = (String) dataObject.get("QUARTER");
        this.FORM = (String) dataObject.get("FORM");
        this.STATUS = (String) dataObject.get("STATUS");
        this.AS_ON_DATE = (String) dataObject.get("AS_ON_DATE");
        this.NET_PAY = (String) dataObject.get("NET_PAY");
        this.TOTAL = (String) dataObject.get("TOTAL");
        this.SHORT_PAYMENT = (String) dataObject.get("SHORT_PAYMENT");
        this.INTEREST_SHORT_PAYMENT = (String) dataObject.get("INTEREST_SHORT_PAYMENT");
        this.SHORT_DEDUCTION = (String) dataObject.get("SHORT_DEDUCTION");
        this.INTEREST_SHORT_DEDUCTION = (String) dataObject.get("INTEREST_SHORT_DEDUCTION");
        this.ADDITIONAL_LATE_PAYMENT = (String) dataObject.get("ADDITIONAL_LATE_PAYMENT");
        this.INTEREST_ON_LATE_PAYMENT = (String) dataObject.get("INTEREST_ON_LATE_PAYMENT");
        this.LATE_FILING = (String) dataObject.get("LATE_FILING");
        this.INTERST_LATE_DEDUCTION = (String) dataObject.get("INTERST_LATE_DEDUCTION");
        this.INTERST = (String) dataObject.get("INTERST");
        this.ADDITIONAL_LATE_FILING = (String) dataObject.get("ADDITIONAL_LATE_FILING");
        this.ADDITIONAL_LATE_DEDUCTION = (String) dataObject.get("ADDITIONAL_LATE_DEDUCTION");
        this.ORDER_PASSED_DATE = (String) dataObject.get("ORDER_PASSED_DATE");
        this.DEDUCTEES_WITHOUT_PAN = (String) dataObject.get("DEDUCTEES_WITHOUT_PAN");
        this.DEDUCTEES_WITH_INVALID_PAN = (String) dataObject.get("DEDUCTEES_WITH_INVALID_PAN");
    }
	
}
