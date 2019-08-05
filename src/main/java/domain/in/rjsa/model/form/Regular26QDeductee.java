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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.in.rjsa.util.JsonDateSerializer;
import lombok.Data;

@Data
@Entity
@Table(name = "regular26QDeductee")
public class Regular26QDeductee extends CommonModelAbstract{
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
	@Column(name = "branchId")
	public Long branchId;
	@Column(name = "srNo")
	public Long srNo;	
	@Column(name = "challanSrNo")
	@Digits(integer = 15, fraction = 0,message="Enter valid Challan SrNo. Challan SrNo. should not be greater than 12")
	@NotNull(message = "ChallanSrNo is a required field")
	public Long challanSrNo;
	@Column(name = "deducteeRefNo")
	@Size(min=0, max=45, message="Deductee RefNo should be 45 characters.")
	@NotNull(message = "Deductee Ref No is a required field")
	public String deducteeRefNo;
	@Column(name = "deducteeCode")
	@Size(min=0, max=45, message="Deductee Code should be 45 characters.")
	@NotNull(message = "Deductee Code is a required field")
	public String deducteeCode;
	@Column(name = "deducteePan")
	@Size(min=0, max=45, message="Deductee Pan should be 45 characters.")
	@NotNull(message = "Deductee Pan is a required field")
	public String deducteePan;
	@Column(name = "deducteeName")
	@Size(min=0, max=45, message="Deductee Name should be 45 characters.")
	@NotNull(message = "Deductee name is a required field")
	public String deducteeName;
	@Column(name = "sectionCode")
	@Size(min=0, max=45, message="Section Code should be 45 characters.")
	@NotNull(message = "Section Code is a required field")
	public String sectionCode;
	@Temporal(TemporalType.DATE)
	@Column(name = "paymentDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date paymentDate;
	@Column(name = "paidAmt")
	@Digits(integer = 12, fraction = 0,message="Enter valid Paid Amt it should not be greater than 12")
	@NotNull(message = "Paid Amt is a required field")
	public Double paidAmt;
	@Column(name = "tds")
	@Digits(integer = 12, fraction = 0,message="Enter valid Tds it should not be greater than 12")
	@NotNull(message = "Tds is a required field")
	public Double tds;
	@Column(name = "surcharge")
	@Digits(integer = 12, fraction = 0,message="Enter valid Surcharge it should not be greater than 12")
	@NotNull(message = "Surcharge is a required field")
	public Double surcharge;
	@Column(name = "eduCess")
	@Digits(integer = 12, fraction = 0,message="Enter valid Education Cess it should not be greater than 12")
	@NotNull(message = "Education Cess is a required field")
	public Double eduCess;
	@Column(name = "totalTaxDeduct")
	@Digits(integer = 12, fraction = 0,message="Enter valid Total Tax Deduct it should not be greater than 12")
	@NotNull(message = "Total Tax Deduct  is a required field")
	public Double totalTaxDeduct;
	@Column(name = "totalTaxDeposit")
	@Digits(integer = 12, fraction = 0,message="Enter valid Total Tax Deposit it should not be greater than 12")
	@NotNull(message = "Total Tax Deposit is a required field")
	public Double totalTaxDeposit;
	@Temporal(TemporalType.DATE)
	@Column(name = "deductDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date deductDate;
	@Column(name = "rateTaxDeduct")
	@Digits(integer = 12, fraction = 0,message="Enter valid Rate Tax Deposit it should not be greater than 12")
	@NotNull(message = "Rate Tax Deduct is a required field")
	public Double rateTaxDeduct;
	@Column(name = "remarks")
	@Size(min=0, max=45, message="Section Code should be 45 characters.")
	@NotNull(message = "Remarks is a required field")
	public String remarks;
	@Column(name = "certificateNo")
	@Size(min=0, max=45, message="Certificate No should be 45 characters.")
	@NotNull(message = "Certificate No is a required field")
	public String certificateNo;
	@Column(name = "fy")
	@Size(min=0, max=45, message="Fy should be 45 characters.")
	@NotNull(message = "Fy is a required field")
	public String fy;
	@Column(name = "quarter")
	@Size(min=0, max=45, message="Quarter should be 45 characters.")
	@NotNull(message = "Quarter is a required field")
	public String quarter;
	@Column(name = "verify")
	public boolean verify = false;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDeductDate() {
		return deductDate;
	}
	public void setDeductDate(Date deductDate) {
		this.deductDate = deductDate;
	}
}
