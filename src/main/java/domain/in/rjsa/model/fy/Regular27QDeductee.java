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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.in.rjsa.model.form.CommonModelAbstract;
import domain.in.rjsa.util.JsonDateSerializer;
import lombok.Data;

@Data
//@Entity
//@Table(name = "AAACN4165C_2324.regular27QDeductee")
public class Regular27QDeductee extends CommonModelAbstract {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;

	//@Column(name = "challanHeading")
	public String challanHeading;

	//@Column(name = "deducteeRefNo")
	public String deducteeRefNo;

	//@Column(name = "deducteeCode")
	public String deducteeCode;

	//@Column(name = "pan")
	public String pan;

	//@Column(name = "name")
	public String name;

	//@Column(name = "sectionCode")
	public String sectionCode;

	//@Temporal(TemporalType.DATE)
	//@Column(name = "dateOfPayment")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date dateOfPayment;

	//@Column(name = "amountPaid")
	public Double amountPaid;

	//@Column(name = "tds")
	public Double tds;

	//@Column(name = "surcharge")
	public Double surcharge;

	//@Column(name = "eduCess")
	public Double eduCess;

	//@Column(name = "totalTaxDeducted")
	public Double totalTaxDeducted;

	//@Column(name = "totalTaxDeposited")
	public Double totalTaxDeposited;

	//@Temporal(TemporalType.DATE)
	//@Column(name = "dateOfDeduction")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date dateOfDeduction;

	//@Column(name = "rateAtWhichTaxCollected")
	public Double rateAtWhichTaxCollected;

	//@Column(name = "remarksReason")
	public String remarksReason;

	//@Column(name = " grossingUpIndicator")
	public String grossingUpIndicator;

	//@Column(name = "certificateNumber")
	public String certificateNumber;

	//@Column(name = "uniqueAcknowledgeNo")
	public Long uniqueAcknowledgeNo;

	//@Column(name = "countryOfResidence")
	public String countryOfResidence;

	//@Column(name = "emailId")
	public String emailId;

	//@Column(name = "contactNoOfDeductee")
	public Long contactNoOfDeductee;

	//@Column(name = "addressOfDeductee")
	public String addressOfDeductee;

	//@Column(name = "taxIdentificationNo")
	public Long taxIdentificationNo;

	//@Column(name = "fy")
	public String fy;

	//@Column(name = "quarter")
	public String quarter;

	//@Column(name = "tdsRateAsPerItActs")
	public String tdsRateAsPerItActs;

	//@Column(name = "natureOfRemittance")
	public String natureOfRemittance;

	//@Column(name = "branchCode")
	public Long branchCode;

	//@Column(name = "accNo")
	public String accNo;

	//@Column(name = "challanSrNo")
	public Long challanSrNo;

	//@Column(name = "month")
	public String month;

	//@Column(name = "custVendId")
	public String custVendId;

	//@Column(name = "uniqueRefNo")
	public String uniqueRefNo;

	//@Column(name = "cashWithdrawal194N")
	public Double cashWithdrawal194N;

	//@Column(name = "cashWithdrawal194N20Lto1Cr")
	public Double cashWithdrawal194N20Lto1Cr;

	//@Column(name = "cashWithdrawal194N1Cr")
	public Double cashWithdrawal194N1Cr;

	//@Column(name = "TAN")
	public String TAN;

	//@Column(name = "roCode")
	public String roCode;

	//@Column(name = "errorDescription")
	public String errorDescription;

	//@Column(name = "warningDescription")
	public String warningDescription;

	//@Column(name = "ShortDeduction")
	public Double shortDeduction;

	//@Column(name = "InterestOnShortDeduction")
	public Double interestOnShortDeduction;

	//@Column(name = "InterestOnLatePayment")
	public Double interestOnLatePayment;

	//@Column(name = "InterestOnLateDeduction")
	public Double interestOnLateDeduction;

//	//@Column(name = "verify")
//	public boolean verify = false;

	//@Column(name = "resolved")
	public boolean resolved;

	//@Column(name = "comments")
	public String comments;

//	//@Column(name = "remarks")
//	public String remarks;

	//@Column(name = "deducteeSrNo")
	public Long deducteeSrNo;

	//@Column(name = "tranAmt")
	public Long tranAmt;

	//@Column(name = "additionalDetail")
	public String additionalDetail;

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
