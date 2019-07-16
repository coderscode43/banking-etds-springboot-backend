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
	@NotNull(message = "TDS Value is required")
	@Digits(integer = 12, fraction = 2,message="The amount value is not greater than 12 digits. Maximum 2 fractions allowed.")
	public Double tds;
	
	@Column(name = "surcharge")
	@NotNull(message = "Surcharge Value is required")
	@Digits(integer = 12, fraction = 2,message="The amount value is not greater than 12 digits. Maximum 2 fractions allowed.")
	public Double surcharge;
	
	@Column(name = "eduCess")
	@NotNull(message = "Education Cess Value is required")
	@Digits(integer = 12, fraction = 2,message="The amount value is not greater than 12 digits. Maximum 2 fractions allowed.")
	public Double eduCess;
	
	@Column(name = "interest")
	@NotNull(message = "Interest is required")
	@Digits(integer = 12, fraction = 2,message="The amount value is not greater than 12 digits. Maximum 2 fractions allowed.")
	public Double interest;
	
	@Column(name = "fee")
	@NotNull(message = "Fee is required")
	@Digits(integer = 12, fraction = 2,message="The amount value is not greater than 12 digits. Maximum 2 fractions allowed.")
	public Double fee;
	
	@Column(name = "others")
	@NotNull(message = "Others is required")
	@Digits(integer = 12, fraction = 2,message="The amount value is not greater than 12 digits. Maximum 2 fractions allowed.")
	public Double others;
	
	@Column(name = "totalTaxDeposit")
	@NotNull(message = "Total Tax Deposit is required")
	@Digits(integer = 12, fraction = 2,message="The amount value is not greater than 12 digits. Maximum 2 fractions allowed.")
	public Double totalTaxDeposit;
	
	@Column(name = "tdsDepositBookEntry")
	@NotNull(message = "TDS Deposit Book Entry is required")
	@Size(min=0, max=45, message="TDS Deposit Book Entry should not be greater than 45 characters.")
	public String tdsDepositBookEntry;
	
	@Column(name = "bankBranchCode")
	@NotNull(message = "Bank Branch Code is required")
	@Size(min=0, max=45, message="Bank Branch Code should not be greater than 45 characters.")
	public String bankBranchCode;
	
	@Column(name = "challanSrNo")
	@NotNull(message = "Challan Sr.No is required")
	@Digits(integer = 12, fraction = 0,message="The amount value is not proper.")
	public Long challanSrNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "taxDepositDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	@NotNull(message = "Date is required")
	public Date taxDepositDate;
	
	@Column(name = "minorHeadChallan")
	@NotNull(message = "Minor Head Of Challan is required")
	@Size(min=0, max=45, message="Minor Head Of Challan should not be greater than 45 characters.")
	public String minorHeadChallan;
	
	@Column(name = "interestAllocated")
    @NotNull(message = "Interest Allocated is required")
	@Digits(integer = 12, fraction = 2,message="The amount value is not greater than 12 digits. Maximum 2 fractions allowed.")
	public Double interestAllocated;
	
    @Column(name = "otherAmtAllocated")
    @NotNull(message = "Other Amount Allocated is required")
	@Digits(integer = 12, fraction = 2,message="The amount value is not greater than 12 digits. Maximum 2 fractions allowed.")
	public Double otherAmtAllocated;
	
	@Column(name = "nilChallanIndicator")
	@NotNull(message = "Nill Challan Indicator is required")
	@Size(min=0, max=45, message="Nill Challan Indicator should not be greater than 45 characters.")
	public String nilChallanIndicator;
	
	@Column(name = "fy")
    @NotNull(message = "Financial Year is required")
	@Size(min=0, max=45, message="Financial Year should not be greater than 45 characters.")
	public String fy;
	
	@Column(name = "quarter")
	@NotNull(message = "Quarter is required")
	@Size(min=0, max=45, message="Nill Challan Indicator should not be greater than 45 characters.")
	public String quarter;
	
	@Column(name = "amountConsumed")
	@NotNull(message = "Amount Consumed is required")
	@Digits(integer = 12, fraction = 2,message="The Amount Consumed value is not greater than 12 digits. Maximum 2 fractions allowed.")
	public Double amountConsumed;
	
	@Column(name = "amountAvailable")
	@NotNull(message = "Amount Available is required")
	@Digits(integer = 12, fraction = 2,message="The Amount Available value is not greater than 12 digits. Maximum 2 fractions allowed.")
	public Double amountAvailable;
}
