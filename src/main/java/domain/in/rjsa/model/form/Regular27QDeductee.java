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
@Table(name = "regular27QDeductee")
public class Regular27QDeductee {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;	
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "deducteeSrNo")
	public Long deducteeSrNo;	
	@Column(name = "srNoInChallan")
	public Long srNoInChallan;	
	@Column(name = "deducteeRefNo")
	public Long deducteeRefNo;	
	@Column(name = "deducteeCode")
	public String deducteeCode;	
	@Column(name = "pan")
	public String pan;		
	@Column(name = "name")
	public String name;	
	@Column(name = "sectionCode")
	public String sectionCode;	
	@Column(name = "dateOfPayment")
	public Date dateOfPayment;	
	@Column(name = "amountPaid")
	public Double amountPaid;	
	@Column(name = "tds")
	public Double tds;		
	@Column(name = "surcharge")
	public Double surcharge;
	@Column(name = "educationCess")
	public Double educationCess;
	@Column(name = "totalTaxDeducted")
	public Double totalTaxDeducted;
	@Column(name = "totalTaxDeposited")
	public Double totalTaxDeposited;
	@Column(name = "dateOfDeduction")
	public Date dateOfDeduction;
	@Column(name = "rateAtWhichTaxDeducted")
	public Double rateAtWhichTaxDeducted;
	@Column(name = "reasonForNonDeduction")
	public String reasonForNonDeduction;
	@Column(name = "noOfCertificateUnderSection")
	public Long noOfCertificateUnderSection;
	@Column(name = "uniqueAcknowledgeNo")
	public Long uniqueAcknowledgeNo;
	@Column(name = "countryOfResidence")
	public String countryOfResidence;
	@Column(name = "emailId")
	public String emailId;
	@Column(name = "contactNoOfDeductee")
	public Long contactNoOfDeductee;
	@Column(name = "addressOfDeductee")
	public String addressOfDeductee;
	@Column(name = "taxIdentificationNo")
	public Long taxIdentificationNo;
	@Column(name = "errorDescription")
	public String errorDescription;	
	@Column(name = "warningDescription")
	public String warningDescription;
	@Column(name = "shortDeduction")
	public String shortDeduction;
	@Column(name = "interestOnShortDeduction")
	public String interestOnShortDeduction;
	@Column(name = "interestOnLatePayment")
	public String interestOnLatePayment;
	@Column(name = "interestOnLateDeduction")
	public String interestOnLateDeduction;
	@Column(name = "fy")
	public String fy;
	@Column(name = "quarter")
	public String quarter;
}
