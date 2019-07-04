package domain.in.rjsa.model.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "regular27QChallan")
public class Regular27QChallan extends CommonModelAbstract{
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
	@Size(min=0, max=45, message="tdsDepositBookEntry should not be greater than 45 characters.")
	public String tdsDepositBookEntry;
	
	
	@Column(name = "bankBranchCode")
	public String bankBranchCode;
	@Column(name = "challanSrNo")
	public Long challanSrNo;
	@Temporal(TemporalType.DATE)
	@Column(name = "taxDepositDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date taxDepositDate;
	
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
	@Column(name = "quarter")
	public String quarter;
}
