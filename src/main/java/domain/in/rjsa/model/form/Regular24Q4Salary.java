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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "regular24Q4Salary")
public class Regular24Q4Salary extends CommonModelAbstract  {
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
	@Column(name = "srNo")
	public Long srNo;
	@Column(name = "pan")
	@NotNull(message = "PAN is a required field")
/*	@Pattern(regexp="^([a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1})|^$", message="PAN is not valid.")
*/	@Size(min=0, max=10, message="Invalid PAN number")
	public String pan;
	@Column(name = "panRefNo")
	@NotNull(message = "PAN ref number is required")
	@Size(min=0, max=45, message="Invalid PAN reference number")
	public String panRefNo;
	@Column(name = "name")
	@NotNull(message = "Name is required")
	@Size(min=0, max=45, message="Invalid name")
	public String name;
	@Column(name = "category")
	@NotNull(message = "Category is required")
	@Size(min=0, max=45, message="Invalid Category")
	public String category;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fromDateOfEmployment")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date fromDateOfEmployment;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "toDateOfEmployment")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date toDateOfEmployment;
	
	@Column(name = "grossSalaryAsPerProvision")
	@Digits(integer = 12, fraction = 0,message="Invalid Gross Salary as per Provision")
	public Double grossSalaryAsPerProvision;
	@Column(name = "valueOfPerquisites")
	@Size(min=0, max=45, message="Invalid Value of Perquisites")
	public String valueOfPerquisites;
	@Column(name = "profitInSalary")
	@Digits(integer = 12, fraction = 0,message="Invalid Profit in Salary")
	public Double profitInSalary;
	@Column(name = "taxableSalaryFromCurrentEmp")
	@NotNull(message = "Taxable Salary from Current Employee is required")
	@Digits(integer = 12, fraction = 0,message="Invalid Taxable Salary from Current Employee")
	public Double taxableSalaryFromCurrentEmp;
	@Column(name = "reportedSalaryFromOtherEmp")
	@NotNull(message = "Reported Salary from other Employee is required")
	@Digits(integer = 12, fraction = 0,message="Invalid Reported Salary from other Employee")
	public Double reportedSalaryFromOtherEmp;
	@Column(name = "totalAmountOfSalary")
	@NotNull(message = "Total amount of salary is required")
	@Digits(integer = 12, fraction = 0,message="Invalid Total amount of salary")
	public Double totalAmountOfSalary;
	@Column(name = "leaveTravelAllowance")
	@Size(min=0, max=45, message="Invalid Leave Travel Allowance")
	public String leaveTravelAllowance;
	@Column(name = "gratuity")
	@Size(min=0, max=45, message="Invalid Gratuity")
	public String gratuity;
	@Column(name = "commutedValueOfPension")
	@Digits(integer = 12, fraction = 0,message="Invalid Commuted Value of Pension")
	public Double commutedValueOfPension;
	@Column(name = "leaveEncashmentSalary")
	@Digits(integer = 12, fraction = 0,message="Invalid Leave Encashment Salary")
	public Double leaveEncashmentSalary;
	@Column(name = "houseRentAllowance")
	@Size(min=0, max=45, message="Invalid House Rent Allownace")
	public String houseRentAllowance;
	@Column(name = "anyOtherExemptionus10")
	@Digits(integer = 12, fraction = 0,message="Invalid Any other Exemtionus 10")
	public Double anyOtherExemptionus10;
	@Column(name = "totalAmountOfExemption")
	@Digits(integer = 12, fraction = 0,message="Invalid Total amount of Exemption")
	public Double totalAmountOfExemption;
	@Column(name = "entertainmentAllowance")
	@NotNull(message = "Entertainment Allowance is a required field")
	@Size(min=0, max=45, message="Invalid Entertainment Allowance")
	public String entertainmentAllowance;
	@Column(name = "pTax")
	@NotNull(message = "PTax is required")
	@Digits(integer = 12, fraction = 0,message="Invalid PTax")
	public Double pTax;
	@Column(name = "standardDeduction")
	@Digits(integer = 12, fraction = 0,message="Invalid Standard Deduction")
	public Double standardDeduction;
	@Column(name = "totalDeductionUnderSection16")
	@NotNull(message = "Total Deduction Under Section 16 is required")
	@Digits(integer = 12, fraction = 0,message="Invalid Total Deduction Under Section 16")
	public Double totalDeductionUnderSection16;
	@Column(name = "incomeFromSalary")
	@NotNull(message = "Income from Salary is a required field")
	@Digits(integer = 12, fraction = 0,message="Invalid Income from Salary")
	public Double incomeFromSalary;
	@Column(name = "incomeFromHouseProperty")
	@Digits(integer = 12, fraction = 0,message="Invalid Income from House Property")
	public Double incomeFromHouseProperty;
	@Column(name = "incomeFromOtherSources")
	@NotNull(message = "Income from other Sources is required")
	@Digits(integer = 12, fraction = 0,message="Invalid Income from other Sources")
	public Double incomeFromOtherSources;
	@Column(name = "grossTotalIncome")
	@NotNull(message = "Gross total income is required")
	@Digits(integer = 12, fraction = 0,message="Invalid Gross total income")
	public Double grossTotalIncome;
	@Column(name = "deductionUs80C")
	@Digits(integer = 12, fraction = 0,message="Invalid DeductionUs80C")
	public Double deductionUs80C;
	@Column(name = "deductionUs80CCC")
	@Digits(integer = 12, fraction = 0,message="Invalid DeductionUs80CCC")
	public Double deductionUs80CCC;
	@Column(name = "deductionUs80CCD1")
	@Digits(integer = 12, fraction = 0,message="Invalid DeductionUs80CCD1")
	public Double deductionUs80CCD1;
	@Column(name = "deductionUs80C80CCC80CCD1")
	@NotNull(message = "State is a required field")
	@Digits(integer = 12, fraction = 0,message="Invalid deductionUs80C80CCC80CCD1")
	public Double deductionUs80C80CCC80CCD1;
	@Column(name = "deductionUs80CCD1B")
	@Digits(integer = 12, fraction = 0,message="Invalid deductionUs80CCD1B")
	public Double deductionUs80CCD1B;
	@Column(name = "deductionUs80CCD2")
	@Digits(integer = 12, fraction = 0,message="Invalid deductionUs80CCD2")
	public Double deductionUs80CCD2;	
	@Column(name = "deductionUs80D")
	@Digits(integer = 12, fraction = 0,message="Invalid deductionUs80D")
	public Double deductionUs80D;
	@Column(name = "deductionUs80E")
	@Digits(integer = 12, fraction = 0,message="Invalid deductionUs80E")
	public Double deductionUs80E;
	@Column(name = "deductionUs80G")
	@Digits(integer = 12, fraction = 0,message="Invalid deductionUs80G")
	public Double deductionUs80G;
	@Column(name = "deductionUs80TTA")
	@Digits(integer = 12, fraction = 0,message="Invalid deductionUs80TTA")
	public Double deductionUs80TTA;
	@Column(name = "amountDeductible80ccg")
	@NotNull(message = "Amount Deductible 80ccg is required")
	@Digits(integer = 12, fraction = 0,message="Invalid Amount Deductible 80ccg")
	public Double amountDeductible80ccg;	
	@Column(name = "anyOtherDeduction")
	@NotNull(message = "Any other Deduction is required")
	@Digits(integer = 12, fraction = 0,message="Invalid any other deduction")
	public Double anyOtherDeduction;
	@Column(name = "totalDeductionUnderChapterVIA")
	@NotNull(message = "Total Deduction Under Chapter VIA is a required field")
	@Digits(integer = 12, fraction = 0,message="Invalid Total Deduction Under Chapter VIA")
	public Double totalDeductionUnderChapterVIA;
	@Column(name = "totalTaxableIncome")
	@NotNull(message = "Total Taxable Income is required")
	@Digits(integer = 12, fraction = 0,message="Invalid Total Taxable Income")
	public Double totalTaxableIncome;
	@Column(name = "incomeTaxOnTotalIncome")
	@NotNull(message = "Income Taxable on total income is a required field")
	@Digits(integer = 12, fraction = 0,message="Invalid Income Taxable on total income")
	public Double incomeTaxOnTotalIncome;
	@Column(name = "rebateUs87A")
	@Digits(integer = 12, fraction = 0,message="Invalid RebateUs87A")
	public Double rebateUs87A;
	@Column(name = "surcharge")
	@NotNull(message = "Surcharge is required")
	@Digits(integer = 12, fraction = 0,message="Invalid Surcharge")
	public Double surcharge;
	@Column(name = "educationCess")
	@NotNull(message = "Education Cess is a required field")
	@Digits(integer = 12, fraction = 0,message="Invalid Education Cess")
	public Double educationCess;
	@Column(name = "incomeTaxReliefUs89")
	@NotNull(message = "Income Tax Relief Us89 is required")
	@Digits(integer = 12, fraction = 0,message="Invalid Income Tax Relief Us89")
	public Double incomeTaxReliefUs89;
	@Column(name = "netIncomeTaxPayable")
	@NotNull(message = "Net Income Tax Payable is required")
	@Digits(integer = 12, fraction = 0,message="Invalid Net Income Tax Payable")
	public Double netIncomeTaxPayable;
	@Column(name = "tdsFromCurrentEmployer")
	@Digits(integer = 12, fraction = 0,message="Invalid Tds From Current Employer")
	public Double tdsFromCurrentEmployer;
	@Column(name = "reportedTdsFromPreviousEmployer")
	@NotNull(message = "Reported Tds From Previous Employer is required")
	@Digits(integer = 12, fraction = 0,message="Invalid Reported Tds From Previous Employer")
	public Double reportedTdsFromPreviousEmployer;
	@Column(name = "totalTds")
	@NotNull(message = "Total Tds is a required field")
	@Digits(integer = 12, fraction = 0,message="Invalid Total Tds")
	public Double totalTds;
	@Column(name = "shortfallInTaxDeduction")
	@NotNull(message = "Short fall In Tax Deduction is required")
	@Digits(integer = 12, fraction = 0,message="Invalid Short fall In Tax Deduction")
	public Double shortfallInTaxDeduction;
	@Column(name = "whetherTaxDeductedAtHigherRate")
	@NotNull(message = "Whether Tax Deducted At Higher Rate is required")
	@Size(min=0, max=45, message="Invalid Whether Tax Deducted At Higher Rate")
	public String whetherTaxDeductedAtHigherRate;
	@Column(name = "countOfPanOfLandlord")
	@Digits(integer = 12, fraction = 0,message="Invalid Count Of Pan Of Landlord")
	public Long countOfPanOfLandlord;
	@Column(name = "panOfLandlord1")
	@Size(min=0, max=45, message="Invalid Pan Of Landlord1")
	public String panOfLandlord1;
	@Column(name = "nameOfLandlord1")
	@Size(min=0, max=45, message="Invalid Name Of Landlord1")
	public String nameOfLandlord1;
	@Column(name = "panOfLandlord2")
	@Size(min=0, max=45, message="Invalid Pan Of Landlord2")
	public String panOfLandlord2;
	@Column(name = "nameOfLandlord2")
	@Size(min=0, max=45, message="Invalid Name Of Landlord2")
	public String nameOfLandlord2;
	@Column(name = "panOfLandlord3")
	@Size(min=0, max=45, message="Invalid Pan Of Landlord3")
	public String panOfLandlord3;
	@Column(name = "nameOfLandlord3")
	@Size(min=0, max=45, message="Invalid Name Of Landlord3")
	public String nameOfLandlord3;
	@Column(name = "panOfLandlord4")
	@Size(min=0, max=45, message="Invalid Pan Of Landlord4")
	public String panOfLandlord4;
	@Column(name = "nameOfLandlord4")
	@Size(min=0, max=45, message="Invalid Name Of Landlord4")
	public String nameOfLandlord4;
	@Column(name = "whetherInterestPaidToBeLender")
	@Size(min=0, max=45, message="Invalid Whether Interest Paid To Be Lender")
	public String whetherInterestPaidToBeLender;
	@Column(name = "countOfPanOfLender")
	@Digits(integer = 12, fraction = 0,message="Invalid Count Of Pan Of Lender")
	public Long countOfPanOfLender;
	@Column(name = "panOfLender1")
	@Size(min=0, max=45, message="Invalid Pan Of Lender1")
	public String panOfLender1;
	@Column(name = "nameOfLender1")
	@Size(min=0, max=45, message="Invalid Name Of Lender1")
	public String nameOfLender1;
	@Column(name = "panOfLender2")
	@Size(min=0, max=45, message="Invalid Pan Of Lender2")
	public String panOfLender2;
	@Column(name = "nameOfLender2")
	@Size(min=0, max=45, message="Invalid Name Of Lender2")
	public String nameOfLender2;
	@Column(name = "panOfLender3")
	@Size(min=0, max=45, message="Invalid Pan Of Lender3")
	public String panOfLender3;
	@Column(name = "nameOfLender3")
	@Size(min=0, max=45, message="Invalid Name Of Lender3")
	public String nameOfLender3;
	@Column(name = "panOfLender4")
	@Size(min=0, max=45, message="Invalid Pan Of Lender4")
	public String panOfLender4;
	@Column(name = "nameOfLender4")
	@Size(min=0, max=45, message="Invalid Name Of Lender4")
	public String nameOfLender4;
	@Column(name = "contributionPaidByTrustees")
	@Digits(integer = 12, fraction = 0,message="Invalid Contribution Paid By Trustees")
	public Double contributionPaidByTrustees;
	
