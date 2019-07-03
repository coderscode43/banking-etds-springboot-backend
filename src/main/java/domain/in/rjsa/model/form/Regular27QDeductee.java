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

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "regular27QDeductee")
public class Regular27QDeductee extends CommonModelAbstract {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
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
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date date;
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
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfDeduction")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date dateOfDeduction;
	@Column(name = "rateAtWhichTaxDeducted")
	public Double rateAtWhichTaxDeducted;
	@Column(name = "reasonForNonDeduction")
	public String reasonForNonDeduction;
	@Column(name = " grossingUpIndicator")
	public String grossingUpIndicator;
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

	
	
	public void setEntity(Regular27QDeductee form27Q){
		this.date=form27Q.getDate();
		
	}
}

