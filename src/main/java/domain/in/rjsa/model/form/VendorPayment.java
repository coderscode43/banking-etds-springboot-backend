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
@Table(name = "vendorPayment")
public class VendorPayment extends CommonModelAbstract{
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
	@Column(name = "vendorId")
	public Long vendorId;
	@Column(name = "vendorNo")
	@Size(min=0, max=15, message="Vendor No should be 15 characters.")
	@NotNull(message = "Vendor No is a required field")
	public String vendorNo;
	@Column(name = "vendorName")
	@Size(min=0, max=45, message="Vendor Name should be 45 characters.")
	@NotNull(message = "Vendor Name is a required field")
	public String vendorName;
	@Column(name = "vendorPAN")
	@Size(min=0, max=10, message="Vendor Pan should be 10 characters.")
	@NotNull(message = "Vendor Pan is a required field")
	public String vendorPAN;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date date;
	
	@Column(name = "paymentDone")
	@Size(min=0, max=45, message="Payment Done should be 45 characters.")
	@NotNull(message = "Payment Done is a required field")
	public String paymentDone;	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "paymentDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date paymentDate;
	
	@Column(name = "paymentMode")
	@Size(min=0, max=45, message="Payment Mode should be 45 characters.")
	@NotNull(message = "Payment Mode is a required field")
	public String paymentMode;
	@Column(name = "invoiceNumber")
	@Size(min=0, max=45, message="Invoice Number should be 45 characters.")
	@NotNull(message = "Invoice Number is a required field")
	public String invoiceNumber;
	@Column(name = "totalInvoiceValue")
	@Digits(integer = 12, fraction = 0,message="Enter valid Total Invoice Value it should not be greater than 12")
	@NotNull(message = "Total Invoice Value is a required field")
	public Double totalInvoiceValue;
	@Column(name = "taxableValue")
	@Digits(integer = 12, fraction = 0,message="Enter valid Taxable Value it should not be greater than 12")
	@NotNull(message = "Taxable Value is a required field")
	public Double taxableValue;
	@Column(name = "cgst")
	@Digits(integer = 12, fraction = 0,message="Enter valid CGST it should not be greater than 12")
	@NotNull(message = "CGST is a required field")
	public Double cgst;
	@Column(name = "sgst")
	@Digits(integer = 12, fraction = 0,message="Enter valid SGST it should not be greater than 12")
	@NotNull(message = "SGST is a required field")
	public Double sgst;
	@Column(name = "igst")
	@Digits(integer = 12, fraction = 0,message="Enter valid IGST it should not be greater than 12")
	@NotNull(message = "IGST is a required field")
	public Double igst;
	@Column(name = "cess")
	@Size(min=0, max=45, message="Education Cess should be 45 characters.")
	@NotNull(message = "Education Cess is a required field")
	public String cess;
	@Column(name = "incomeTaxTds")
	@Digits(integer = 12, fraction = 0,message="Enter valid Income Tax Tds it should not be greater than 12")
	@NotNull(message = "Income Tax Tds is a required field")
	public Double incomeTaxTds;
	@Column(name = "gstTds")
	@Digits(integer = 12, fraction = 0,message="Enter valid GST TDS it should not be greater than 12")
	@NotNull(message = "Income GST TDS is a required field")
	public Double gstTds;
	@Column(name = "natureOfPayment")
	@Size(min=0, max=45, message="Nature Of Payment should be 45 characters.")
	@NotNull(message = "Nature Of Payment is a required field")
	public String natureOfPayment;
	@Column(name = "netAmountPaid")
	@Digits(integer = 12, fraction = 0,message="Enter valid Net Amount Paid it should not be greater than 12")
	@NotNull(message = "Net Amount Paid is a required field")
	public Double netAmountPaid;
	@Column(name = "bglCode")
	@Size(min=0, max=45, message="BLG Code should be 45 characters.")
	@NotNull(message = "BLG Code is a required field")
	public String bglCode;
	@Column(name = "ourGst")
	@Size(min=0, max=45, message="Invoice Number should be 45 characters.")
	@NotNull(message = "Invoice Number is a required field")
	public String ourGst;
	@Column(name = "gstNo")
	@Size(min=0, max=45, message="GST No should be 45 characters.")
	@NotNull(message = "GST No is a required field")
	public String gstNo;
}
