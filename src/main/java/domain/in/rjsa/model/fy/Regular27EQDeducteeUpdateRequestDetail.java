package domain.in.rjsa.model.fy;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
@Entity
@Table(name = "FYDetails.regular27EQDeducteeUpdateRequestDetail")
public class Regular27EQDeducteeUpdateRequestDetail extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "branchId")
	public Long branchId;
	@Column(name = "srNoInChallan")
	public Long srNoInChallan;
   @Column(name = "PartyReferenceNo")
	public Long PartyReferenceNo;
    @Column(name = "partyCode")
  	public Long partyCode;
    @Column(name = "panoftheParty")
	public String panoftheParty;
    @Column(name = "nameoftheParty")
	public String nameoftheParty;
    @Column(name = "amountReceiptDebited")
   	public Long amountReceiptDebited;
    @Column(name = "dateofReceivedDebited")
	public Date dateofReceivedDebited;
    @Column(name = "tcs")
   	public String tcs;
    @Column(name = "surcharge")
    public String surcharge;
    @Column(name = "quarter")
    public String quarter;
    @Column(name = "fy")
    public String fy;
    @Column(name = "educationCess")
   	public String educationCess;
    @Column(name = "totalTaxCollected")
    public Long totalTaxCollected;
    @Column(name = "totalTaxDeposited")
   	public Long totalTaxDeposited;
    @Column(name = "dateofCollected")
   	public Date dateofCollected;
    @Column(name = "totalValueofPurchase")
   	public Long totalValueofPurchase;
    @Column(name = "rateatwhichTaxCollected")
   	public Long rateatwhichTaxCollected;
    @Column(name = "reasonforNonCollection")
   	public String reasonforNonCollection;
    @Column(name = "sectionCollectionCode")
   	public String sectionCollectionCode;
    @Column(name = "certificatenumber")
   	public Long certificatenumber;
    @Column(name = "deducteeisNonResident")
   	public String deducteeisNonResident;
    @Column(name = "permanentEstablishment")
   	public String permanentEstablishment;
    @Column(name = "errorDescription")
   	public String errorDescription;
    @Column(name = "warningDescription")
   	public String warningDescription;
    @Column(name = "shortDeduction")
   	public String shortDeduction;
    @Column(name = "interestonShortDeduction")
   	public Long interestonShortDeduction;
    @Column(name = "interestonLatePayment")
   	public Long interestonLatePayment;
    @Column(name = "interestonLateDeduction")
   	public Long interestonLateDeduction;
    @Column(name = "remarks")
   	public String remarks;
    @Column(name = "status")
   	public String status;
    
	
}
