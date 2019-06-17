package domain.in.rjsa.model;

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
@Table(name = "regular24Q4Salary")
public class Regular24Q4Salary {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;	
	@Column(name = "srNo")
	public Long srNo;
	@Column(name = "pan")
	public String pan;
	@Column(name = "panRefNo")
	public String panRefNo;
	@Column(name = "name")
	public String name;
	@Column(name = "category")
	public String category;
	@Column(name = "fromDateOfEmployment")
	public Date fromDateOfEmployment;
	@Column(name = "toDateOfEmployment")
	public Date toDateOfEmployment;
	@Column(name = "grossSalaryAsPerProvision")
	public Double grossSalaryAsPerProvision;
	@Column(name = "valueOfPerquisites")
	public String valueOfPerquisites;
	@Column(name = "profitInSalary")
	public Double profitInSalary;
	@Column(name = "taxableSalaryFromCurrentEmp")
	public Double taxableSalaryFromCurrentEmp;
	@Column(name = "reportedSalaryFromOtherEmp")
	public Double reportedSalaryFromOtherEmp;
	@Column(name = "totalAmountOfSalary")
	public Double totalAmountOfSalary;
	@Column(name = "leaveTravelAllowance")
	public String leaveTravelAllowance;
	@Column(name = "gratuity")
	public String gratuity;
	@Column(name = "commutedValueOfPension")
	public Double commutedValueOfPension;
	@Column(name = "leaveEncashmentSalary")
	public Double leaveEncashmentSalary;
	@Column(name = "houseRentAllowance")
	public String houseRentAllowance;
	@Column(name = "anyOtherExemptionus10")
	public Double anyOtherExemptionus10;
	@Column(name = "totalAmountOfExemption")
	public Double totalAmountOfExemption;
	@Column(name = "entertainmentAllowance")
	public String entertainmentAllowance;
	@Column(name = "pTax")
	public Double pTax;
	@Column(name = "standardDeduction")
	public Double standardDeduction;
	@Column(name = "totalDeductionUnderSection16")
	public Double totalDeductionUnderSection16;
	@Column(name = "incomeFromSalary")
	public Double incomeFromSalary;
	@Column(name = "incomeFromHouseProperty")
	public Double incomeFromHouseProperty;
	@Column(name = "incomeFromOtherSources")
	public Double incomeFromOtherSources;
	@Column(name = "grossTotalIncome")
	public Double grossTotalIncome;
	@Column(name = "deductionUs80C")
	public Double deductionUs80C;
	@Column(name = "deductionUs80CCC")
	public Double deductionUs80CCC;
	@Column(name = "deductionUs80CCD1")
	public Double deductionUs80CCD1;
	@Column(name = "deductionUs80C80CCC80CCD1")
	public Double deductionUs80C80CCC80CCD1;
	@Column(name = "deductionUs80CCD1B")
	public Double deductionUs80CCD1B;
	@Column(name = "deductionUs80CCD2")
	public Double deductionUs80CCD2;	
	@Column(name = "deductionUs80D")
	public Double deductionUs80D;
	@Column(name = "deductionUs80E")
	public Double deductionUs80E;
	@Column(name = "deductionUs80G")
	public Double deductionUs80G;
	@Column(name = "deductionUs80TTA")
	public Double deductionUs80TTA;
	@Column(name = "amountDeductible80ccg")
	public Double amountDeductible80ccg;
	
	@Column(name = "anyOtherDeduction")
	public Double anyOtherDeduction;
	@Column(name = "totalDeductionUnderChapterVIA")
	public Double totalDeductionUnderChapterVIA;
	@Column(name = "totalTaxableIncome")
	public Double totalTaxableIncome;
	@Column(name = "incomeTaxOnTotalIncome")
	public Double incomeTaxOnTotalIncome;
	@Column(name = "rebateUs87A")
	public Double rebateUs87A;
	@Column(name = "surcharge")
	public Double surcharge;
	@Column(name = "educationCess")
	public Double educationCess;
	@Column(name = "incomeTaxReliefUs89")
	public Double incomeTaxReliefUs89;
	@Column(name = "netIncomeTaxPayable")
	public Double netIncomeTaxPayable;
	@Column(name = "tdsFromCurrentEmployer")
	public Double tdsFromCurrentEmployer;
	@Column(name = "reportedTdsFromPreviousEmployer")
	public Double reportedTdsFromPreviousEmployer;
	@Column(name = "totalTds")
	public Double totalTds;
	@Column(name = "shortfallInTaxDeduction")
	public Double shortfallInTaxDeduction;
	@Column(name = "whetherTaxDeductedAtHigherRate")
	public String whetherTaxDeductedAtHigherRate;
	@Column(name = "countOfPanOfLandlord")
	public Long countOfPanOfLandlord;
	@Column(name = "panOfLandlord1")
	public String panOfLandlord1;
	@Column(name = "nameOfLandlord1")
	public String nameOfLandlord1;
	@Column(name = "panOfLandlord2")
	public String panOfLandlord2;
	@Column(name = "nameOfLandlord2")
	public String nameOfLandlord2;
	@Column(name = "panOfLandlord3")
	public String panOfLandlord3;
	@Column(name = "nameOfLandlord3")
	public String nameOfLandlord3;
//	@Column(name = "panOfLandlord4")
//	public String panOfLandlord4;
//	@Column(name = "nameOfLandlord4")
//	public String nameOfLandlord4;
//	@Column(name = "whetherInterestPaidToBeLender")
//	public String whetherInterestPaidToBeLender;
//	@Column(name = "countOfPanOfLender")
//	public Long countOfPanOfLender;
//	@Column(name = "panOfLender1")
//	public String panOfLender1;
//	@Column(name = "nameOfLender1")
//	public String nameOfLender1;
//	@Column(name = "panOfLandlord4")
//	public String panOfLandlord4;
//	@Column(name = "panOfLandlord4")
//	public String panOfLandlord4;
//	@Column(name = "panOfLandlord4")
//	public String panOfLandlord4;
//	@Column(name = "panOfLandlord4")
//	public String panOfLandlord4;
//	@Column(name = "panOfLandlord4")
//	public String panOfLandlord4;
	
	
	
	
	
	
}
