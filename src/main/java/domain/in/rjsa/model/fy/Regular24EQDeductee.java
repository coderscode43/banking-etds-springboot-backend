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
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.in.rjsa.model.form.CommonModelAbstract;
import domain.in.rjsa.util.JsonDateSerializer;
import lombok.Data;

@Data
@Entity
@Table(name = "FYDetails.regular24EQDeductee")
public class Regular24EQDeductee extends CommonModelAbstract {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;
	@Column(name = "branchCode")
	public Long branchCode;
	@Column(name = "srNo")
	public Long srNo;
	@Column(name = "deducteeId")
	public Long deducteeId;
	@Column(name = "challanHeading")
	public String challanHeading;
	@Column(name = "challanSrNo")
	@NotNull(message = "Challan Sr. No. is a required field.")
	@Digits(integer = 12, fraction = 0, message = "Enter valid Challan No. Challan No. should not be greater than 12")
	public Long challanSrNo;
	@Column(name = "employeeRefNo")
	@NotNull(message = "Employee No. is a required field.")
	@Size(min = 0, max = 45, message = "Employee No should not be greater than 45 characters.")
	public String employeeRefNo;
	@Column(name = "panRefNo")
	@NotNull(message = "Pan Reference No. is a required field.")
	@Size(min = 0, max = 45, message = "Pan Reference No. should not be greater than 45 characters.")
	public String panRefNo;
	@Column(name = "pan")
	@NotNull(message = "Pan is a required field.")
	@Size(min = 0, max = 45, message = "Pan should not be greater than 10 characters.")
	public String pan;
	@Column(name = "name")
	@NotNull(message = "Name is a required field.")
	@Size(min = 0, max = 45, message = "Name should not be greater than 45 characters.")
	public String name;
	@Column(name = "sectionCode")
	@NotNull(message = "Section Code is a required field.")
	@Size(min = 0, max = 45, message = "Section Code should not be greater than 45 characters.")
	public String sectionCode;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfPayment")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	@NotNull(message = "Date is a required field.")
	public Date dateOfPayment;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfDeduction")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	@NotNull(message = "Date is a required field.")
	public Date dateOfDeduction;
	@Column(name = "amountPaid")
	@NotNull(message = "Amount Paid is a required field.")
	@Digits(integer = 12, fraction = 2, message = "Amount Paid is not proper.Maximum 2 fractions allowed.")
	public Double amountPaid;
	@Column(name = "tds")
	@NotNull(message = "Tds is a required field.")
	@Digits(integer = 12, fraction = 2, message = "Tds is not proper.Maximum 2 fractions allowed.Maximum 2 fractions allowed.")
	public Double tds;
	@Column(name = "surcharge")
	@NotNull(message = "Surcharge is a required field.")
	@Digits(integer = 12, fraction = 2, message = "Surcharge is not proper.Maximum 2 fractions allowed.")
	public Double surcharge;
	@Column(name = "educationCess")
	@NotNull(message = "Education Cess is a required field.")
	@Digits(integer = 12, fraction = 2, message = "Education Cess is not proper.Maximum 2 fractions allowed.")
	public Double educationCess;
	@Column(name = "totalTds")
	@NotNull(message = "Total Tds is a required field.")
	@Digits(integer = 12, fraction = 2, message = "Education Cess is not proper.Maximum 2 fractions allowed.")
	public Double totalTds;
	@Column(name = "totalTaxDeposited")
	@NotNull(message = "Total Tax Deposited is a required field.")
	@Digits(integer = 12, fraction = 2, message = "Total Tax Deposited value is not proper.Maximum 2 fractions allowed.")
	public Double totalTaxDeposited;
	@Column(name = "certificateNumber")
	@NotNull(message = "Certificate Number is a required field.")
	@Size(min = 0, max = 45, message = "Certificate Number should not be greater than 45 characters.")
	public String certificateNumber;
	@Column(name = "remarks")
	@NotNull(message = "Remarks is a required field.")
	@Size(min = 0, max = 45, message = "Remarks should not be greater than 45 characters.")
	public String remarks;
	@Column(name = "fy")
	@NotNull(message = "FY is a required field.")
	@Size(min = 0, max = 45, message = "FY should not be greater than 45 characters.")
	public String fy;
	@Column(name = "quarter")
	@NotNull(message = "Quarter is a required field.")
	@Size(min = 0, max = 45, message = "Quarter should not be greater than 45 characters.")
	public String quarter;
	@Column(name = "verify")
	public boolean verify = false;
	

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDateOfDeduction() {
		return dateOfDeduction;
	}

	public void setDateOfDeduction(Date dateOfDeduction) {
		this.dateOfDeduction = dateOfDeduction;
	}
}

