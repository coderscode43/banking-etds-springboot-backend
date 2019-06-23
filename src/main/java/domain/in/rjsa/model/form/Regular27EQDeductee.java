package domain.in.rjsa.model.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "regular27EQDeductee")
public class Regular27EQDeductee {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;	
	@Column(name = "srNo")
	public Long srNo;
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "challanSrNo")
	public Long challanSrNo;	
	@Column(name = "partyRefNo")
	public Long partyRefNo;	
	@Column(name = "partyCode")
	public String partyCode;	
	@Column(name = "partyPan")
	public String partyPan;	
	@Column(name = "partyName")
	public String partyName;	
	@Column(name = "amtDebited")
	public Double amtDebited;	
	@Column(name = "debitedDate")
	public Date debitedDate;	
	@Column(name = "tcs")
	public Double tcs;	
	@Column(name = "surcharge")
	public Double surcharge;	
	@Column(name = "eduCess")
	public Double eduCess;	
	@Column(name = "totalTaxCollect")
	public Double totalTaxCollect;	
	@Column(name = "totalTaxDeposit")
	public Double totalTaxDeposit;	
	@Column(name = "collectedDate")
	public Double collectedDate;	
	@Column(name = "totValuePurchase")
	public Double totValuePurchase;	
	@Column(name = "rateTaxCollected")
	public Double rateTaxCollected;	
	@Column(name = "nonCollectReason")
	public String nonCollectReason;	
	@Column(name = "sectionCode")
	public String sectionCode;	

	@Column(name = "certificateNo")
	public String certificateNo;
	@Column(name = "nonResidentDeductee")
	public String nonResidentDeductee;
	@Column(name = "permanentEst")
	public String permanentEst;
	
	@Column(name = "warningDescription")
	public String warningDescription;
	@Column(name = "shortDeduct")
	public Double shortDeduct;
	
	@Column(name = "interestOnShtDeduct")
	public Double interestOnShtDeduct;
	
	@Column(name = "interestOnLatePay")
	public Double interestOnLatePay;
	@Column(name = "interestOnLateDeduct")
	public Double interestOnLateDeduct;
	@Column(name = "fy")
	public String fy;
	@Column(name = "quarter")
	public String quarter;
	
}
