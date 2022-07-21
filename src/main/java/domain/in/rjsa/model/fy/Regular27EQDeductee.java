package domain.in.rjsa.model.fy;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.in.rjsa.model.form.CommonModelAbstract;
import domain.in.rjsa.util.JsonDateSerializer;
import lombok.Data;
@Data
@Entity
@Table(name = "FYDetails.regular27EQDeductee")
public class Regular27EQDeductee extends CommonModelAbstract {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;
    @Column(name = "deducteeReferenceNo")
	public Long deducteeReferenceNo;
    @Column(name = "deducteeCode")
  	public String deducteeCode;
    @Column(name = "panofthedeductee")
	public String panofthedeductee;
    @Column(name = "nameoftheDeductee")
	public String nameoftheDeductee;
    @Column(name = "amountReceiptDebited")
   	public Double amountReceiptDebited;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dateofReceivedDebited")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date dateofReceivedDebited;
    
    @Column(name = "tcs")
   	public Double tcs;
    @Column(name = "surcharge")
   	public Double surcharge;
    @Column(name = "quarter")
   	public String quarter;
    @Column(name = "fy")
   	public String fy;
    @Column(name = "educationCess")
   	public Double educationCess;
    @Column(name = "totalTaxCollected")
   	public Double totalTaxCollected;
    @Column(name = "totalTaxDeposited")
   	public Double totalTaxDeposited;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dateofCollected")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
   	public Date dateofCollected;
    
    @Column(name = "totalValueofPurchase")
   	public Double totalValueofPurchase;
    @Column(name = "rateatwhichTaxCollected")
   	public Double rateatwhichTaxCollected;
    @Column(name = "reasonforNonCollection")
   	public String reasonforNonCollection;
    @Column(name = "certificatenumber")
   	public String certificatenumber;
    @Column(name = "deducteeisNonResident")
   	public String deducteeisNonResident;
    @Column(name = "permanentEstablishment")
    public String permanentEstablishment;
    @Column(name = "branchCode")
	public Long branchCode;
	@Column(name = "accNo")
	public String accNo;
	@Column(name = "idNo")
	public Long idNo;
	@Column(name = "month")
	public String month;
	@Column(name = "deducteeId")
	public Long deducteeId;
	@Column(name = "challanHeading")
	public String challanHeading;
	@Column(name = "custVendId")
	public String custVendId;
	@Column(name = "uniqueRefNo")
	public String uniqueRefNo;
	@Column(name = "reasonForNonCollectionForG")
	public String reasonForNonCollectionForG;
	@Column(name = "ifAnswerTo681AisyesthenChallanNumber")
	public Long ifAnswerTo681AisyesthenChallanNumber;
	@Temporal(TemporalType.DATE)
	@Column(name = "ifAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date ifAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment;
	@Column(name = "TAN")
	public String TAN ;
	@Column(name = "roCode")
	public Long roCode ;
	@Column(name = "collectionCode")
	public String collectionCode ;
	@Column(name = "errorDescription")
	public String errorDescription ;
	@Column(name = "warningDescription")
	public String warningDescription ;
	@Column(name = "ShortDeduction")
	public Double ShortDeduction;
	@Column(name = "InterestOnShortDeduction")
	public Double InterestOnShortDeduction ;
	@Column(name = "InterestOnLatePayment")
	public Double InterestOnLatePayment;
	@Column(name = "InterestOnLateDeduction")
	public Double InterestOnLateDeduction;
	
	@Column(name = "resolved")
	public boolean resolved;
	
	@Column(name = "comments")
	public String comments;
	
	
	/*
	 * @Column(name = "verify") public boolean verify = false;
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getdateofReceivedDebited() {
		return dateofReceivedDebited;
	}

	public void setdateofReceivedDebited(Date dateofReceivedDebited) {
		this.dateofReceivedDebited = dateofReceivedDebited;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getdateofCollected() {
		return dateofCollected;
	}

	public void setdateofCollected(Date dateofCollected) {
		this.dateofCollected = dateofCollected;
	}

	public void updateAllowedFields(Regular27EQDeductee regular27eqWeb) {
		// TODO Auto-generated method stub
//		this.accNo=regular27eqWeb.getAccNo();
//		this.amountReceiptDebited=regular27eqWeb.getAmountReceiptDebited();
		this.branchCode=regular27eqWeb.getBranchCode();
//		this.certificatenumber=regular27eqWeb.getCertificatenumber();
//		this.challanHeading=regular27eqWeb.getChallanHeading();
//		this.collectionCode=regular27eqWeb.getCollectionCode();
//		this.comments=regular27eqWeb.getComments();
//		this.dateofCollected=regular27eqWeb.getdateofCollected();
//		this.dateofReceivedDebited=regular27eqWeb.getdateofReceivedDebited();
//		this.deducteeCode=regular27eqWeb.getDeducteeCode();
//		this.deducteeId=regular27eqWeb.getDeducteeId();
//		this.deducteeisNonResident=regular27eqWeb.getDeducteeisNonResident();
//		this.deducteeReferenceNo=regular27eqWeb.getDeducteeReferenceNo();
//		this.educationCess=regular27eqWeb.getEducationCess();
//		this.errorDescription=regular27eqWeb.getErrorDescription();
//		this.fy=regular27eqWeb.getFy();
//		this.idNo=regular27eqWeb.getIdNo();
//		this.ifAnswerTo681AisyesthenChallanNumber=regular27eqWeb.getIfAnswerTo681AisyesthenChallanNumber();
//		this.ifAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment=regular27eqWeb.getIfAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment();
//		this.InterestOnLateDeduction=regular27eqWeb.getInterestOnLateDeduction();
//		this.InterestOnLatePayment=regular27eqWeb.getInterestOnLatePayment();
//		this.InterestOnShortDeduction=regular27eqWeb.getInterestOnShortDeduction();
//		this.month=regular27eqWeb.getMonth();
//		this.nameoftheDeductee=regular27eqWeb.getNameoftheDeductee();
//		this.panofthedeductee=regular27eqWeb.getPanofthedeductee();
//		this.permanentEstablishment=regular27eqWeb.getPermanentEstablishment();
//		this.quarter=regular27eqWeb.getQuarter();
//		this.rateatwhichTaxCollected=regular27eqWeb.getRateatwhichTaxCollected();
//		this.reasonForNonCollectionForG=regular27eqWeb.getReasonForNonCollectionForG();
		this.roCode=regular27eqWeb.getRoCode();
//		this.ShortDeduction=regular27eqWeb.getShortDeduction();
//		this.surcharge=regular27eqWeb.getSurcharge();
//		this.TAN=regular27eqWeb.getTAN();
//		this.tcs=regular27eqWeb.getTcs();
//		this.totalTaxCollected=regular27eqWeb.getTotalTaxCollected();
//		this.totalTaxDeposited=regular27eqWeb.getTotalTaxDeposited();
//		this.totalValueofPurchase=regular27eqWeb.getTotalValueofPurchase();
//		this.uniqueRefNo=regular27eqWeb.getUniqueRefNo();
//		this.warningDescription=regular27eqWeb.getWarningDescription();	
		
	}
	
	
}

