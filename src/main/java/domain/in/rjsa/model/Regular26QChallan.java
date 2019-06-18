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
@Table(name = "regular26QChallan")
public class Regular26QChallan {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;	
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "srNo")
	public Long srNo;	
	@Column(name = "tds")
	public Double tds;
	@Column(name = "surcharge")
	public Double surcharge;
	@Column(name = "eduCess")
	public Double eduCess;
	@Column(name = "interest")
	public Double interest;
	@Column(name = "fee")
	public Double fee;	
	@Column(name = "others")
	public Double others;
	@Column(name = "totalTaxDeposit")
	public Double totalTaxDeposit;
	@Column(name = "tdsDepositBookEntry")
	public String tdsDepositBookEntry;
	@Column(name = "bsrCode")
	public String bsrCode;
	@Column(name = "challanSrNo")
	public Long challanSrNo;
	@Column(name = "dateTaxDeposit")
	public Date dateTaxDeposit;
	@Column(name = "minorHeadChallan")
	public String minorHeadChallan;
	@Column(name = "interestAllocated")
	public Double interestAllocated;
	@Column(name = "otherAmtAllocated")
	public Double otherAmtAllocated;
	
	@Column(name = "nilChallanIndicator")
	public String nilChallanIndicator;
	
	@Column(name = "warningDescription")
	public String warningDescription;
	
	@Column(name = "shortPayment")
	public Double shortPayment;
	@Column(name = "interestShortPay")
	public Double interestShortPay;
	@Column(name = "fy")
	public String fy;
}
