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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

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
	@NotNull(message = "Deductee Sr No is a required field")
	@Digits(integer = 15, fraction = 0,message="Enter valid Deductee Sr No. Deductee Sr No. should not be greater than 12")
	public Long deducteeSrNo;
	@Column(name = "srNoInChallan")
	@NotNull(message = "Sr No In Challan is a required field")
	@Digits(integer = 15, fraction = 0,message="Enter valid Sr No In Challan. Sr No In Challan. should not be greater than 12")
	public Long srNoInChallan;
	
	@Column(name = "deducteeRefNo")
	@NotNull(message = "Deductee Ref No is a required field")
	@Digits(integer = 15, fraction = 0,message="Enter valid Deductee Ref No.Deductee Ref No. should not be greater than 12")
	public Long deducteeRefNo;
	@Column(name = "deducteeCode")
	@NotNull(message = "Deductee Code  is a required field")
	@Size(min=0, max=45, message="Deductee Code  should not be greater than 45 characters.")
	public String deducteeCode;
	@Column(name = "pan")
	@NotNull(message = "PAN In Deductee is a required field")
	@Pattern(regexp="^([a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1})|^$", message="PAN is not valid.")
	@Size(min=0, max=45, message="PAN should be between 0 to 10 characters.")
	public String pan;
	@Column(name = "name")
	@NotNull(message = "Name is a required field")
	@Size(min=0, max=45, message=" Name length should be between 1 to 100 characters.")
	public String name;
	@Column(name = "sectionCode")
	@NotNull(message = "Section Code is a required field")
	@Size(min=0, max=45, message="section Code should not be greater than 45 characters.")
	public String sectionCode;
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	@NotNull(message = "Date required field")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date date;
	@Column(name = "amountPaid")
	@NotNull(message = "amount Paid is a required field")
	@Digits(integer = 12, fraction = 2,message="amount Paid is not proper.Maximum 2 fractions allowed.")
	public Double amountPaid;
	@Column(name = "tds")
	@NotNull(message = "tds is a required field")
	@Digits(integer = 12, fraction = 2,message="tds is not proper.Maximum 2 fractions allowed.")
	public Double tds;
	@Column(name = "surcharge")
	@NotNull(message = "surcharge is a required field")
	@Digits(integer = 12, fraction = 2,message="surcharge is not proper.Maximum 2 fractions allowed.")
	public Double surcharge;
	@Column(name = "educationCess")
	@NotNull(message = "educationCess is a required field")
	@Digits(integer = 12, fraction = 2,message="educationCess is not proper.Maximum 2 fractions allowed.")
	public Double educationCess;
	@Column(name = "totalTaxDeducted")
	@NotNull(message = "totalTaxDeducted is a required field")
	@Digits(integer = 12, fraction = 2,message="totalTaxDeducted is not proper.Maximum 2 fractions allowed.")
	public Double totalTaxDeducted;
	@Column(name = "totalTaxDeposited")
	@NotNull(message = "Total Tax Deposited is a required field")
	@Digits(integer = 12, fraction = 2,message="totalTaxDepositedis not proper.Maximum 2 fractions allowed.")
	public Double totalTaxDeposited;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfDeduction")
	@NotNull(message = "Date is a required field")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date dateOfDeduction;
	@Column(name = "rateAtWhichTaxDeducted")
	@NotNull(message = "Rate At Which Tax Deducted is a required field")
	@Digits(integer = 12, fraction = 2,message="Rate At Which Tax Deducted  is not proper.Maximum 2 fractions allowed.")
	public Double rateAtWhichTaxDeducted;
	@Column(name = "reasonForNonDeduction")
	@NotNull(message = "Reason For Non Deduction is a required field")
	@Size(min=0, max=45, message="section Code should not be greater than 45 characters.")
	public String reasonForNonDeduction;
	@Column(name = " grossingUpIndicator")
	@NotNull(message = "Grossing Up Indicator is a required field")
	@Size(min=0, max=45, message="grossingUpIndicator should not be greater than 45 characters.")
	public String grossingUpIndicator;
	@Column(name = "noOfCertificateUnderSection")
	@NotNull(message = "No Of Certificate Under Section is a required field")
	@Digits(integer = 15, fraction = 0,message="No Of Certificate Under Section .No Of Certificate Under Section . should not be greater than 12")
	public Long noOfCertificateUnderSection;
	@Column(name = "uniqueAcknowledgeNo")
	@NotNull(message = "Unique Acknowledge No Under Section is a required field")
	@Digits(integer = 15, fraction = 0,message="Enter valid Unique Acknowledge.Unique Acknowledge. should not be greater than 12")
	public Long uniqueAcknowledgeNo;
	@Column(name = "countryOfResidence")
	@NotNull(message = "Country Of Residence is a required field")
	@Size(min=0, max=45, message="Country Of Residence length should be between 1 to 100 characters.")
	public String countryOfResidence;
	@Column(name = "emailId")
	@NotNull(message = "Email is a required field")
	@Size(min=0, max=45, message="Email should be between 0 to 75 characters.")
	public String emailId;
	@Column(name = "contactNoOfDeductee")
	@NotNull(message = "contact No Of Deductee is a required field")
	@Size(min=0, max=10, message="contact No  should be between 0 to 10 characters.")
	public String contactNoOfDeductee;
	@Column(name = "addressOfDeductee")
	@NotNull(message = "address Of Deductee is a required field")
	@Size(min=0, max=45, message="address Of Deductee length should be between 1 to 100 characters.")
	public String addressOfDeductee;
	@Column(name = "taxIdentificationNo")
	@NotNull(message = "tax Identification No is a required field")
	@Digits(integer = 20, fraction = 0,message="Enter valid tax Identification No.tax Identification No. should not be greater than 12")
	public Long taxIdentificationNo;
	@Column(name = "fy")
	@NotNull(message = "Financial Year is a required field")
	@Size(min=0, max=45, message="address Of Deductee length should be between 1 to 100 characters.")
	public String fy;
	@Column(name = "quarter")
	@NotNull(message = "Quarter is a required field")
	@Size(min=0, max=45, message="address Of Deductee length should be between 1 to 100 characters.")
	public String quarter;
	@Column(name = "tdaRateAsPerItActs")
	@NotNull(message = "amount Paid is a required field")
	@Digits(integer = 12, fraction = 2,message="amount Paid is not proper.Maximum 2 fractions allowed.")
	public Double tdaRateAsPerItActs;
	
	@Column(name = "natureOfRemittance")
	@NotBlank(message = "amount Paid is a required field")
	@Size(min=0, max=75, message="Country Of Residence length should be between 1 to 100 characters.")
	public String natureOfRemittance;
	
	@Column(name = "verify")
	public boolean verify = false;
	
	public void setEntity(Regular27QDeductee form27Q){
		this.date=form27Q.getDate();
		
	}
}

