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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.in.rjsa.model.form.CommonModelAbstract;
import domain.in.rjsa.util.JsonDateSerializer;
import lombok.Data;

@Data
//@Entity
//@Table(name = "AAACN4165C_2324.regular24QDeductee")
public class Regular24QDeductee extends CommonModelAbstract {
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
	//@Column(name = "panRefNo")
//	@NotNull(message = "Pan Reference No. is a required field.")
	@Size(min = 0, max = 45, message = "Pan Reference No. should not be greater than 45 characters.")
	public String panRefNo;
	//@Column(name = "pan")
//	@NotNull(message = "Pan is a required field.")
//	@Size(min = 0, max = 45, message = "Pan should not be greater than 10 characters.")
	public String pan;
	//@Column(name = "name")
//	@NotNull(message = "Name is a required field.")
	@Size(min = 0, max = 45, message = "Name should not be greater than 45 characters.")
	public String name;
	//@Column(name = "sectionCode")
//	@NotNull(message = "Section Code is a required field.")
	@Size(min = 0, max = 45, message = "Section Code should not be greater than 45 characters.")
	public String sectionCode;
	//@Temporal(TemporalType.DATE)
	//@Column(name = "dateOfPayment")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
//	@NotNull(message = "Date is a required field.")
	public Date dateOfPayment;
	//@Temporal(TemporalType.DATE)
	//@Column(name = "dateOfDeduction")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
//	@NotNull(message = "Date is a required field.")
	public Date dateOfDeduction;
	//@Column(name = "amountPaid")
//	@NotNull(message = "Amount Paid is a required field.")
	//@Digits(integer = 12, fraction = 2, message = "Amount Paid is not proper.Maximum 2 fractions allowed.")
	public Double amountPaid;
	//@Column(name = "tds")
//	@NotNull(message = "Tds is a required field.")
	//@Digits(integer = 12, fraction = 2, message = "Tds is not proper.Maximum 2 fractions allowed.Maximum 2 fractions allowed.")
	public Double tds;
	//@Column(name = "surcharge")
//	@NotNull(message = "Surcharge is a required field.")
	//@Digits(integer = 12, fraction = 2, message = "Surcharge is not proper.Maximum 2 fractions allowed.")
	public Double surcharge;
	//@Column(name = "eduCess")
//	@NotNull(message = "Education Cess is a required field.")
	//@Digits(integer = 12, fraction = 2, message = "Education Cess is not proper.Maximum 2 fractions allowed.")
	public Double eduCess;
	//@Column(name = "totalTaxDeducted")
//	@NotNull(message = "Total Tds is a required field.")
	//@Digits(integer = 12, fraction = 2, message = "Education Cess is not proper.Maximum 2 fractions allowed.")
	public Double totalTaxDeducted;
	//@Column(name = "totalTaxDeposited")
//	@NotNull(message = "Total Tax Deposited is a required field.")
	//@Digits(integer = 12, fraction = 2, message = "Total Tax Deposited value is not proper.Maximum 2 fractions allowed.")
	public Double totalTaxDeposited;
	//@Column(name = "certificateNumber")
//	@NotNull(message = "Certificate Number is a required field.")
	@Size(min = 0, max = 45, message = "Certificate Number should not be greater than 45 characters.")
	public String certificateNumber;
	//@Column(name = "remarksReason")
	@Size(min = 0, max = 45, message = "Remarks should not be greater than 45 characters.")
	public String remarksReason;
	//@Column(name = "fy")
//	@NotNull(message = "FY is a required field.")
	@Size(min = 0, max = 45, message = "FY should not be greater than 45 characters.")
	public String fy;
	//@Column(name = "quarter")
//	@NotNull(message = "Quarter is a required field.")
	@Size(min = 0, max = 45, message = "Quarter should not be greater than 45 characters.")
	public String quarter;
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

	//@Column(name = "deducteeSrNo")
	public Long deducteeSrNo;

	//@Column(name = "tranAmt")
	public Long tranAmt;

	//@Column(name = "additionalDetail")
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
