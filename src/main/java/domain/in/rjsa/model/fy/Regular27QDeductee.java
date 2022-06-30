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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.in.rjsa.model.form.CommonModelAbstract;
import domain.in.rjsa.util.JsonDateSerializer;
import lombok.Data;

@Data
@Entity
@Table(name = "AABCT5589K_2223.regular27QDeductee")
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
	
	@Column(name = "challanHeading")
	public String challanHeading;
	
	@Column(name = "deducteeRefNo")
	public String deducteeRefNo;
	
	@Column(name = "deducteeCode")
	public String deducteeCode;
	
	@Column(name = "pan")
	public String pan;
	
	@Column(name = "name")
	public String name;
	
	@Column(name = "sectionCode")
	public String sectionCode;
	
	@Column(name = "date")
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
	
	@Column(name = "dateOfDeduction")
	public Date dateOfDeduction;
	
	@Column(name = "rateAtWhichTaxDeducted")
	public Double rateAtWhichTaxDeducted;
	
	@Column(name = "reasonForNonDeduction")
	public String reasonForNonDeduction;
	
	@Column(name = " grossingUpIndicator")
	public String grossingUpIndicator;
	
	@Column(name = "noOfCertificateUnderSection")
	public String noOfCertificateUnderSection;
	
	@Column(name = "uniqueAcknowledgeNo")
	public Long uniqueAcknowledgeNo;
	
	@Column(name = "countryOfResidence")
	public Long countryOfResidence;
	
	@Column(name = "emailId")
	public String emailId;
	
	@Column(name = "contactNoOfDeductee")
	public Long contactNoOfDeductee;
	
	@Column(name = "addressOfDeductee")
	public String addressOfDeductee;
	
	@Column(name = "taxIdentificationNo")
	public Long taxIdentificationNo;
	
	@Column(name = "fy")
	public String fy;
	
	@Column(name = "quarter")
	public String quarter;
	
	@Column(name = "tdsRateAsPerItActs")
	public String tdsRateAsPerItActs;
	
	@Column(name = "natureOfRemittance")
	public Long natureOfRemittance;
	
	@Column(name = "branchCode")
	public Long branchCode;
	
	@Column(name = "accNo")
	public String accNo;
	
	@Column(name = "idNo")
	public Long idNo;
	
	@Column(name = "month")
	public String month;
	
	@Column(name = "custVendId")
	public String custVendId;
	
	@Column(name = "uniqueRefNo")
	public String uniqueRefNo;
	
	@Column(name = "cashWithdrawal194N")
	public Double cashWithdrawal194N;
	
	@Column(name = "cashWithdrawal194N20Lto1Cr")
	public Double cashWithdrawal194N20Lto1Cr;
	
	@Column(name = "cashWithdrawal194N1Cr")
	public Double cashWithdrawal194N1Cr;
	
	@Column(name = "TAN")
	public String TAN ;
	
	@Column(name = "roCode")
	public Long roCode ;
	
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
	
	
//	@Column(name = "verify")
//	public boolean verify = false;
	
	@Column(name = "resolved")
	public boolean resolved;
	
	@Column(name = "comments")
	public String comments;
	
	@Column(name = "remarks")
	public String remarks;
	
	public void setEntity(Regular27QDeductee form27Q){
		this.date=form27Q.getDate();		
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateOfDeduction() {
		return dateOfDeduction;
	}
	public void setDateOfDeduction(Date dateOfDeduction) {
		this.dateOfDeduction = dateOfDeduction;
	}
}

