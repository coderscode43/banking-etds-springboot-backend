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
	@NotNull(message = "Tds is a required field")
	@Digits(integer = 12, fraction = 2,message="The Tds is not proper.Maximum 2 fractions allowed.")
	public Double tds;
	@Column(name = "surcharge")
	@Digits(integer = 12, fraction = 2,message="The Surcharge is not proper.Maximum 2 fractions allowed.")
	@NotNull(message = "Surcharge is a required field")
	public Double surcharge;
	@Column(name = "eduCess")
	@Digits(integer = 12, fraction = 2,message="The EducationCess is not proper.Maximum 2 fractions allowed.")
	@NotNull(message = "EducationCess is a required field")
	public Double eduCess;
	@Column(name = "interest")
	@Digits(integer = 12, fraction = 2,message="The Interest is not proper.Maximum 2 fractions allowed.")
	@NotNull(message = "Interest is a required field")
	public Double interest;
	@Column(name = "fee")
	@Digits(integer = 12, fraction = 2,message="The Fee is not proper.Maximum 2 fractions allowed.")
	@NotNull(message = "Fee is a required field")
	public Double fee;	
	@Column(name = "others")
	@Digits(integer = 12, fraction = 2,message="The Others is not proper.Maximum 2 fractions allowed.")
	@NotNull(message = "Others is a required field")
	public Double others;
	@Column(name = "totalTaxDeposit")
	@Digits(integer = 12, fraction = 2,message="The TotalTaxDeposit is not proper.Maximum 2 fractions allowed.")
	@NotNull(message = "TotalTaxDeposit is a required field")
	public Double totalTaxDeposit;
	@Column(name = "tdsDepositBookEntry")
	@Size(min=0, max=45, message="TdsDepositBookEntry should be between 0 to 100 characters.")
	@NotNull(message = "TdsDepositBookEntry is a required field")
	public String tdsDepositBookEntry;
	@Column(name = "bsrCode")
	@Size(min=0, max=45, message="BsrCode length should not be more than 45 characters.")
	@NotNull(message = "BsrCode is a required field")
	public String bsrCode;
	@Column(name = "challanSrNo")
	@Digits(integer = 25, fraction = 0,message="Enter valid Challan SrNo. Challan SrNo. should not be greater than 12")
	@NotNull(message = "ChallanSrNo is a required field")
	public Long challanSrNo;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateTaxDeposit")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@NotNull(message = "DateTaxDeposit is a required field")
	public Date dateTaxDeposit;
	@Column(name = "minorHeadChallan")
	@Size(min=0, max=45, message="MinorHeadChallan length should be between 1 to 25 characters.")
	@NotNull(message = "MinorHeadChallan is a required field")
	public String minorHeadChallan;
	@Column(name = "interestAllocated")
	@Digits(integer = 12, fraction = 2,message="The InterestAllocated is not proper.Maximum 2 fractions allowed.")
	@NotNull(message = "InterestAllocated is a required field")
	public Double interestAllocated;
	@Column(name = "otherAmtAllocated")
	@Digits(integer = 12, fraction = 2,message="The OtherAmtAllocated is not proper.Maximum 2 fractions allowed.")
	@NotNull(message = "OtherAmtAllocated is a required field")
	public Double otherAmtAllocated;
	@Column(name = "nilChallanIndicator")
	@Size(min=0, max=45, message="NilChallanIndicator length should be between 1 to 25 characters.")
	@NotNull(message = "NilChallanIndicator is a required field")
	public String nilChallanIndicator;
	@Column(name = "fy")
	@Size(min=0, max=45, message="Fy length should be between 1 to 25 characters.")
	@NotNull(message = "Fy is a required field")
	public String fy;
	@Column(name = "quarter")
	@Size(min=0, max=45, message="Quarter length should be between 1 to 25 characters.")
	@NotNull(message = "Quarter is a required field")
	public String quarter;
}
