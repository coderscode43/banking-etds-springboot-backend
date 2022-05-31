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

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.in.rjsa.model.form.CommonModelAbstract;
import domain.in.rjsa.util.JsonDateSerializer;
import lombok.Data;

@Data
@Entity
@Table(name = "FYDetails.regular26QDeductee")
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
	
	@Column(name = "challanHeading")
	public String challanHeading;
	@Column(name = "deducteeRefNo")
	
	public String deducteeRefNo;
	@Column(name = "deducteeCode")
	
	public String deducteeCode;
	@Column(name = "deducteePan")
	
	public String deducteePan;
	@Column(name = "deducteeName")
	
	public String deducteeName;
	@Column(name = "sectionCode")
	
	public String sectionCode;
	@Temporal(TemporalType.DATE)
	
	public Date paymentDate;
	@Column(name = "paidAmt")
	
	public Double paidAmt;
	@Column(name = "tds")
	
	public Double tds;
	@Column(name = "surcharge")
	
	public Double surcharge;
	@Column(name = "eduCess")
	
	public Double eduCess;
	@Column(name = "totalTaxDeduct")
	
	public Double totalTaxDeduct;
	@Column(name = "totalTaxDeposit")
	
	public Double totalTaxDeposit;
	@Temporal(TemporalType.DATE)
	
	public Date deductDate;
	@Column(name = "rateTaxDeduct")
	
	public Double rateTaxDeduct;
	@Column(name = "remarks")
	
	public String remarks;
	@Column(name = "certificateNo")
	
	public String certificateNo;
	@Column(name = "fy")
	
	public String fy;
	@Column(name = "quarter")
	
	public String quarter;
	@Column(name = "branchCode")
	public Long branchCode;
	@Column(name = "accNo")
	public String accNo;
	@Column(name = "idNo")
	public Long idNo;

	@Column(name = "month")
	public String month;
	
	@Column(name = "custVendId")
	public String custVendId;
	
	@Column(name = "uniqueRefNo")
	public String uniqueRefNo;
	
	@Column(name = "cashWithdrawal194N")
	public Double cashWithdrawal194N;
	
	@Column(name = "cashWithdrawal194N20Lto1Cr")
	public Double cashWithdrawal194N20Lto1Cr;
	
	@Column(name = "cashWithdrawal194N1Cr")
	public Double cashWithdrawal194N1Cr;
	
	@Column(name = "TAN")
	public String TAN ;
	
	@Column(name = "roCode")
	public Long roCode ;
	
	@Column(name = "errorDescription")
	public String errorDescription ;
	
	@Column(name = "warningDescription")
	public String warningDescription ;
	
	@Column(name = "ShortDeduction")
	public Double ShortDeduction;
	
	@Column(name = "InterestOnShortDeduction")
	public Double InterestOnShortDeduction ;
	
	@Column(name = "InterestOnLatePayment")
	public Double InterestOnLatePayment;
	
	@Column(name = "InterestOnLateDeduction")
	public Double InterestOnLateDeduction;
	

	@Column(name = "resolved")
	public boolean resolved;
	
	@Column(name = "comments")
	public String comments;
	
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
