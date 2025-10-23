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
public class Regular27EQDeductee extends CommonModelAbstract {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Validate(order = 49)
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
	public Double amountPaid;

	@Validate(order = 8)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date dateOfPayment;

	@Validate(order = 9)
	public Double tds;
	
	@Validate(order = 10)
	public Double surcharge;
	
	@Validate(order = 11)
	public Double eduCess;
	
	@Validate(order = 12)
	public Double totalTaxDeducted;
	
	@Validate(order = 13)
	public Double totalTaxDeposited;
	
	@Validate(order = 14)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date dateOfDeduction;
	
	@Validate(order = 15)
	public Double totalValueofPurchase;
	
	@Validate(order = 16)
	public Double rateatwhichTaxCollected;
	
	@Validate(order = 17)
	public String remarksReason;
	
	@Validate(order = 18)
	public String sectionCode;
	
	@Validate(order = 19)
	public String certificateNumber;
	
	@Validate(order = 20)
	public String deducteeisNonResident;
	
	@Validate(order = 21)
	public String permanentEstablishment;
	
	@Validate(order = 22)
	public String reasonForNonCollectionForG;
	
	@Validate(order = 23)
	public Long ifAnswerTo681AisyesthenChallanNumber;
	
	@Validate(order = 24)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date ifAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment;
	
	@Validate(order = 26)
	public String errorDescription;
	
	@Validate(order = 27)
	public String warningDescription;
	
	@Validate(order = 28)
	public Double shortDeduction;
	
	@Validate(order = 29)
	public Double interestOnShortDeduction;
	
	@Validate(order = 30)
	public Double interestOnLatePayment;
	
	@Validate(order = 31)
	public Double interestOnLateDeduction;
	
	@Validate(order = 32)
	public Long branchCode;
	
	@Validate(order = 33)
	public String custVendId;
	
	@Validate(order = 34)
	public String challanHeading;
	
	@Validate(order = 35)
	public String accNo;
	
	@Validate(order = 36)
	public String uniqueRefNo;
	
	@Validate(order = 37)
	public String roCode;
	
	@Validate(order = 38)
	public String TAN;
	
	@Validate(order = 39)
	public String comments;
	
	@Validate(order = 40)
	public Long tranAmt;
	
	@Validate(order = 41)
	public String additionalDetail;
	
	@Validate(order = 42)
	public String month;
	
	@Validate(order = 43)
	public boolean resolved;
	
	@Validate(order = 44)
	public String fy;
	
	@Validate(order = 45)
	public String quarter;
	
	/* 4 new columns */
	@Validate(order = 25)
	private String whetherCollecteeOptRegime;

	@Validate(order = 46)
	public String validInvalidPan;

	@Validate(order = 47)
	public String panStatus;

	@Validate(order = 48)
	public String specifiedPerson;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDateofCollected() {
		return dateOfDeduction;
	}

	public void setDateOfDeduction(Date dateOfDeduction) {
		this.dateOfDeduction = dateOfDeduction;
	}

