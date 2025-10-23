package domain.in.rjsa.model.fy;

import java.text.ParseException;
import java.util.Date;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.in.rjsa.annotation.Validate;
import domain.in.rjsa.model.form.CommonModelAbstract;
import domain.in.rjsa.util.JsonDateSerializer;
import lombok.Data;

@Data
public class Regular27QDeductee extends CommonModelAbstract {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Validate(order = 58)
	public Long id;
	
	@Validate(order = 1)
	public Long deducteeSrNo;
	
	@Validate(order = 2)
	public Long challanSrNo;

	@Validate(order = 3)
	public String deducteeRefNo;
	
	@Validate(order = 4)
	public String deducteeCode;
	
	@Validate(order = 5)
	public String pan;
	
	@Validate(order = 6)
	public String name;
	
	@Validate(order = 7)
	public String sectionCode;
	
	@Validate(order = 8)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date dateOfPayment;
	
	@Validate(order = 9)
	public Double amountPaid;

	@Validate(order = 10)
	public Double tds;
	
	@Validate(order = 11)
	public Double surcharge;
	
	@Validate(order = 12)
	public Double eduCess;
	
	@Validate(order = 13)
	public Double totalTaxDeducted;
	
	@Validate(order = 14)
	public Double totalTaxDeposited;
	
	@Validate(order = 15)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date dateOfDeduction;
	
	@Validate(order = 16)
	public Double rateAtWhichTaxCollected;
	
	@Validate(order = 17)
	public String remarksReason;
	
	@Validate(order = 18)
	public String grossingUpIndicator;
	
	@Validate(order = 19)
	public String certificateNumber;

	@Validate(order = 20)
	public String tdsRateAsPerItActs;

	@Validate(order = 21)
	public String natureOfRemittance;
	
	@Validate(order = 22)
	public Long uniqueAcknowledgeNo;

	@Validate(order = 23)
	public String countryOfResidence;
	
	@Validate(order = 24)
	public String emailId;

	@Validate(order = 25)
	public Long contactNoOfDeductee;

	@Validate(order = 26)
	public String addressOfDeductee;

	@Validate(order = 27)
	public Long taxIdentificationNo;
	
	@Validate(order = 28)
	public Double cashWithdrawal194N;

	@Validate(order = 29)
	public Double cashWithdrawal194N20Lto1Cr;

	@Validate(order = 30)
	public Double cashWithdrawal194N1Cr;
	
	@Validate(order = 35)
	public String errorDescription;

	@Validate(order = 36)
	public String warningDescription;

	@Validate(order = 37)
	public Double shortDeduction;
	
	@Validate(order = 38)
	public Double interestOnShortDeduction;

	@Validate(order = 39)
	public Double interestOnLatePayment;

	@Validate(order = 40)
	public Double interestOnLateDeduction;
	
	@Validate(order = 41)
	public Long branchCode;
	
	@Validate(order = 42)
	public String custVendId;
	
	@Validate(order = 43)
	public String challanHeading;
	
	@Validate(order = 44)
	public String accNo;
	
	@Validate(order = 45)
	public String uniqueRefNo;
	
	@Validate(order = 46)
	public String roCode;
	
	@Validate(order = 47)
	public String TAN;
	
	@Validate(order = 48)
	public String comments;
	
	@Validate(order = 49)
	public Long tranAmt;
	
	@Validate(order = 50)
	public String additionalDetail;
	
	@Validate(order = 51)
	public String month;
	
	@Validate(order = 52)
	public boolean resolved;
	
	@Validate(order = 53)
	public String fy;
	
	@Validate(order = 54)
	public String quarter;
	
	/* 7 new columns */
	@Validate(order = 31)
	public Double cashWithdrawal194N3Cr;
	
	@Validate(order = 32)
	public Double cashWithdrawal194N20Lto3Cr;
	
	@Validate(order = 33)
	public Double cashWithdrawal194Ngr3Cr;
	
	@Validate(order = 34)
	public String whetherCollecteeOptRegime;
	
