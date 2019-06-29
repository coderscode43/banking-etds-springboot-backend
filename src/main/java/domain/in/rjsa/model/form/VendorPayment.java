package domain.in.rjsa.model.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "vendorPayment")
public class VendorPayment extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "branchId")
	public Long branchId;
	@Column(name = "vendorNo")
	public String vendorNo;
	@Column(name = "vendorName")
	public String vendorName;
	@Column(name = "vendorPAN")
	public String vendorPAN;
	
	@Column(name = "invoiceDate")
	public Date invoiceDate;
	@Column(name = "paymentDone")
	public String paymentDone;	
	@Column(name = "paymentDate")
	public Date paymentDate;
	@Column(name = "paymentMode")
	public String paymentMode;
	@Column(name = "invoiceNumber")
	public String invoiceNumber;
	@Column(name = "totalInvoiceValue")
	public Double totalInvoiceValue;
	@Column(name = "taxableValue")
	public Double taxableValue;
	@Column(name = "cgst")
	public Double cgst;
	@Column(name = "sgst")
	public Double sgst;
	@Column(name = "igst")
	public Double igst;
	@Column(name = "cess")
	public String cess;
	@Column(name = "incomeTaxTds")
	public Double incomeTaxTds;
	@Column(name = "gstTds")
	public Double gstTds;
	@Column(name = "natureOfPayment")
	public String natureOfPayment;
	@Column(name = "netAmountPaid")
	public Double netAmountPaid;
	@Column(name = "bglCode")
	public String bglCode;
	@Column(name = "ourGst")
	public String ourGst;
}
