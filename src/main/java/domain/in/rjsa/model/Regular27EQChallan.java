package domain.in.rjsa.model;

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
@Table(name = "regular27EQChallan")
public class Regular27EQChallan {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;	
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "srNo")
	public Long srNo;	
	@Column(name = "challanSrNo")
	public Long challanSrNo;
	@Column(name = "deducteeRefNo")
	public String deducteeRefNo;
	@Column(name = "deducteeCode")
	public String deducteeCode;
	@Column(name = "deducteePan")
	public String deducteePan;
	@Column(name = "deducteeName")
	public String deducteeName;
	@Column(name = "sectionCode")
	public String sectionCode;
	@Column(name = "paymentDate")
	public Date paymentDate;
	@Column(name = "paidAmt")
	public Double paidAmt;
	@Column(name = "tds")
	public Double tds;
	@Column(name = "surcharge")
	public Double surcharge;
	@Column(name = "eduCess")
	public Double eduCess;
	@Column(name = "totalTaxDeduct")
	public Double totalTaxDeduct;
	@Column(name = "totalTaxDeposit")
	public Double totalTaxDeposit;
	@Column(name = "deductDate")
	public Date deductDate;
	@Column(name = "rateTaxDeduct")
	public Double rateTaxDeduct;
	@Column(name = "remarks")
	public String remarks;
	@Column(name = "certificateNo")
	public String certificateNo;	
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
