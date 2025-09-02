package domain.in.rjsa.model.fy;

import java.text.ParseException;
import java.util.Date;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class ChallanDetails extends CommonModelAbstract {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public Long id;

	public Long challanSrNo;

	public Double tds;

	public Double surcharge;

	public Double eduCess;

	public Double interest;

	public Double fee;

	public Double others;

	public Double totalTaxDeposited;

	public String byBookEntry;

	public Long bsrCode;

	public Long idNo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date dateOfDeposition;

	public Double minorHeadOfChallan;

	public Double interestAllocated;

	public Double otherAmtAllocated;

	public String nilChallanIndicator;

	public String errorDescription;

	public String warningDescription;

	public Double shortPayment;

	public Double interestOnShortPayment;

	public String challanHeading;

	public String fy;

	public String quarter;

	public String month;

	public String form;

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