	@Validate(order = 55)
	public String validInvalidPan;

	@Validate(order = 56)
	public String panStatus;

	@Validate(order = 57)
	public String specifiedPerson;

	public void setEntity(Regular27QDeductee form27Q) {
		this.dateOfPayment = form27Q.getDateOfPayment();
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDateOfDeduction() {
		return dateOfDeduction;
	}

	public void setDateOfDeduction(Date dateOfDeduction) {
		this.dateOfDeduction = dateOfDeduction;
	}

	public void updateAllowedFields(Regular27QDeductee regular27qWeb) {
		// TODO Auto-generated method stub.
		this.resolved = regular27qWeb.isResolved();
		this.uniqueAcknowledgeNo = regular27qWeb.getUniqueAcknowledgeNo();
		this.certificateNumber = regular27qWeb.getCertificateNumber();
		this.countryOfResidence = regular27qWeb.getCountryOfResidence();
		this.tdsRateAsPerItActs = regular27qWeb.getTdsRateAsPerItActs();
		this.grossingUpIndicator = regular27qWeb.getGrossingUpIndicator();
		this.emailId = regular27qWeb.getEmailId();
		this.contactNoOfDeductee = regular27qWeb.getContactNoOfDeductee();
		this.natureOfRemittance = regular27qWeb.getNatureOfRemittance();
		this.deducteeCode = regular27qWeb.getDeducteeCode();
		this.addressOfDeductee = regular27qWeb.getAddressOfDeductee();
		this.pan = regular27qWeb.getPan();
		this.taxIdentificationNo = regular27qWeb.getTaxIdentificationNo();
		this.name = regular27qWeb.getName();
		this.cashWithdrawal194N = regular27qWeb.getCashWithdrawal194N();
		this.sectionCode = regular27qWeb.getSectionCode();
		this.cashWithdrawal194N20Lto1Cr = regular27qWeb.getCashWithdrawal194N20Lto1Cr();
		this.dateOfPayment = regular27qWeb.getDateOfPayment();
		this.cashWithdrawal194N1Cr = regular27qWeb.getCashWithdrawal194N1Cr();
		this.amountPaid = regular27qWeb.getAmountPaid();
		this.tds = regular27qWeb.getTds();
		this.surcharge = regular27qWeb.getSurcharge();
		this.shortDeduction = regular27qWeb.getShortDeduction();
		this.eduCess = regular27qWeb.getEduCess();
		this.interestOnShortDeduction = regular27qWeb.getInterestOnShortDeduction();
		this.totalTaxDeducted = regular27qWeb.getTotalTaxDeducted();
		this.interestOnLatePayment = regular27qWeb.getInterestOnLatePayment();
		this.totalTaxDeposited = regular27qWeb.getTotalTaxDeposited();
		this.interestOnLateDeduction = regular27qWeb.getInterestOnLateDeduction();
		this.dateOfDeduction = regular27qWeb.getDateOfDeduction();
		this.remarksReason = regular27qWeb.getRemarksReason();
		this.rateAtWhichTaxCollected = regular27qWeb.getRateAtWhichTaxCollected();
	}
	
	
	public void setData(JSONObject dataObject) throws ParseException{
	    this.challanHeading = dataObject.get("CHALLANHEADING").toString();
	    this.deducteeRefNo = dataObject.get("DEDUCTEEREFNO").toString();
	    this.deducteeCode = dataObject.get("DEDUCTEECODE").toString();
	    this.pan = dataObject.get("PAN").toString();
	    this.name = dataObject.get("NAME").toString();
	    this.sectionCode = dataObject.get("SECTIONCODE").toString();
	    this.amountPaid = Double.valueOf(dataObject.get("AMOUNTPAID").toString());
	    this.tds = Double.valueOf(dataObject.get("TDS").toString());
	    this.surcharge = Double.valueOf(dataObject.get("SURCHARGE").toString());
	    this.eduCess = Double.valueOf(dataObject.get("EDUCESS").toString());
	    this.totalTaxDeducted = Double.valueOf(dataObject.get("TOTALTAXDEDUCTED").toString());
	    this.totalTaxDeposited = Double.valueOf(dataObject.get("TOTALTAXDEPOSITED").toString());
	    this.rateAtWhichTaxCollected = Double.valueOf(dataObject.get("RATEATWICHTAXCOLLECTED").toString());
	    this.remarksReason = dataObject.get("REMARKSREASON").toString();
	    this.grossingUpIndicator = dataObject.get("GROSSINGUPINDICATOR").toString();
	    this.certificateNumber = dataObject.get("CERTIFICATENUMBER").toString();
	    this.uniqueAcknowledgeNo = Long.valueOf(dataObject.get("UNIQUEACKNOWLEDGENO").toString());
	    this.countryOfResidence = dataObject.get("COUNTRYOFRESIDENCE").toString();
	    this.emailId = dataObject.get("EMAILID").toString();
	    this.contactNoOfDeductee = Long.valueOf(dataObject.get("CONTACTNOOFDEDUCTEE").toString());
	    this.addressOfDeductee = dataObject.get("ADDRESSOFDEDUCTEE").toString();
	    this.taxIdentificationNo = Long.valueOf(dataObject.get("TAXIDENTIFICATIONNO").toString());
	    this.fy = dataObject.get("FY").toString();
	    this.quarter = dataObject.get("QUARTER").toString();
	    this.tdsRateAsPerItActs = dataObject.get("TDSRATEASPERITACTS").toString();
	    this.natureOfRemittance = dataObject.get("NATUREOFREMITTANCE").toString();
	    this.branchCode = Long.valueOf(dataObject.get("BRANCHCODE").toString());
	    this.accNo = dataObject.get("ACCNO").toString();
	    this.challanSrNo = Long.valueOf(dataObject.get("CHALLANSRNO").toString());
	    this.month = dataObject.get("MONTH").toString();
	    this.custVendId = dataObject.get("CUSTVENDID").toString();
	    this.uniqueRefNo = dataObject.get("UNIQUEREFNO").toString();
	    this.cashWithdrawal194N = Double.valueOf(dataObject.get("CASHWITHDRAWAL194N").toString());
	    this.cashWithdrawal194N20Lto1Cr = Double.valueOf(dataObject.get("CASHWITHDRAWAL194N20LTO1CR").toString());
	    this.cashWithdrawal194N1Cr = Double.valueOf(dataObject.get("CASHWITHDRAWAL194N1CR").toString());
	    this.TAN = dataObject.get("TAN").toString();
	    this.roCode = dataObject.get("ROCODE").toString();
	    this.errorDescription = dataObject.get("ERRORDESCRIPTION").toString();
	    this.warningDescription = dataObject.get("WARNINGDESCRIPTION").toString();
	    this.shortDeduction = Double.valueOf(dataObject.get("SHORTDEDUCTION").toString());
	    this.interestOnShortDeduction = Double.valueOf(dataObject.get("INTERESTONSHORTDEDUCTION").toString());
	    this.interestOnLatePayment = Double.valueOf(dataObject.get("INTERESTONLATEPAYMENT").toString());
	    this.interestOnLateDeduction = Double.valueOf(dataObject.get("INTERESTONLATEDEDUCTION").toString());
	    this.resolved = Boolean.parseBoolean(dataObject.get("RESOLVED").toString());
	    this.comments = dataObject.get("COMMENTS").toString();
	    this.deducteeSrNo = Long.valueOf(dataObject.get("DEDUCTEESRNO").toString());
	    this.tranAmt = Long.valueOf(dataObject.get("TRANAMT").toString());
	    this.additionalDetail = dataObject.get("ADDITIONALDETAIL").toString();
	    
	    this.dateOfPayment = returnDate(dataObject.get("DATEOFPAYMENT").toString());
	    this.dateOfDeduction = returnDate(dataObject.get("DATEOFDEDUCTION").toString());
	}

}
