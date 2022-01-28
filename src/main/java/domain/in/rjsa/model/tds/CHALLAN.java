package domain.in.rjsa.model.tds;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.in.rjsa.model.form.CommonModelAbstract;
import domain.in.rjsa.util.JsonDateSerializer;
import lombok.Data;

@Data
@Entity
@Table(name = "Taxo.CHALLAN")
public class CHALLAN extends CommonModelAbstract{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;	
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "branchId")
	public Long branchId;
	@Column(name = "srNo")
	public Long srNo;
	@Column(name = "tds")
	@NotNull(message = "TDS is a required field")
	@Digits(integer = 12, fraction = 2,message="Invalid TDS")
	public Double tds;
	@Column(name = "surcharge")
	@NotNull(message = "Surcharge is a required field")
	@Digits(integer = 12, fraction = 2,message="Invalid Surcharge")
	public Double surcharge;
	@Column(name = "educationCess")
	@NotNull(message = "Education Cess is a required field")
	@Digits(integer = 12, fraction = 2,message="Invalid Education Cess")
	public Double educationCess;
	@Column(name = "interest")
	@NotNull(message = "Interest is a required field")
	@Digits(integer = 12, fraction = 2,message="Invalid Interest")
	public Double interest;
	@Column(name = "fee")
	@NotNull(message = "Fee is a required field")
	@Digits(integer = 12, fraction = 2,message="Invalid Fee")
	public Double fee;
	@Column(name = "others")
	@NotNull(message = "Others is a required field")
	@Digits(integer = 12, fraction = 2,message="Invalid Value")
	public Double others;
	@Column(name = "totalTaxDeposited")
	@NotNull(message = "Total Tax Deposited is a required field")
	@Digits(integer = 12, fraction = 2,message="Invalid Total Tax Deposited")
	public Double totalTaxDeposited;
	@Column(name = "byBookEntry")
	@NotNull(message = "By Book Entry is a required field")
	@Size(min=0, max=45, message="Invalid By Book Entry")
	public String byBookEntry;
	@Column(name = "bsrCode")
	@Size(min=0, max=45, message="Bsr Code length should not be more than 45 characters.")
	public String bsrCode;
	@Column(name = "challanSerialNo")
	@Digits(integer = 12, fraction = 0,message="Invalid Challan serial number")
	public Long challanSerialNo;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfDeposition")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date dateOfDeposition;
	@Column(name = "minorHeadOfChallan")
	@Size(min=0, max=45, message="Invalid Minor Head Of Challan")
	public String minorHeadOfChallan;
	@Column(name = "interestAllocated")
	@Digits(integer = 12, fraction = 2,message="Invalid Interest Allocated")
	public Double interestAllocated;
	@Column(name = "otherAmtAllocated")
	@Digits(integer = 12, fraction = 2,message="Invalid Other Amount Allocated")
	public Double otherAmtAllocated;
	@Column(name = "remarks")
	@Size(min=0, max=45, message="Invalid Remarks")
	public String remarks;
	@Column(name = "nillChallanIndicator")
	@Size(min=0, max=45, message="Invalid Nill Challan Indicator")
	public String nillChallanIndicator;
	@Column(name = "fy")
	@Size(min=0, max=45, message="Invalid FY")
	public String fy;
//	@Column(name = "quarter")
//	@Size(min=0, max=45, message="Invalid Quarter")
//	public String quarter;
	@Column(name = "amountConsumed")
	@NotNull(message = "Amount Consumed is a required field")
	@Digits(integer = 12, fraction = 2,message="Invalid Value")
	public Double amountConsumed;
	@Column(name = "amountAvailable")
	@NotNull(message = "Amount Available is a required field")
	@Digits(integer = 12, fraction = 2,message="Invalid Value")
	public Double amountAvailable;
	@Column(name = "verify")
	public Boolean verify = false;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateOfDeposition() {
		return dateOfDeposition;
	}
	public void setDateOfDeposition(Date dateOfDeposition) {
		this.dateOfDeposition = dateOfDeposition;
	}
	
}
