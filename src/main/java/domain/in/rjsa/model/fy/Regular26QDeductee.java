package domain.in.rjsa.model.fy;

import java.text.ParseException;
import java.util.Date;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.in.rjsa.model.form.CommonModelAbstract;
import domain.in.rjsa.util.JsonDateSerializer;
import lombok.Data;

@Data
public class Regular26QDeductee extends CommonModelAbstract {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Long id;

	public String challanHeading;

	public String deducteeRefNo;

	public String deducteeCode;

	public String pan;

	public String name;

	public String sectionCode;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date dateOfPayment;

	public Double amountPaid;

	public Double tds;

	public Double surcharge;

	public Double eduCess;

	public Double totalTaxDeducted;

	public Double totalTaxDeposited;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date dateOfDeduction;

	public Double rateAtWhichTaxCollected;

	public String remarksReason;

	public String certificateNumber;

	public String fy;

	public String quarter;

	public String branchCode;

	public String accNo;

	public Long challanSrNo;

	public String month;

	public String custVendId;

	public String uniqueRefNo;

	public Double cashWithdrawal194N;

	public Double cashWithdrawal194N20Lto1Cr;

	public Double cashWithdrawal194N1Cr;

	public String TAN;

	public String roCode;

	public String errorDescription;

	public String warningDescription;

	public Double shortDeduction;

	public Double interestOnShortDeduction;

	public Double interestOnLatePayment;

	public Double interestOnLateDeduction;

	public boolean resolved;

	public String comments;

	public Long deducteeSrNo;

	public Double tranAmt;

	public String additionalDetail;

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
