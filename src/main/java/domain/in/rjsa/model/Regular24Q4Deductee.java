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
@Table(name = "regular24Q4Deductee")
public class Regular24Q4Deductee {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;	
	@Column(name = "srNo")
	public Long srNo;	
	@Column(name = "challanSrNo")
	public Long challanSrNo;
	@Column(name = "employeeRefNo")
	public String employeeRefNo;
	@Column(name = "panRefNo")
	public String panRefNo;
	@Column(name = "pan")
	public String pan;
	@Column(name = "name")
	public String name;
	@Column(name = "sectionCode")
	public String sectionCode;
	@Column(name = "dateOfPayment")
	public Date dateOfPayment;
	@Column(name = "dateOfDeduction")
	public Date dateOfDeduction;
	@Column(name = "amountPaid")
	public Double amountPaid;
	@Column(name = "tds")
	public Double tds;
	@Column(name = "surcharge")
	public Double surcharge;
	@Column(name = "educationCess")
	public Double educationCess;
	@Column(name = "totalTds")
	public String totalTds;
	@Column(name = "totalTaxDeposited")
	public Double totalTaxDeposited;
	@Column(name = "remarks")
	public String remarks;
	@Column(name = "certificateNumber")
	public String certificateNumber;
	@Column(name = "errorDescription")
	public String errorDescription;
	@Column(name = "warningDescription")
	public String warningDescription;
	@Column(name = "shortDeduction")
	public Double shortDeduction;
	@Column(name = "interestOnShortDeduction")
	public Double interestOnShortDeduction;
	@Column(name = "interestOnLatePayment")
	public Double interestOnLatePayment;
	@Column(name = "interestOnLateDeduction")
	public Double interestOnLateDeduction;
}