	public void updateAllowedFields(Regular27EQDeductee regular27eqWeb) {
		this.resolved = regular27eqWeb.isResolved();
		this.pan = regular27eqWeb.getPan();
		this.name = regular27eqWeb.getName();
		this.amountPaid = regular27eqWeb.getAmountPaid();
		this.dateOfPayment = regular27eqWeb.getDateOfPayment();
		this.tds = regular27eqWeb.getTds();
		this.surcharge = regular27eqWeb.getSurcharge();
		this.eduCess = regular27eqWeb.getEduCess();
		this.totalTaxDeducted = regular27eqWeb.getTotalTaxDeducted();
		this.totalTaxDeposited = regular27eqWeb.getTotalTaxDeposited();
		this.dateOfDeduction = regular27eqWeb.getDateOfDeduction();
		this.totalValueofPurchase = regular27eqWeb.getTotalValueofPurchase();
		this.rateatwhichTaxCollected = regular27eqWeb.getRateatwhichTaxCollected();
		this.remarksReason = regular27eqWeb.getRemarksReason();
		this.sectionCode = regular27eqWeb.getSectionCode();
		this.certificateNumber = regular27eqWeb.getCertificateNumber();
		this.deducteeisNonResident = regular27eqWeb.getDeducteeisNonResident();
		this.permanentEstablishment = regular27eqWeb.getPermanentEstablishment();
		this.reasonForNonCollectionForG = regular27eqWeb.getReasonForNonCollectionForG();
		this.ifAnswerTo681AisyesthenChallanNumber = regular27eqWeb.getIfAnswerTo681AisyesthenChallanNumber();
		this.ifAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment = regular27eqWeb
				.getIfAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment();
		this.shortDeduction = regular27eqWeb.getShortDeduction();
		this.interestOnShortDeduction = regular27eqWeb.getInterestOnShortDeduction();
		this.interestOnLatePayment = regular27eqWeb.getInterestOnLatePayment();
		this.interestOnLateDeduction = regular27eqWeb.getInterestOnLateDeduction();
	}

	
	public void setData(JSONObject dataObject) throws ParseException{
		this.deducteeRefNo = dataObject.get("DEDUCTEEREFNO").toString().toUpperCase();
		this.deducteeCode = dataObject.get("DEDUCTEECODE").toString().toUpperCase();
		this.pan = dataObject.get("PAN").toString().toUpperCase();
		this.name = dataObject.get("NAME").toString().toUpperCase();
		this.amountPaid = Double.valueOf(dataObject.get("AMOUNTPAID").toString());
		this.tds = Double.valueOf(dataObject.get("TDS").toString());
		this.surcharge = Double.valueOf(dataObject.get("SURCHARGE").toString());
		this.quarter = dataObject.get("QUARTER").toString().toUpperCase();
		this.fy = dataObject.get("FY").toString().toUpperCase();
		this.eduCess = Double.valueOf(dataObject.get("EDUCESS").toString());
		this.totalTaxDeducted = Double.valueOf(dataObject.get("TOTALTAXDEDUCTED").toString());
		this.totalTaxDeposited = Double.valueOf(dataObject.get("TOTALTAXDEPOSITED").toString());
		this.totalValueofPurchase = Double.valueOf(dataObject.get("TOTALVALUEOFPURCHASE").toString());
		this.rateatwhichTaxCollected = Double.valueOf(dataObject.get("RATEATWHICHTAXCOLLECTED").toString());
		this.remarksReason = dataObject.get("REMARKSREASON").toString().toUpperCase();
		this.certificateNumber = dataObject.get("CERTIFICATENUMBER").toString().toUpperCase();
		this.deducteeisNonResident = dataObject.get("DEDUCTEEISNONRESIDENT").toString().toUpperCase();
		this.permanentEstablishment = dataObject.get("PERMANENTESTABLISHMENT").toString().toUpperCase();
		this.branchCode = Long.valueOf(dataObject.get("BRANCHCODE").toString());
		this.accNo = dataObject.get("ACCNO").toString().toUpperCase();
		this.challanSrNo = Long.valueOf(dataObject.get("CHALLANSRNO").toString());
		this.month = dataObject.get("MONTH").toString().toUpperCase();
		this.challanHeading = dataObject.get("CHALLANHEADING").toString().toUpperCase();
		this.custVendId = dataObject.get("CUSTVENDID").toString().toUpperCase();
		this.uniqueRefNo = dataObject.get("UNIQUEREFNO").toString().toUpperCase();
		this.reasonForNonCollectionForG = dataObject.get("REASONFORNOCOLLECTIONFORG").toString().toUpperCase();
		this.ifAnswerTo681AisyesthenChallanNumber = Long
				.valueOf(dataObject.get("IFANSWERT0681AISYESTHENCHALLANNUMBER").toString());
		this.TAN = dataObject.get("TAN").toString().toUpperCase();
		this.roCode = dataObject.get("ROCODE").toString().toUpperCase();
		this.sectionCode = dataObject.get("SECTIONCODE").toString().toUpperCase();
		this.errorDescription = dataObject.get("ERRORDESCRIPTION").toString().toUpperCase();
		this.warningDescription = dataObject.get("WARNINGDESCRIPTION").toString().toUpperCase();
		this.shortDeduction = Double.valueOf(dataObject.get("SHORTDEDUCTION").toString());
		this.interestOnShortDeduction = Double.valueOf(dataObject.get("INTERESTONSHORTDEDUCTION").toString());
		this.interestOnLatePayment = Double.valueOf(dataObject.get("INTERESTONLATEPAYMENT").toString());
		this.interestOnLateDeduction = Double.valueOf(dataObject.get("INTERESTONLATEDEDUCTION").toString());
		this.resolved = Boolean.parseBoolean(dataObject.get("RESOLVED").toString());
		this.comments = dataObject.get("COMMENTS").toString().toUpperCase();
		this.deducteeSrNo = Long.valueOf(dataObject.get("DEDUCTEESRNO").toString());
		this.tranAmt = Long.valueOf(dataObject.get("TRANAMT").toString());
		this.additionalDetail = dataObject.get("ADDITIONALDETAIL").toString().toUpperCase();

		this.dateOfPayment = returnDate(dataObject.get("DATEOFPAYMENT").toString());
		this.dateOfDeduction = returnDate(dataObject.get("DATEOFDEDUCTION").toString());
		this.ifAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment = returnDate(
					dataObject.get("IFANSWERT0681AISYESTHENDATEOFPAYMENTOFTDSTOCENTRALGOVERNMENT").toString());
	}

}
