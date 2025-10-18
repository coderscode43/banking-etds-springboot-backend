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
public class Regular26QDeductee extends CommonModelAbstract {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Validate(order = 48)
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
	public String certificateNumber;
	
	@Validate(order = 19)
	public Double cashWithdrawal194N;

	@Validate(order = 20)
	public Double cashWithdrawal194N20Lto1Cr;

	@Validate(order = 21)
	public Double cashWithdrawal194N1Cr;
	
	/* 3 new columns */
	@Validate(order = 22)
	public Double cashWithdrawal194N3Cr;
	
	@Validate(order = 23)
	public Double cashWIthdrawal194N20LtO3Cr;
	
	@Validate(order = 24)
	public Double cashWithdrawal194Ngr3Cr;
	
	@Validate(order = 25)
	public String errorDescription;

	@Validate(order = 26)
	public String warningDescription;

	@Validate(order = 27)
	public Double shortDeduction;

	@Validate(order = 28)
	public Double interestOnShortDeduction;

	@Validate(order = 29)
	public Double interestOnLatePayment;

	@Validate(order = 30)
	public Double interestOnLateDeduction;
	
	@Validate(order = 31)
	public String branchCode;
	
	@Validate(order = 32)
	public String custVendId;
	
	@Validate(order = 33)
	public String challanHeading;
	
	@Validate(order = 34)
	public String accNo;
	
	@Validate(order = 35)
	public String uniqueRefNo;
	
	@Validate(order = 36)
	public String roCode;
	
	@Validate(order = 37)
	public String TAN;
	
	@Validate(order = 38)
	public String comments;
	
	@Validate(order = 39)
	public Double tranAmt;

	@Validate(order = 40)
	public String additionalDetail;

	@Validate(order = 41)
	public String month;

	@Validate(order = 42)
	public boolean resolved;

	@Validate(order = 43)
	public String fy;
	
	@Validate(order = 44)
	public String quarter;
	
	/* 3 new columns */
	@Validate(order = 45)
	private String validInvalidPan;
	
	@Validate(order = 46)
	private String panStatus;
	
	@Validate(order = 47)
	private String specifiedPerson;

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

	public void updateAllowedFields(Regular26QDeductee entity) {
		this.resolved = entity.isResolved();
		this.deducteeCode = entity.getDeducteeCode();
		this.pan = entity.getPan();
		this.name = entity.getName();
		this.sectionCode = entity.getSectionCode();
		this.dateOfPayment = entity.getDateOfPayment();
		this.amountPaid = entity.getAmountPaid();
		this.tds = entity.getTds();
		this.surcharge = entity.getSurcharge();
		this.eduCess = entity.getEduCess();
		this.totalTaxDeducted = entity.getTotalTaxDeducted();
		this.totalTaxDeposited = entity.getTotalTaxDeposited();
		this.dateOfDeduction = entity.getDateOfDeduction();
		this.rateAtWhichTaxCollected = entity.getRateAtWhichTaxCollected();
		this.remarksReason = entity.getRemarksReason();
		this.certificateNumber = entity.getCertificateNumber();
		this.interestOnLatePayment = entity.getInterestOnLatePayment();
		this.deducteeRefNo = entity.getDeducteeRefNo();
		this.challanSrNo = entity.getChallanSrNo();
		this.cashWithdrawal194N20Lto1Cr = entity.getCashWithdrawal194N20Lto1Cr();
		this.cashWithdrawal194N1Cr = entity.getCashWithdrawal194N1Cr();
		this.cashWithdrawal194N = entity.getCashWithdrawal194N();
		this.shortDeduction = entity.getShortDeduction();
		this.interestOnShortDeduction = entity.getInterestOnShortDeduction();
		this.interestOnLateDeduction = entity.getInterestOnLateDeduction();
		this.deducteeSrNo = entity.getDeducteeSrNo();
		this.tranAmt = entity.getTranAmt();
		this.additionalDetail = entity.getAdditionalDetail();
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
	    this.certificateNumber = dataObject.get("CERTIFICATENUMBER").toString();
	    this.fy = dataObject.get("FY").toString();
	    this.quarter = dataObject.get("QUARTER").toString();
	    this.branchCode = dataObject.get("BRANCHCODE").toString();
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
	    this.tranAmt = Double.valueOf(dataObject.get("TRANAMT").toString());
	    this.additionalDetail = dataObject.get("ADDITIONALDETAIL").toString();

	    this.dateOfPayment = returnDate(dataObject.get("DATEOFPAYMENT").toString());
	    this.dateOfDeduction = returnDate(dataObject.get("DATEOFDEDUCTION").toString());
	}


}
