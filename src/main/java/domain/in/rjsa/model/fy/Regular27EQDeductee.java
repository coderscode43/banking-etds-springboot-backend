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

import domain.in.rjsa.model.form.CommonModelAbstract;
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
    @Column(name = "partyReferenceNo")
	public Long partyReferenceNo;
    @Column(name = "partyCode")
  	public Long partyCode;
    @Column(name = "panoftheParty")
	public String panoftheParty;
    @Column(name = "nameoftheParty")
	public String nameoftheParty;
    @Column(name = "amountReceiptDebited")
   	public Long amountReceiptDebited;
    @Temporal(TemporalType.DATE)
    @Column(name = "dateofReceivedDebited")
	public Date dateofReceivedDebited;
    @Column(name = "tcs")
   	public String tcs;
    @Column(name = "surcharge")
   	public String surcharge;
    @Column(name = "quarter")
   	public String quarter;
    @Column(name = "fy")
   	public Long fy;
    @Column(name = "educationCess")
   	public String educationCess;
    @Column(name = "totalTaxCollected")
   	public Long totalTaxCollected;
    @Column(name = "totalTaxDeposited")
   	public Long totalTaxDeposited;
    @Temporal(TemporalType.DATE)
    @Column(name = "dateofCollected")
   	public Date dateofCollected;
    @Column(name = "totalValueofPurchase")
   	public Long totalValueofPurchase;
    @Column(name = "rateatwhichTaxCollected")
   	public Long rateatwhichTaxCollected;
    @Column(name = "reasonforNonCollection")
   	public String reasonforNonCollection;
    @Column(name = "sectionCollectionCode")
   	public Long sectionCollectionCode;
    @Column(name = "certificatenumber")
   	public Long certificatenumber;
    @Column(name = "deducteeisNonResident")
   	public String deducteeisNonResident;
    @Column(name = "permanentEstablishment")
    public String permanentEstablishment;
    @Column(name = "branchCode")
	public Long branchCode;
	@Column(name = "accNo")
	public Long accNo;
	@Column(name = "idNo")
	public Long idNo;
	@Column(name = "type")
	public String type;
	@Column(name = "month")
	public String month;
	@Column(name = "deducteeId")
	public Long deducteeId;
	@Column(name = "challanHeading")
	public String challanHeading;
	@Column(name = "customerId")
	public Long customerId;
	@Column(name = "uniqueRefNo")
	public Long uniqueRefNo;
	@Column(name = "reasonForNonCollectionForG")
	public String reasonForNonCollectionForG;
	@Column(name = "ifAnswerTo681AisyesthenChallanNumber")
	public String ifAnswerTo681AisyesthenChallanNumber;
	@Column(name = "ifAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment")
	public String ifAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment;
	@Column(name = "TAN")
	public String TAN ;
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
}
