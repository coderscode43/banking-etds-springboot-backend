package domain.in.rjsa.model.fy;

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
//@Table(name = "AAACN4165C_2324.challanDetails")
public class ChallanDetails extends CommonModelAbstract {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;

	//@Column(name = "challanSrNo")
	public Long challanSrNo;

	//@Column(name = "tds")
	public Double tds;

	//@Column(name = "surcharge")
	public Double surcharge;

	//@Column(name = "eduCess")
	public Double eduCess;

	//@Column(name = "interest")
	public Double interest;

	//@Column(name = "fee")
	public Double fee;

	//@Column(name = "others")
	public Double others;

	//@Column(name = "totalTaxDeposited")
	public Double totalTaxDeposited;

	//@Column(name = "byBookEntry")
	public String byBookEntry;

	//@Column(name = "bsrCode")
	public Long bsrCode;

	//@Column(name = "idNo")
	public Long idNo;

	//@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	//@Column(name = "dateOfDeposition")
	private Date dateOfDeposition;

	//@Column(name = "minorHeadOfChallan")
	public Double minorHeadOfChallan;

	//@Column(name = "interestAllocated")
	public Double interestAllocated;

	//@Column(name = "otherAmtAllocated")
	public Double otherAmtAllocated;

	//@Column(name = "nilChallanIndicator")
	public String nilChallanIndicator;

	//@Column(name = "errorDescription")
	public String errorDescription;

	//@Column(name = "warningDescription")
	public String warningDescription;

	//@Column(name = "shortPayment")
	public Double shortPayment;

	//@Column(name = "interestOnShortPayment")
	public Double interestOnShortPayment;

	//@Column(name = "challanHeading")
	public String challanHeading;

	//@Column(name = "fy")
	public String fy;

	//@Column(name = "quarter")
	public String quarter;

	//@Column(name = "month")
	public String month;

	//@Column(name = "form")
	public String form;

	//@Column(name = "TAN")
	public String TAN;

	public void setData(JSONObject dataObject) throws ParseException{
		this.challanSrNo = Long.valueOf(dataObject.get("CHALLANSRNO").toString());
		this.tds = Double.valueOf(dataObject.get("TDS").toString());
		this.surcharge = Double.valueOf(dataObject.get("SURCHARGE").toString());
		this.eduCess = Double.valueOf(dataObject.get("EDUCESS").toString());
		this.interest = Double.valueOf(dataObject.get("INTEREST").toString());
		this.fee = Double.valueOf(dataObject.get("FEE").toString());
		this.others = Double.valueOf(dataObject.get("OTHERS").toString());
		this.totalTaxDeposited = Double.valueOf(dataObject.get("TOTALTAXDEPOSITED").toString());
		this.byBookEntry = dataObject.get("BYBOOKENTRY").toString();
		this.bsrCode = Long.valueOf(dataObject.get("BSRCODE").toString());
		this.idNo = Long.valueOf(dataObject.get("IDNO").toString());
		this.minorHeadOfChallan = Double.valueOf(dataObject.get("MINORHEADOFCHALLAN").toString());
		this.interestAllocated = Double.valueOf(dataObject.get("INTERESTALLOCATED").toString());
		this.otherAmtAllocated = Double.valueOf(dataObject.get("OTHERAMTALLOCATED").toString());
		this.nilChallanIndicator = dataObject.get("NILCHALLANINDICATOR").toString();
		this.errorDescription = dataObject.get("ERRORDESCRIPTION").toString();
		this.warningDescription = dataObject.get("WARNINGDESCRIPTION").toString();
		this.shortPayment = Double.valueOf(dataObject.get("SHORTPAYMENT").toString());
		this.interestOnShortPayment = Double.valueOf(dataObject.get("INTERESTONSHORTPAYMENT").toString());
		this.challanHeading = dataObject.get("CHALLANHEADING").toString();
		this.fy = dataObject.get("FY").toString();
		this.quarter = dataObject.get("QUARTER").toString();
		this.month = dataObject.get("MONTH").toString();
		this.form = dataObject.get("FORM").toString();
		this.TAN = dataObject.get("TAN").toString();

		this.dateOfDeposition = returnDate(dataObject.get("DATEOFDEPOSITION").toString());
	}

}
