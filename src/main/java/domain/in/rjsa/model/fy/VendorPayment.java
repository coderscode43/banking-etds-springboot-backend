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
@Table(name = "FYDetails.vendorPayment")
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
	@Column(name = "vendorId")
	public Long vendorId;
	@Column(name = "deducteeId")
	public Long deducteeId;
	@Column(name = "challanHeading")
	public String challanHeading;
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
	
//	@Column(name = "paymentDone")
//	@Size(min=0, max=45, message="Payment Done should be 45 characters.")
//	@NotNull(message = "Payment Done is a required field")
//	public String paymentDone;	
	
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
	@Digits(integer = 12, fraction = 0,message="Enter valid cess it should not be greater than 12")
	@NotNull(message = "cess is a required field")
	public Double cess;
	
	@Column(name = "surcharge")
	@Digits(integer = 12, fraction = 0,message="Enter valid surcharge it should not be greater than 12")
	@NotNull(message = "surcharge is a required field")
	public Double surcharge;
	
	
	@Column(name = "incomeTaxTds")
//	@Digits(integer = 12, fraction = 0,message="Enter valid Income Tax Tds it should not be greater than 12")
//	@NotNull(message = "Income Tax Tds is a required field")
	public Double incomeTaxTds;
	@Column(name = "gstTds")
//	@Digits(integer = 12, fraction = 0,message="Enter valid GST TDS it should not be greater than 12")
	//@NotNull(message = "Income GST TDS is a required field")
	public Double gstTds;
	@Column(name = "natureOfPayment")
	@Size(min=0, max=45, message="Nature Of Payment should be 45 characters.")
	@NotNull(message = "Nature Of Payment is a required field")
	public String natureOfPayment;
	@Column(name = "netAmountPaid")
//	@Digits(integer = 12, fraction = 0,message="Enter valid Net Amount Paid it should not be greater than 12")
//	@NotNull(message = "Net Amount Paid is a required field")
	public Double netAmountPaid;
	@Column(name = "blgCode")
	@Size(min=0, max=45, message="BLG Code should be 45 characters.")
	@NotNull(message = "BLG Code is a required field")
	public String blgCode;
	@Column(name = "branchCode")
	public Long branchCode;
	@Column(name = "accNo")
	public Long accNo;
	@Column(name = "idNo")
	public Long idNo;
	@Column(name = "cif")
	public String cif;
	@Column(name = "type")
	public String type;
	@Column(name = "month")
	public String month;
	@Column(name = "remark")
	public String remark;
	
//	@Column(name = "ourGst")
//	@Size(min=0, max=45, message="Invoice Number should be 45 characters.")
//	@NotNull(message = "Invoice Number is a required field")
//	public String ourGst;
	
	@Column(name = "gstNo")
	@Size(min=0, max=45, message="GST No should be 45 characters.")
	@NotNull(message = "GST No is a required field")
	public String gstNo;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
}
