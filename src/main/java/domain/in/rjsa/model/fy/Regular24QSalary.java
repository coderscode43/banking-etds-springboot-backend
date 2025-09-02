package domain.in.rjsa.model.fy;

import java.text.ParseException;
import java.util.Date;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.in.rjsa.model.form.CommonModelAbstract;
import domain.in.rjsa.util.JsonDateSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Regular24QSalary extends CommonModelAbstract {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Long id;
	public Long branchId;
	public Long srNo;
	@NotBlank(message = "PAN is required")
	@Size(min = 0, max = 10, message = "PAN size should not be greater than 10")
	public String pan;
	@NotNull(message = "PAN ref number is required")
	@Size(min = 0, max = 45, message = "Invalid PAN reference number")
	public String panRefNo;
	@NotNull(message = "Name is required")
	@Size(min = 0, max = 45, message = "Invalid name")
	public String name;
	@NotNull(message = "Category is required")
	@Size(min = 0, max = 45, message = "Invalid Category")
	public String category;

	@NotNull(message = "From Date Of Employment is required")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date fromDateOfEmployment;

	@NotNull(message = "To Date Of Employment is required")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date toDateOfEmployment;

	@NotNull(message = "Gross Salary As Per Provision is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Gross Salary as per Provision")
	public Double grossSalaryAsPerProvision;
	@NotNull(message = "Value of perquisites is required")
	@Size(min = 0, max = 45, message = "Invalid Value of Perquisites")
	public String valueOfPerquisites;
	@NotNull(message = "Profit in Salary is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Profit in Salary")
	public Double profitInSalary;
	@NotNull(message = "Taxable Salary from Current Employee is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Taxable Salary from Current Employee")
	public Double taxableSalaryFromCurrentEmp;
	@NotNull(message = "Reported Salary from other Employee is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Reported Salary from other Employee")
	public Double reportedSalaryFromOtherEmp;
	@NotNull(message = "Total amount of salary is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Total amount of salary")
	public Double totalAmountOfSalary;
	@NotNull(message = "Leave Travel Allowance is required")
	@Size(min = 0, max = 45, message = "Invalid Leave Travel Allowance")
	public String leaveTravelAllowance;
	@NotNull(message = "Gratuity is required")
	@Size(min = 0, max = 45, message = "Invalid Gratuity")
	public String gratuity;
	@NotNull(message = "Commuted value is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Commuted Value of Pension")
	public Double commutedValueOfPension;
	@NotNull(message = "Leave Encashment is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Leave Encashment Salary")
	public Double leaveEncashmentSalary;
	@NotNull(message = "HouseRent Allowance is required")
	@Size(min = 0, max = 45, message = "Invalid House Rent Allownace")
	public String houseRentAllowance;
	@NotNull(message = "Any Other exemptionus 10 is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Any other Exemtionus 10")
	public Double anyOtherExemptionus10;
	@NotNull(message = "Total Amount of Exemption is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Total amount of Exemption")
	public Double totalAmountOfExemption;
	@NotNull(message = "Entertainment Allowance is a required field")
	@Size(min = 0, max = 45, message = "Invalid Entertainment Allowance")
	public String entertainmentAllowance;
	@NotNull(message = "PTax is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid PTax")
	public Double pTax;
	@NotNull(message = "Standard Deduction is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Standard Deduction")
	public Double standardDeduction;
	@NotNull(message = "Total Deduction Under Section 16 is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Total Deduction Under Section 16")
	public Double totalDeductionUnderSection16;
	@NotNull(message = "Income from Salary is a required field")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Income from Salary")
	public Double incomeFromSalary;
	@NotNull(message = "Income from House Property is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Income from House Property")
	public Double incomeFromHouseProperty;
	@NotNull(message = "Income from other Sources is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Income from other Sources")
	public Double incomeFromOtherSources;
	@NotNull(message = "Gross total income is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Gross total income")
	public Double grossTotalIncome;
	@NotNull(message = "DeductionUs80C is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid DeductionUs80C")
	public Double deductionUs80C;
	@NotNull(message = "DeductionUs80CCC is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid DeductionUs80CCC")
	public Double deductionUs80CCC;
	@NotNull(message = "DeductionUs80CCD1 is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid DeductionUs80CCD1")
	public Double deductionUs80CCD1;
	@NotNull(message = "DeductionUs80C80CCC80CCD1 is a required field")
	//@Digits(integer = 12, fraction = 2, message = "Invalid deductionUs80C80CCC80CCD1")
	public Double deductionUs80C80CCC80CCD1;
	@NotNull(message = "DeductionUs80CCD1B is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid deductionUs80CCD1B")
	public Double deductionUs80CCD1B;
	@NotNull(message = "DeductionUs80CCD2 is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid deductionUs80CCD2")
	public Double deductionUs80CCD2;
	@NotNull(message = "DeductionUs80D is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid deductionUs80D")
	public Double deductionUs80D;
	@NotNull(message = "DeductionUs80E is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid deductionUs80E")
	public Double deductionUs80E;
	@NotNull(message = "DeductionUs80G is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid deductionUs80G")
	public Double deductionUs80G;
	@NotNull(message = "DeductionUs80TTA is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid deductionUs80TTA")
	public Double deductionUs80TTA;
	@NotNull(message = "Amount Deductible 80ccg is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Amount Deductible 80ccg")
	public Double amountDeductible80ccg;
	@NotNull(message = "Any other Deduction is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid any other deduction")
	public Double anyOtherDeduction;
	@NotNull(message = "Total Deduction Under Chapter VIA is a required field")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Total Deduction Under Chapter VIA")
	public Double totalDeductionUnderChapterVIA;
	@NotNull(message = "Total Taxable Income is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Total Taxable Income")
	public Double totalTaxableIncome;
	@NotNull(message = "Income Taxable on total income is a required field")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Income Taxable on total income")
	public Double incomeTaxOnTotalIncome;
	@NotNull(message = "RebateUs87A is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid RebateUs87A")
	public Double rebateUs87A;
	@NotNull(message = "Surcharge is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Surcharge")
	public Double surcharge;
	@NotNull(message = "Education Cess is a required field")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Education Cess")
	public Double educationCess;
	@NotNull(message = "Income Tax Relief Us89 is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Income Tax Relief Us89")
	public Double incomeTaxReliefUs89;
	@NotNull(message = "Net Income Tax Payable is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Net Income Tax Payable")
	public Double netIncomeTaxPayable;
	@NotNull(message = "TDS from Current Employer is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Tds From Current Employer")
	public Double tdsFromCurrentEmployer;
	@NotNull(message = "Reported Tds From Previous Employer is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Reported Tds From Previous Employer")
	public Double reportedTdsFromPreviousEmployer;
	@NotNull(message = "Total Tds is a required field")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Total Tds")
	public Double totalTds;
	@NotNull(message = "Short fall In Tax Deduction is required")
	//@Digits(integer = 12, fraction = 2, message = "Invalid Short fall In Tax Deduction")
	public Double shortfallInTaxDeduction;
	@NotNull(message = "Whether Tax Deducted At Higher Rate is required")
	@Size(min = 0, max = 45, message = "Invalid Whether Tax Deducted At Higher Rate")
	public String whetherTaxDeductedAtHigherRate;
	// @NotBlank(message = "Count of PAN of Landlord is required")
	//@Digits(integer = 12, fraction = 0, message = "Invalid Count Of Pan Of Landlord")
	public Long countOfPanOfLandlord;
	@Size(min = 0, max = 45, message = "Invalid Pan Of Landlord1")
	public String panOfLandlord1;
	@Size(min = 0, max = 45, message = "Invalid Name Of Landlord1")
	public String nameOfLandlord1;
	@Size(min = 0, max = 45, message = "Invalid Pan Of Landlord2")
	public String panOfLandlord2;
	@Size(min = 0, max = 45, message = "Invalid Name Of Landlord2")
	public String nameOfLandlord2;
	@Size(min = 0, max = 45, message = "Invalid Pan Of Landlord3")
	public String panOfLandlord3;
	@Size(min = 0, max = 45, message = "Invalid Name Of Landlord3")
	public String nameOfLandlord3;
	@Size(min = 0, max = 45, message = "Invalid Pan Of Landlord4")
	public String panOfLandlord4;
	@Size(min = 0, max = 45, message = "Invalid Name Of Landlord4")
	public String nameOfLandlord4;
	@NotNull(message = "Whether Interest Paid to be lender is required")
	@Size(min = 0, max = 45, message = "Invalid Whether Interest Paid To Be Lender")
	public String whetherInterestPaidToBeLender;
	// @NotNull(message = "Count of PAN of lender is required")
	//@Digits(integer = 12, fraction = 0, message = "Invalid Count Of Pan Of Lender")
	public Long countOfPanOfLender;
	@Size(min = 0, max = 45, message = "Invalid Pan Of Lender1")
	public String panOfLender1;
	@Size(min = 0, max = 45, message = "Invalid Name Of Lender1")
	public String nameOfLender1;
	@Size(min = 0, max = 45, message = "Invalid Pan Of Lender2")
	public String panOfLender2;
	@Size(min = 0, max = 45, message = "Invalid Name Of Lender2")
	public String nameOfLender2;
	@Size(min = 0, max = 45, message = "Invalid Pan Of Lender3")
	public String panOfLender3;
	@Size(min = 0, max = 45, message = "Invalid Name Of Lender3")
	public String nameOfLender3;
	@Size(min = 0, max = 45, message = "Invalid Pan Of Lender4")
	public String panOfLender4;
	@Size(min = 0, max = 45, message = "Invalid Name Of Lender4")
	public String nameOfLender4;
	//@Digits(integer = 12, fraction = 2, message = "Invalid Contribution Paid By Trustees")
	public Double contributionPaidByTrustees;

	@Size(min = 0, max = 45, message = "Invalid Name Of The Super Annuation Fund")
	public String nameOfTheSuperAnnuationFund;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date SuperannuationFundFromDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date SuperannuationFundToDate;

	//@Digits(integer = 12, fraction = 2, message = "Invalid Contribution Super Annuation Fund")
	public Double contributionSuperannuationFund;
	//@Digits(integer = 12, fraction = 2, message = "Invalid Average Rate Of Deduction")
	public Double averageRateOfDeduction;
	//@Digits(integer = 12, fraction = 2, message = "Invalid Tax Deducted Of Super Annuation Fund")
	public Double taxDeductedOfSuperannuationFund;
	//@Digits(integer = 12, fraction = 2, message = "Invalid Gross Total Income including Contribution")
	public Double grossTotalIncomeincludingContribution;
	public String remarks;

	public String fy;
	@Size(min = 0, max = 45, message = "Invalid quarter")
	public String quarter;

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFromDateOfEmployment() {
		return fromDateOfEmployment;
	}

	public void setFromDateOfEmployment(Date fromDateOfEmployment) {
		this.fromDateOfEmployment = fromDateOfEmployment;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getToDateOfEmployment() {
		return toDateOfEmployment;
	}

	public void setToDateOfEmployment(Date toDateOfEmployment) {
		this.toDateOfEmployment = toDateOfEmployment;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getSuperannuationFundFromDate() {
		return SuperannuationFundFromDate;
	}

	public void setSuperannuationFundFromDate(Date SuperannuationFundFromDate) {
		this.SuperannuationFundFromDate = SuperannuationFundFromDate;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getSuperannuationFundToDate() {
		return SuperannuationFundToDate;
	}

	public void setSuperannuationFundToDate(Date SuperannuationFundToDate) {
		this.SuperannuationFundToDate = SuperannuationFundToDate;
	}

	public void setData(JSONObject dataObject) throws ParseException{
		this.branchId = Long.valueOf(dataObject.get("BRANCHID").toString());
		this.srNo = Long.valueOf(dataObject.get("SRNO").toString());
		this.pan = dataObject.get("PAN").toString();
		this.panRefNo = dataObject.get("PANREFNO").toString();
		this.name = dataObject.get("NAME").toString();
		this.category = dataObject.get("CATEGORY").toString();
		this.grossSalaryAsPerProvision = Double.valueOf(dataObject.get("GROSSSALARYASPERPROVISION").toString());
		this.valueOfPerquisites = dataObject.get("VALUEOFPERQUISITES").toString();
		this.profitInSalary = Double.valueOf(dataObject.get("PROFITINSALARY").toString());
		this.taxableSalaryFromCurrentEmp = Double.valueOf(dataObject.get("TAXABLESALARYFROMCURRENTEMP").toString());
		this.reportedSalaryFromOtherEmp = Double.valueOf(dataObject.get("REPORTEDSALARYFROMOTHEREMP").toString());
		this.totalAmountOfSalary = Double.valueOf(dataObject.get("TOTALAMOUNTOFSALARY").toString());
		this.leaveTravelAllowance = dataObject.get("LEAVETRAVELALLOWANCE").toString();
		this.gratuity = dataObject.get("GRATUITY").toString();
		this.commutedValueOfPension = Double.valueOf(dataObject.get("COMMUTEDVALUEOFPENSION").toString());
		this.leaveEncashmentSalary = Double.valueOf(dataObject.get("LEAVEENCASHMENTSALARY").toString());
		this.houseRentAllowance = dataObject.get("HOUSERENTALLOWANCE").toString();
		this.anyOtherExemptionus10 = Double.valueOf(dataObject.get("ANYOTHEREXEMPTIONUS10").toString());
		this.totalAmountOfExemption = Double.valueOf(dataObject.get("TOTALAMOUNTOFEXEMPTION").toString());
		this.entertainmentAllowance = dataObject.get("ENTERTAINMENTALLOWANCE").toString();
		this.pTax = Double.valueOf(dataObject.get("PTAX").toString());
		this.standardDeduction = Double.valueOf(dataObject.get("STANDARDDEDUCTION").toString());
		this.totalDeductionUnderSection16 = Double.valueOf(dataObject.get("TOTALDEDUCTIONUNDERSECTION16").toString());
		this.incomeFromSalary = Double.valueOf(dataObject.get("INCOMEFROMSALARY").toString());
		this.incomeFromHouseProperty = Double.valueOf(dataObject.get("INCOMEFROMHOUSEPROPERTY").toString());
		this.incomeFromOtherSources = Double.valueOf(dataObject.get("INCOMEFROMOTHERSOURCES").toString());
		this.grossTotalIncome = Double.valueOf(dataObject.get("GROSSTOTALINCOME").toString());
		this.deductionUs80C = Double.valueOf(dataObject.get("DEDUCTIONUS80C").toString());
		this.deductionUs80CCC = Double.valueOf(dataObject.get("DEDUCTIONUS80CCC").toString());
		this.deductionUs80CCD1 = Double.valueOf(dataObject.get("DEDUCTIONUS80CCD1").toString());
		this.deductionUs80C80CCC80CCD1 = Double.valueOf(dataObject.get("DEDUCTIONUS80C80CCC80CCD1").toString());
		this.deductionUs80CCD1B = Double.valueOf(dataObject.get("DEDUCTIONUS80CCD1B").toString());
		this.deductionUs80CCD2 = Double.valueOf(dataObject.get("DEDUCTIONUS80CCD2").toString());
		this.deductionUs80D = Double.valueOf(dataObject.get("DEDUCTIONUS80D").toString());
		this.deductionUs80E = Double.valueOf(dataObject.get("DEDUCTIONUS80E").toString());
		this.deductionUs80G = Double.valueOf(dataObject.get("DEDUCTIONUS80G").toString());
		this.deductionUs80TTA = Double.valueOf(dataObject.get("DEDUCTIONUS80TTA").toString());
		this.amountDeductible80ccg = Double.valueOf(dataObject.get("AMOUNTDEDUCTIBLE80CCG").toString());
		this.anyOtherDeduction = Double.valueOf(dataObject.get("ANYOTHERDEDUCTION").toString());
		this.totalDeductionUnderChapterVIA = Double.valueOf(dataObject.get("TOTALDEDUCTIONUNDERCHAPTERVIA").toString());
		this.totalTaxableIncome = Double.valueOf(dataObject.get("TOTALTAXABLEINCOME").toString());
		this.incomeTaxOnTotalIncome = Double.valueOf(dataObject.get("INCOMETAXONTOTALINCOME").toString());
		this.rebateUs87A = Double.valueOf(dataObject.get("REBATEUS87A").toString());
		this.surcharge = Double.valueOf(dataObject.get("SURCHARGE").toString());
		this.educationCess = Double.valueOf(dataObject.get("EDUCATIONCESS").toString());
		this.incomeTaxReliefUs89 = Double.valueOf(dataObject.get("INCOMETAXRELIEFUS89").toString());
		this.netIncomeTaxPayable = Double.valueOf(dataObject.get("NETINCOMETAXPAYABLE").toString());
		this.tdsFromCurrentEmployer = Double.valueOf(dataObject.get("TDSFROMCURRENTPLOYER").toString());
		this.reportedTdsFromPreviousEmployer = Double
				.valueOf(dataObject.get("REPORTEDTDSFROMPREVIOUSEMPLOYER").toString());
		this.totalTds = Double.valueOf(dataObject.get("TOTALTDS").toString());
		this.shortfallInTaxDeduction = Double.valueOf(dataObject.get("SHORTFALLINTAXDEDUCTION").toString());
		this.whetherTaxDeductedAtHigherRate = dataObject.get("WHETHERTAXDEDUCTEDATHIGHERRATE").toString();
		this.countOfPanOfLandlord = Long.valueOf(dataObject.get("COUNTOFPANOFLANDLORD").toString());
		this.panOfLandlord1 = dataObject.get("PANOFLANDLORD1").toString();
		this.nameOfLandlord1 = dataObject.get("NAMEOFLANDLORD1").toString();
		this.panOfLandlord2 = dataObject.get("PANOFLANDLORD2").toString();
		this.nameOfLandlord2 = dataObject.get("NAMEOFLANDLORD2").toString();
		this.panOfLandlord3 = dataObject.get("PANOFLANDLORD3").toString();
		this.nameOfLandlord3 = dataObject.get("NAMEOFLANDLORD3").toString();
		this.panOfLandlord4 = dataObject.get("PANOFLANDLORD4").toString();
		this.nameOfLandlord4 = dataObject.get("NAMEOFLANDLORD4").toString();
		this.whetherInterestPaidToBeLender = dataObject.get("WHETHERINTERESTPAIDTOBELENDER").toString();
		this.countOfPanOfLender = Long.valueOf(dataObject.get("COUNTOFPANOFLENDER").toString());
		this.panOfLender1 = dataObject.get("PANOFLENDER1").toString();
		this.nameOfLender1 = dataObject.get("NAMEOFLENDER1").toString();
		this.panOfLender2 = dataObject.get("PANOFLENDER2").toString();
		this.nameOfLender2 = dataObject.get("NAMEOFLENDER2").toString();
		this.panOfLender3 = dataObject.get("PANOFLENDER3").toString();
		this.nameOfLender3 = dataObject.get("NAMEOFLENDER3").toString();
		this.panOfLender4 = dataObject.get("PANOFLENDER4").toString();
		this.nameOfLender4 = dataObject.get("NAMEOFLENDER4").toString();
		this.contributionPaidByTrustees = Double.valueOf(dataObject.get("CONTRIBUTIONPAIDBYTRUSTEES").toString());
		this.nameOfTheSuperAnnuationFund = dataObject.get("NAMEOFTHESUPERANNUATIONFUND").toString();

		this.contributionSuperannuationFund = Double
				.valueOf(dataObject.get("CONTRIBUTIONSUPERANNUATIONFUND").toString());
		this.averageRateOfDeduction = Double.valueOf(dataObject.get("AVERAGERATEOFDEDUCTION").toString());
		this.taxDeductedOfSuperannuationFund = Double
				.valueOf(dataObject.get("TAXDEDUCTEDOFSUPERANNUATIONFUND").toString());
		this.grossTotalIncomeincludingContribution = Double
				.valueOf(dataObject.get("GROSSTOTALINCOMEINCLUDINGCONTRIBUTION").toString());
		this.remarks = dataObject.get("REMARKS").toString();

		this.fromDateOfEmployment = returnDate(dataObject.get("FROMDATEOFEMPLOYMENT").toString());
		this.toDateOfEmployment = returnDate(dataObject.get("TODATEOFEMPLOYMENT").toString());
		this.SuperannuationFundFromDate = returnDate(dataObject.get("SUPERANNUATIONFUNDFROMDATE").toString());
		this.SuperannuationFundToDate = returnDate(dataObject.get("SUPERANNUATIONFUNDTODATE").toString());
	}

}
