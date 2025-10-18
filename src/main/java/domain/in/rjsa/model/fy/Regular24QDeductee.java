package domain.in.rjsa.model.fy;

import java.text.ParseException;
import java.util.Date;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.in.rjsa.annotation.Validate;
import domain.in.rjsa.model.form.CommonModelAbstract;
import domain.in.rjsa.util.JsonDateSerializer;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Regular24QDeductee extends CommonModelAbstract {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Validate(order = 41)
	public Long id;
	
	@Validate(order = 1)
	public Long deducteeSrNo;
	
	@Validate(order = 2)
	public Long challanSrNo;
	
	@Validate(order = 3)
	public String deducteeRefNo;
	
	@Validate(order = 4)
	@Size(min = 0, max = 45, message = "Pan Reference No. should not be greater than 45 characters.")
	public String panRefNo;
	
	@Validate(order = 5)
	public String pan;
	
	@Validate(order = 6)
	@Size(min = 0, max = 45, message = "Name should not be greater than 45 characters.")
	public String name;
	
	@Validate(order = 7)
	@Size(min = 0, max = 45, message = "Section Code should not be greater than 45 characters.")
	public String sectionCode;
	
	@Validate(order = 8)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date dateOfPayment;
	
	@Validate(order = 9)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date dateOfDeduction;
	
	@Validate(order = 10)
	public Double amountPaid;
	
	@Validate(order = 11)
	public Double tds;
	
	@Validate(order = 12)
	public Double surcharge;
	
	@Validate(order = 13)
	public Double eduCess;
	
	@Validate(order = 14)
	public Double totalTaxDeducted;
	
	@Validate(order = 15)
	public Double totalTaxDeposited;
	
	@Validate(order = 16)
	@Size(min = 0, max = 45, message = "Remarks should not be greater than 45 characters.")
	public String remarksReason;
	
	@Validate(order = 17)
	@Size(min = 0, max = 45, message = "Certificate Number should not be greater than 45 characters.")
	public String certificateNumber;
	
	@Validate(order = 18)
	public String errorDescription;
	
	@Validate(order = 19)
	public String warningDescription;
	
	@Validate(order = 20)
	public Double shortDeduction;
	
	@Validate(order = 21)
	public Double interestOnShortDeduction;
	
	@Validate(order = 22)
	public Double interestOnLatePayment;
	
	@Validate(order = 23)
	public Double interestOnLateDeduction;
	
	@Validate(order = 24)
	public Long branchCode;
	
	@Validate(order = 25)
	public String custVendId;
	
	@Validate(order = 26)
	public String challanHeading;
	
	@Validate(order = 27)
	public String accNo;

	@Validate(order = 28)
	public String uniqueRefNo;

	@Validate(order = 29)
	public String roCode;

	@Validate(order = 30)
	public String TAN;
	
	@Validate(order = 31)
	public String comments;

	@Validate(order = 32)
	public Long tranAmt;

	@Validate(order = 33)
	public String additionalDetail;
	
	@Validate(order = 34)
	public String month;
	
	@Validate(order = 35)
	public boolean resolved;

	@Validate(order = 36)
	@Size(min = 0, max = 45, message = "FY should not be greater than 45 characters.")
	public String fy;
	
	@Validate(order = 37)
	@Size(min = 0, max = 45, message = "Quarter should not be greater than 45 characters.")
	public String quarter;

	/* 3 new columns */
	@Validate(order = 38)
	private String validInvalidPan;
	
	@Validate(order = 39)
	private String panStatus;
	
	@Validate(order = 40)
	private String specifiedPerson;
	
	public ChallanDetails challanDetails;

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

	public void updateAllowedFields(Regular24QDeductee entity) {
		this.resolved = entity.isResolved();
		this.pan = entity.getPan();
		this.sectionCode = entity.getSectionCode();
		this.dateOfPayment = entity.getDateOfPayment();
		this.dateOfDeduction = entity.getDateOfDeduction();
		this.amountPaid = entity.getAmountPaid();
		this.tds = entity.getTds();
		this.surcharge = entity.getSurcharge();
		this.eduCess = entity.getEduCess();
		this.totalTaxDeducted = entity.getTotalTaxDeducted();
		this.totalTaxDeposited = entity.getTotalTaxDeposited();
		this.certificateNumber = entity.getCertificateNumber();
		this.shortDeduction = entity.getShortDeduction();
		this.interestOnShortDeduction = entity.getInterestOnShortDeduction();
		this.interestOnLateDeduction = entity.getInterestOnLateDeduction();
		this.interestOnLatePayment = entity.getInterestOnLatePayment();
	}
	
	
	public void setData(JSONObject dataObject) throws ParseException{
	    this.challanHeading = dataObject.get("CHALLANHEADING").toString();
	    this.deducteeRefNo = dataObject.get("DEDUCTEEREFNO").toString();
	    this.panRefNo = dataObject.get("PANREFNO").toString();
	    this.pan = dataObject.get("PAN").toString();
	    this.name = dataObject.get("NAME").toString();
	    this.sectionCode = dataObject.get("SECTIONCODE").toString();
	    this.amountPaid = Double.valueOf(dataObject.get("AMOUNTPAID").toString());
	    this.tds = Double.valueOf(dataObject.get("TDS").toString());
	    this.surcharge = Double.valueOf(dataObject.get("SURCHARGE").toString());
	    this.eduCess = Double.valueOf(dataObject.get("EDUCESS").toString());
	    this.totalTaxDeducted = Double.valueOf(dataObject.get("TOTALTAXDEDUCTED").toString());
	    this.totalTaxDeposited = Double.valueOf(dataObject.get("TOTALTAXDEPOSITED").toString());
	    this.certificateNumber = dataObject.get("CERTIFICATENUMBER").toString();
	    this.remarksReason = dataObject.get("REMARKSREASON").toString();
	    this.fy = dataObject.get("FY").toString();
	    this.quarter = dataObject.get("QUARTER").toString();
	    this.branchCode = Long.valueOf(dataObject.get("BRANCHCODE").toString());
	    this.accNo = dataObject.get("ACCNO").toString();
	    this.challanSrNo = Long.valueOf(dataObject.get("CHALLANSRNO").toString());
	    this.month = dataObject.get("MONTH").toString();
	    this.custVendId = dataObject.get("CUSTVENDID").toString();
	    this.uniqueRefNo = dataObject.get("UNIQUEREFNO").toString();
	    this.TAN = dataObject.get("TAN").toString();
	    this.roCode = dataObject.get("ROCODE").toString();
	    this.errorDescription = dataObject.get("ERRORDESCRIPTION").toString();
	    this.warningDescription = dataObject.get("WARNINGDESCRIPTION").toString();
	    this.shortDeduction = Double.valueOf(dataObject.get("SHORTDEDUCTION").toString());
	    this.interestOnShortDeduction = Double.valueOf(dataObject.get("INTERESTONSHORTDEDUCTION").toString());
	    this.interestOnLatePayment = Double.valueOf(dataObject.get("INTERESTONLATEPAYMENT").toString());
	    this.interestOnLateDeduction = Double.valueOf(dataObject.get("INTERESTONLATEDEDUCTION").toString());
	    this.resolved = Boolean.valueOf(dataObject.get("RESOLVED").toString());
	    this.comments = dataObject.get("COMMENTS").toString();
	    this.deducteeSrNo = Long.valueOf(dataObject.get("DEDUCTEESRNO").toString());
	    this.tranAmt = Long.valueOf(dataObject.get("TRANAMT").toString());
	    this.additionalDetail = dataObject.get("ADDITIONALDETAIL").toString();
	    
	    this.dateOfPayment = returnDate(dataObject.get("DATEOFPAYMENT").toString());
	    this.dateOfDeduction = returnDate(dataObject.get("DATEOFDEDUCTION").toString());
	}

}