	@Column(name = "nameOfTheSuperAnnuationFund")
	@Size(min=0, max=45, message="Invalid Name Of The Super Annuation Fund")
	public String nameOfTheSuperAnnuationFund;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "SuperannuationFundFromDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date SuperannuationFundFromDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "SuperannuationFundToDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date SuperannuationFundToDate;
	
	
	@Column(name = "contributionSuperannuationFund")
	@Digits(integer = 12, fraction = 0,message="Invalid Contribution Super Annuation Fund")
	public Double contributionSuperannuationFund;
	@Column(name = "averageRateOfDeduction")
	@Digits(integer = 12, fraction = 0,message="Invalid Average Rate Of Deduction")
	public Double averageRateOfDeduction;
	@Column(name = "taxDeductedOfSuperannuationFund")
	@Digits(integer = 12, fraction = 0,message="Invalid Tax Deducted Of Super Annuation Fund")
	public Double taxDeductedOfSuperannuationFund;
	@Column(name = "grossTotalIncomeincludingContribution")
	@Digits(integer = 12, fraction = 0,message="Invalid Gross Total Income including Contribution")
	public Double grossTotalIncomeincludingContribution;
	
	@Column(name = "fy")
	@Size(min=0, max=45, message="Invalid fy")
	public String fy;
	@Column(name = "quarter")
	@Size(min=0, max=45, message="Invalid quarter")
	public String quarter;
	
	
	
	
}
