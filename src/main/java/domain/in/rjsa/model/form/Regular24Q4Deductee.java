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

import lombok.Data;

@Data
@Entity
@Table(name = "regular24Q4Deductee")
public class Regular24Q4Deductee extends CommonModelAbstract {
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
	@Column(name = "srNo")
	public Long srNo;
	@Column(name = "challanSrNo")
	@NotNull(message = "InvoiceDate is a required field.")
	@Digits(integer = 12, fraction = 0,message="Enter valid Challan No. Challan No. should not be greater than 12")
	public Long challanSrNo;
	@Column(name = "employeeRefNo")
	@NotNull(message = "InvoiceDate is a required field.")
	@Size(min=0, max=45, message="Nature Of Document should not be greater than 45 characters.")
	public String employeeRefNo;
	@Column(name = "panRefNo")
	@NotNull(message = "InvoiceDate is a required field.")
	@Size(min=0, max=45, message="Nature Of Document should not be greater than 45 characters.")
	public String panRefNo;
	@Column(name = "pan")
	@NotNull(message = "InvoiceDate is a required field.")
	@Size(min=0, max=45, message="Nature Of Document should not be greater than 45 characters.")
	public String pan;
	@Column(name = "name")
	@NotNull(message = "InvoiceDate is a required field.")
	@Size(min=0, max=45, message="Nature Of Document should not be greater than 45 characters.")
	public String name;
	@Column(name = "sectionCode")
	@NotNull(message = "InvoiceDate is a required field.")
	@Size(min=0, max=45, message="Nature Of Document should not be greater than 45 characters.")
	public String sectionCode;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfPayment")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date dateOfPayment;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfDeduction")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date dateOfDeduction;
	@Column(name = "amountPaid")
	@NotNull(message = "InvoiceDate is a required field.")
	@Digits(integer = 12, fraction = 2,message="The amount value is not proper.Maximum 2 fractions allowed.")
	public Double amountPaid;
	@Column(name = "tds")
	@NotNull(message = "InvoiceDate is a required field.")
	@Digits(integer = 12, fraction = 2,message="The amount value is not proper.Maximum 2 fractions allowed.")
	public Double tds;
	@Column(name = "surcharge")
	@NotNull(message = "InvoiceDate is a required field.")
	@Digits(integer = 12, fraction = 2,message="The amount value is not proper.Maximum 2 fractions allowed.")
	public Double surcharge;
	@Column(name = "educationCess")
	@NotNull(message = "InvoiceDate is a required field.")
	@Digits(integer = 12, fraction = 2,message="The amount value is not proper.Maximum 2 fractions allowed.")
	public Double educationCess;
	@Column(name = "totalTds")
	@NotNull(message = "InvoiceDate is a required field.")
	@Size(min=0, max=45, message="Nature Of Document should not be greater than 45 characters.")
	public String totalTds;
	@Column(name = "totalTaxDeposited")
	@NotNull(message = "InvoiceDate is a required field.")
	@Digits(integer = 12, fraction = 2,message="The amount value is not proper.Maximum 2 fractions allowed.")
	public Double totalTaxDeposited;
	@Column(name = "certificateNumber")
	@NotNull(message = "InvoiceDate is a required field.")
	@Size(min=0, max=45, message="Nature Of Document should not be greater than 45 characters.")
	public String certificateNumber;
	@Column(name = "remarks")
	@NotNull(message = "InvoiceDate is a required field.")
	@Size(min=0, max=45, message="Nature Of Document should not be greater than 45 characters.")
	public String remarks;
	@Column(name = "fy")
	@NotNull(message = "InvoiceDate is a required field.")
	@Size(min=0, max=45, message="Nature Of Document should not be greater than 45 characters.")
	public String fy;
	@Column(name = "quarter")
	@NotNull(message = "InvoiceDate is a required field.")
	@Size(min=0, max=45, message="Nature Of Document should not be greater than 45 characters.")
	public String quarter;
}
