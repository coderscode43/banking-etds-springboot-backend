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
  	public Long deducteeCode;
    @Column(name = "panofthedeductee")
	public String panofthedeductee;
    @Column(name = "nameoftheDeductee")
	public String nameoftheDeductee;
    @Column(name = "amountReceiptDebited")
   	public Double amountReceiptDebited;
    @Temporal(TemporalType.DATE)
    @Column(name = "dateofReceivedDebited")
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
	public String branchCode;
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
	public Date ifAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment;
	@Column(name = "TAN")
	public String TAN ;
	@Column(name = "roCode")
	public String roCode ;
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
	
	
}

