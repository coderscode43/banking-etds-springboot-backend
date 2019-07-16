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
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "regular26QChallan")
public class Regular26QChallan extends CommonModelAbstract{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@Digits(integer = 12, fraction = 2,message="The Tds is not proper.Maximum 2 fractions allowed.")
	@NotNull(message = "TDS is a required field")
	public Double tds;
	@Column(name = "surcharge")
	@NotNull(message = "Surcharge is a required field")
	@Digits(integer = 12, fraction = 2,message="The Surcharge is not proper.Maximum 2 fractions allowed.")
	public Double surcharge;
	@Column(name = "eduCess")
	@NotNull(message = "EducationCess is a required field")
	@Digits(integer = 12, fraction = 2,message="The EducationCess is not proper.Maximum 2 fractions allowed.")
	public Double eduCess;
	@Column(name = "interest")
	@NotNull(message = "Interest is a required field")
	@Digits(integer = 12, fraction = 2,message="The Interest is not proper.Maximum 2 fractions allowed.")
	public Double interest;
	@Column(name = "fee")
	@NotNull(message = "Fee is a required field")
	@Digits(integer = 12, fraction = 2,message="The Fee is not proper.Maximum 2 fractions allowed.")
	public Double fee;	
	@Column(name = "others")
	@NotNull(message = "Others is a required field")
	@Digits(integer = 12, fraction = 2,message="The Others is not proper.Maximum 2 fractions allowed.")
	public Double others;
	@Column(name = "totalTaxDeposit")
	@NotNull(message = "Total Tax Deposit is a required field")
	@Digits(integer = 12, fraction = 2,message="The TotalTaxDeposit is not proper.Maximum 2 fractions allowed.")
	public Double totalTaxDeposit;
	@Column(name = "tdsDepositBookEntry")
	@NotNull(message = "Tds Deposit BookEntry is a required field")
	@Size(min=0, max=45, message="TdsDepositBookEntry should be between 0 to 100 characters.")
	public String tdsDepositBookEntry;
	@Column(name = "bsrCode")
	@NotNull(message = "BsrCode is a required field")
	@Size(min=0, max=45, message="Bsr Code length should not be more than 45 characters.")
	public String bsrCode;
	@Column(name = "challanSrNo")
	@NotNull(message = "Challan SrNo is a required field")
	@Digits(integer = 25, fraction = 0,message="Enter valid Challan SrNo. Challan SrNo. should not be greater than 12")
	public Long challanSrNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dateTaxDeposit")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	@NotNull(message = "Date Tax Deposit is a required field")
	public Date dateTaxDeposit;
	
	@Column(name = "minorHeadChallan")
	@NotNull(message = "Minor Head Challan is a required field")
	@Size(min=0, max=45, message="MinorHeadChallan length should be 45 characters.")
	public String minorHeadChallan;
	@Column(name = "interestAllocated")
	@NotNull(message = "Interest Allocated is a required field")
	@Digits(integer = 12, fraction = 2,message="The InterestAllocated is not proper.Maximum 2 fractions allowed.")
	public Double interestAllocated;
	@Column(name = "otherAmtAllocated")
	@NotNull(message = "Other Amt Allocated is a required field")
	@Digits(integer = 12, fraction = 2,message="The OtherAmtAllocated is not proper.Maximum 2 fractions allowed.")
	public Double otherAmtAllocated;
	@Column(name = "nilChallanIndicator")
	@NotNull(message = "Nil Challan Indicator is a required field")
	@Size(min=0, max=45, message="NilChallanIndicator length should be 45 characters.")
	public String nilChallanIndicator;
	@Column(name = "fy")
	@NotNull(message = "Fy is a required field")
	@Size(min=0, max=45, message="Fy length should be 45 characters.")
	public String fy;
	@Column(name = "quarter")
	@NotNull(message = "Quarter is a required field")
	@Size(min=0, max=45, message="Quarter length should be 45 characters.")
	public String quarter;
	
	@Column(name = "amountConsumed")
	@NotNull(message = "Amount Consumed is a required field")
	@Digits(integer = 12, fraction = 2,message="Amount Consumed is not proper.Maximum 2 fractions allowed.")
	public Double amountConsumed;
	@Column(name = "amountAvailable")
	@NotNull(message = "Amount Available is a required field")
	@Digits(integer = 12, fraction = 2,message="Amount Available is not proper.Maximum 2 fractions allowed.")
	public Double amountAvailable;
}
