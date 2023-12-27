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
@Table(name = "AAACU3561B_2324.regular26QDeductee")
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
	
	@Column(name = "pan")
	public String pan;
	
	@Column(name = "name")
	public String name;
	
	@Column(name = "sectionCode")
	public String sectionCode;
	
	@Column(name = "dateOfPayment")
	@Temporal(TemporalType.DATE)
	public Date dateOfPayment;
	
	@Column(name = "amountPaid")
	public Double amountPaid;
	
	@Column(name = "tds")
	public Double tds;
	
	@Column(name = "surcharge")
	public Double surcharge;
	
	@Column(name = "eduCess")
	public Double eduCess;
	
	@Column(name = "totalTaxDeducted")
	public Double totalTaxDeducted;
	
	@Column(name = "totalTaxDeposited")
	public Double totalTaxDeposited;
	
	@Column(name = "dateOfDeduction")
	@Temporal(TemporalType.DATE)
	public Date dateOfDeduction;
	
	@Column(name = "rateAtWhichTaxCollected")
	public Double rateAtWhichTaxCollected;
	
	@Column(name = "remarksReason")
	public String remarksReason;
	
	@Column(name = "certificateNumber")
	public String certificateNumber;
	
	@Column(name = "fy")
	public String fy;
	
	@Column(name = "quarter")
	public String quarter;
	
	@Column(name = "branchCode")
	public Long branchCode;
	
	@Column(name = "accNo")
	public String accNo;
	
	@Column(name = "challanSrNo")
	public Long challanSrNo;

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
	public String roCode ;
	
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
	
	@Column(name = "deducteeSrNo")
	public Long deducteeSrNo;
	
	@Column(name = "tranAmt")
	public Long tranAmt;
	
	@Column(name = "additionalDetail")
	public String additionalDetail;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateOfDeduction() {
		return dateOfDeduction;
	}
	public void setDateOfDeduction(Date dateOfDeduction) {
		this.dateOfDeduction = dateOfDeduction;
	}
	public void updateAllowedFields(Regular26QDeductee entity) {
		// TODO Auto-generated method stub
//		this.accNo=regular26qWeb.getAccNo();
		this.branchCode=entity.getBranchCode();
//		this.cashWithdrawal194N=regular26qWeb.getCashWithdrawal194N();
//		this.cashWithdrawal194N1Cr=regular26qWeb.getCashWithdrawal194N1Cr();
//		this.cashWithdrawal194N20Lto1Cr=regular26qWeb.getCashWithdrawal194N20Lto1Cr();
//		this.certificateNo=regular26qWeb.getCertificateNo();
//		this.challanHeading=regular26qWeb.getChallanHeading();
//		this.comments=regular26qWeb.getComments();
//		this.deductDate=regular26qWeb.getDeductDate();
//		this.deducteeCode=regular26qWeb.getDeducteeCode();
//		this.deducteeName=regular26qWeb.getDeducteeName();
//		this.deducteePan=regular26qWeb.getDeducteePan();
//		this.deducteeRefNo=regular26qWeb.getDeducteeRefNo();
//		this.eduCess=regular26qWeb.getEduCess();
//		this.errorDescription=regular26qWeb.getErrorDescription();
//		this.fy=regular26qWeb.getFy();
//		this.idNo=regular26qWeb.getIdNo();
//		this.InterestOnLateDeduction=regular26qWeb.getInterestOnLateDeduction();
//		this.InterestOnLatePayment=regular26qWeb.getInterestOnLatePayment();
//		this.InterestOnShortDeduction=regular26qWeb.getInterestOnShortDeduction();
//		this.month=regular26qWeb.getMonth();
//		this.paymentDate=regular26qWeb.getPaymentDate();
//		this.quarter=regular26qWeb.getQuarter();
//		this.rateTaxDeduct=regular26qWeb.getRateTaxDeduct();
//		this.remarks=regular26qWeb.getRemarks();
		this.roCode=entity.getRoCode();
//		this.sectionCode=regular26qWeb.getSectionCode();
//		this.ShortDeduction=regular26qWeb.getShortDeduction();
//		this.surcharge=regular26qWeb.getSurcharge();
//		this.TAN=regular26qWeb.getTAN();
//		this.tds=regular26qWeb.getTds();
//		this.totalTaxDeduct=regular26qWeb.getTotalTaxDeduct();
//		this.totalTaxDeposit=regular26qWeb.getTotalTaxDeposit();
//		this.uniqueRefNo=regular26qWeb.getUniqueRefNo();
//		this.warningDescription=regular26qWeb.getWarningDescription();
//		this.tranAmt = entuty.getTranAmt();
//		this.additionalDetail = entuty.getAdditionalDetail();
	}
}
