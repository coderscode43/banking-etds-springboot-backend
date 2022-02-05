package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.Regular24QSalaryDao;
import domain.in.rjsa.model.fy.Regular24QSalary;

@Repository("regular24QSalaryDao")
public class Regular24QSalaryDaoImpl extends AbstractDaoFY<Long, Regular24QSalary> implements Regular24QSalaryDao {
	@SuppressWarnings("unchecked")
	public List<Regular24QSalary> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));

		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}
		if (entity.get("panRefNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("panRefNo", entity.get("panRefNo")));
		}
		
		if (entity.get("name") != null) {
			criteria.add(Restrictions.eqOrIsNull("name", entity.get("name")));
		}
		if (entity.get("category") != null) {
			criteria.add(Restrictions.eqOrIsNull("category", entity.get("category")));
		}
		if (entity.get("grossSalaryAsPerProvision") != null) {
			criteria.add(Restrictions.eqOrIsNull("grossSalaryAsPerProvision", Double.valueOf((String) entity.get("grossSalaryAsPerProvision"))));
		}
		if (entity.get("valueOfPerquisites") != null) {
			criteria.add(Restrictions.eqOrIsNull("valueOfPerquisites", entity.get("valueOfPerquisites")));
		}
		if (entity.get("profitInSalary") != null) {
			criteria.add(Restrictions.eqOrIsNull("profitInSalary", Double.valueOf((String) entity.get("profitInSalary"))));
		}
		if (entity.get("taxableSalaryFromCurrentEmp") != null) {
			criteria.add(Restrictions.eqOrIsNull("taxableSalaryFromCurrentEmp", Double.valueOf((String) entity.get("taxableSalaryFromCurrentEmp"))));
		}
		if (entity.get("reportedSalaryFromOtherEmp") != null) {
			criteria.add(Restrictions.eqOrIsNull("reportedSalaryFromOtherEmp", Double.valueOf((String) entity.get("reportedSalaryFromOtherEmp"))));
		}
		if (entity.get("totalAmountOfSalary") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalAmountOfSalary", Double.valueOf((String) entity.get("totalAmountOfSalary"))));
		}
		if (entity.get("leaveTravelAllowance") != null) {
			criteria.add(Restrictions.eqOrIsNull("leaveTravelAllowance", entity.get("leaveTravelAllowance")));
		}
		if (entity.get("gratuity") != null) {
			criteria.add(Restrictions.eqOrIsNull("gratuity", entity.get("gratuity")));
		}
		if (entity.get("commutedValueOfPension") != null) {
			criteria.add(Restrictions.eqOrIsNull("commutedValueOfPension", Double.valueOf((String) entity.get("commutedValueOfPension"))));
		}
		if (entity.get("leaveEncashmentSalary") != null) {
			criteria.add(Restrictions.eqOrIsNull("leaveEncashmentSalary", Double.valueOf((String) entity.get("leaveEncashmentSalary"))));
		}
		if (entity.get("houseRentAllowance") != null) {
			criteria.add(Restrictions.eqOrIsNull("houseRentAllowance", entity.get("houseRentAllowance")));
		}
		if (entity.get("anyOtherExemptionus10") != null) {
			criteria.add(Restrictions.eqOrIsNull("anyOtherExemptionus10", Double.valueOf((String) entity.get("anyOtherExemptionus10"))));
		}
		if (entity.get("totalAmountOfExemption") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalAmountOfExemption", Double.valueOf((String) entity.get("totalAmountOfExemption"))));
		}
		if (entity.get("entertainmentAllowance") != null) {
			criteria.add(Restrictions.eqOrIsNull("entertainmentAllowance", entity.get("entertainmentAllowance")));
		}
		if (entity.get("pTax") != null) {
			criteria.add(Restrictions.eqOrIsNull("pTax", Double.valueOf((String) entity.get("pTax"))));
		}
		if (entity.get("standardDeduction") != null) {
			criteria.add(Restrictions.eqOrIsNull("standardDeduction", Double.valueOf((String) entity.get("standardDeduction"))));
		}
		if (entity.get("totalDeductionUnderSection16") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalDeductionUnderSection16", Double.valueOf((String) entity.get("totalDeductionUnderSection16"))));
		}
		if (entity.get("incomeFromSalary") != null) {
			criteria.add(Restrictions.eqOrIsNull("incomeFromSalary", Double.valueOf((String) entity.get("incomeFromSalary"))));
		}
		if (entity.get("incomeFromHouseProperty") != null) {
			criteria.add(Restrictions.eqOrIsNull("incomeFromHouseProperty", Double.valueOf((String) entity.get("incomeFromHouseProperty"))));
		}
		if (entity.get("incomeFromOtherSources") != null) {
			criteria.add(Restrictions.eqOrIsNull("incomeFromOtherSources", Double.valueOf((String) entity.get("incomeFromOtherSources"))));
		}
		if (entity.get("grossTotalIncome") != null) {
			criteria.add(Restrictions.eqOrIsNull("grossTotalIncome", Double.valueOf((String) entity.get("grossTotalIncome"))));
		}
		if (entity.get("deductionUs80C") != null) {
			criteria.add(Restrictions.eqOrIsNull("deductionUs80C", Double.valueOf((String) entity.get("deductionUs80C"))));
		}
		if (entity.get("deductionUs80CCC") != null) {
			criteria.add(Restrictions.eqOrIsNull("deductionUs80CCC", Double.valueOf((String) entity.get("deductionUs80CCC"))));
		}
		if (entity.get("deductionUs80CCD1") != null) {
			criteria.add(Restrictions.eqOrIsNull("deductionUs80CCD1", Double.valueOf((String) entity.get("deductionUs80CCD1"))));
		}
		if (entity.get("deductionUs80C80CCC80CCD1") != null) {
			criteria.add(Restrictions.eqOrIsNull("deductionUs80C80CCC80CCD1", Double.valueOf((String) entity.get("deductionUs80C80CCC80CCD1"))));
		}
		if (entity.get("deductionUs80CCD1B") != null) {
			criteria.add(Restrictions.eqOrIsNull("deductionUs80CCD1B", Double.valueOf((String) entity.get("deductionUs80CCD1B"))));
		}
		if (entity.get("deductionUs80CCD2") != null) {
			criteria.add(Restrictions.eqOrIsNull("deductionUs80CCD2", Double.valueOf((String) entity.get("deductionUs80CCD2"))));
		}
		if (entity.get("deductionUs80D") != null) {
			criteria.add(Restrictions.eqOrIsNull("deductionUs80D", Double.valueOf((String) entity.get("deductionUs80D"))));
		}
		if (entity.get("deductionUs80G") != null) {
			criteria.add(Restrictions.eqOrIsNull("deductionUs80G", Double.valueOf((String) entity.get("deductionUs80G"))));
		}
		if (entity.get("deductionUs80TTA") != null) {
			criteria.add(Restrictions.eqOrIsNull("deductionUs80TTA", Double.valueOf((String) entity.get("deductionUs80TTA"))));
		}
		if (entity.get("amountDeductible80ccg") != null) {
			criteria.add(Restrictions.eqOrIsNull("amountDeductible80ccg", Double.valueOf((String) entity.get("amountDeductible80ccg"))));
		}
		if (entity.get("anyOtherDeduction") != null) {
			criteria.add(Restrictions.eqOrIsNull("anyOtherDeduction", Double.valueOf((String) entity.get("anyOtherDeduction"))));
		}
		if (entity.get("totalDeductionUnderChapterVIA") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalDeductionUnderChapterVIA", Double.valueOf((String) entity.get("totalDeductionUnderChapterVIA"))));
		}
		if (entity.get("totalTaxableIncome") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalTaxableIncome", Double.valueOf((String) entity.get("totalTaxableIncome"))));
		}
		if (entity.get("incomeTaxOnTotalIncome") != null) {
			criteria.add(Restrictions.eqOrIsNull("incomeTaxOnTotalIncome", Double.valueOf((String) entity.get("incomeTaxOnTotalIncome"))));
		}
		if (entity.get("rebateUs87A") != null) {
			criteria.add(Restrictions.eqOrIsNull("rebateUs87A", Double.valueOf((String) entity.get("rebateUs87A"))));
		}
		if (entity.get("surcharge") != null) {
			criteria.add(Restrictions.eqOrIsNull("surcharge", Double.valueOf((String) entity.get("surcharge"))));
		}
		if (entity.get("educationCess") != null) {
			criteria.add(Restrictions.eqOrIsNull("educationCess", Double.valueOf((String) entity.get("educationCess"))));
		}
		if (entity.get("incomeTaxReliefUs89") != null) {
			criteria.add(Restrictions.eqOrIsNull("incomeTaxReliefUs89", Double.valueOf((String) entity.get("incomeTaxReliefUs89"))));
		}
		if (entity.get("netIncomeTaxPayable") != null) {
			criteria.add(Restrictions.eqOrIsNull("netIncomeTaxPayable", Double.valueOf((String) entity.get("netIncomeTaxPayable"))));
		}
		if (entity.get("tdsFromCurrentEmployer") != null) {
			criteria.add(Restrictions.eqOrIsNull("tdsFromCurrentEmployer", Double.valueOf((String) entity.get("tdsFromCurrentEmployer"))));
		}
		if (entity.get("reportedTdsFromPreviousEmployer") != null) {
			criteria.add(Restrictions.eqOrIsNull("reportedTdsFromPreviousEmployer", Double.valueOf((String) entity.get("reportedTdsFromPreviousEmployer"))));
		}
		if (entity.get("tdsFromCurrentEmployer") != null) {
			criteria.add(Restrictions.eqOrIsNull("tdsFromCurrentEmployer", Double.valueOf((String) entity.get("tdsFromCurrentEmployer"))));
		}
		if (entity.get("totalTds") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalTds", Double.valueOf((String) entity.get("totalTds"))));
		}
		if (entity.get("shortfallInTaxDeduction") != null) {
			criteria.add(Restrictions.eqOrIsNull("shortfallInTaxDeduction", Double.valueOf((String) entity.get("shortfallInTaxDeduction"))));
		}
		
		if (entity.get("whetherTaxDeductedAtHigherRate") != null) {
			criteria.add(Restrictions.eqOrIsNull("whetherTaxDeductedAtHigherRate", entity.get("whetherTaxDeductedAtHigherRate")));
		}
		if (entity.get("countOfPanOfLandlord") != null) {
			criteria.add(Restrictions.eqOrIsNull("countOfPanOfLandlord", Double.valueOf((String) entity.get("countOfPanOfLandlord"))));
		}
		if (entity.get("panOfLandlord1") != null) {
			criteria.add(Restrictions.eqOrIsNull("panOfLandlord1", entity.get("panOfLandlord1")));
		}
		if (entity.get("panOfLandlord2") != null) {
			criteria.add(Restrictions.eqOrIsNull("panOfLandlord2", entity.get("panOfLandlord2")));
		}
		if (entity.get("nameOfLandlord2") != null) {
			criteria.add(Restrictions.eqOrIsNull("nameOfLandlord2", entity.get("nameOfLandlord2")));
		}
		if (entity.get("nameOfLandlord3") != null) {
			criteria.add(Restrictions.eqOrIsNull("nameOfLandlord3", entity.get("nameOfLandlord3")));
		}
		if (entity.get("panOfLandlord3") != null) {
			criteria.add(Restrictions.eqOrIsNull("panOfLandlord3", entity.get("panOfLandlord3")));
		}
		if (entity.get("nameOfLandlord3") != null) {
			criteria.add(Restrictions.eqOrIsNull("nameOfLandlord3", entity.get("nameOfLandlord3")));
		}
		if (entity.get("panOfLandlord4") != null) {
			criteria.add(Restrictions.eqOrIsNull("panOfLandlord4", entity.get("panOfLandlord4")));
		}
		if (entity.get("nameOfLandlord4") != null) {
			criteria.add(Restrictions.eqOrIsNull("nameOfLandlord4", entity.get("nameOfLandlord4")));
		}
		if (entity.get("whetherInterestPaidToBeLender") != null) {
			criteria.add(Restrictions.eqOrIsNull("whetherInterestPaidToBeLender", entity.get("whetherInterestPaidToBeLender")));
		}
		if (entity.get("countOfPanOfLender") != null) {
			criteria.add(Restrictions.eqOrIsNull("countOfPanOfLender", entity.get("countOfPanOfLender")));
		}
		if (entity.get("panOfLender1") != null) {
			criteria.add(Restrictions.eqOrIsNull("panOfLender1", entity.get("panOfLender1")));
		}
		if (entity.get("nameOfLender1") != null) {
			criteria.add(Restrictions.eqOrIsNull("nameOfLender1", entity.get("nameOfLender1")));
		}
		if (entity.get("panOfLender2") != null) {
			criteria.add(Restrictions.eqOrIsNull("panOfLender2", entity.get("panOfLender2")));
		}
		if (entity.get("panOfLender3") != null) {
			criteria.add(Restrictions.eqOrIsNull("panOfLender3", entity.get("panOfLender3")));
		}
		if (entity.get("panOfLender4") != null) {
			criteria.add(Restrictions.eqOrIsNull("panOfLender4", entity.get("panOfLender4")));
		}
		if (entity.get("nameOfLender2") != null) {
			criteria.add(Restrictions.eqOrIsNull("nameOfLender2", entity.get("nameOfLender2")));
		}
		if (entity.get("nameOfTheSuperAnnuationFund") != null) {
			criteria.add(Restrictions.eqOrIsNull("nameOfTheSuperAnnuationFund", entity.get("nameOfTheSuperAnnuationFund")));
		}
		if (entity.get("remarks") != null) {
			criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("quarter") != null) {
			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
		}
		if (entity.get("contributionPaidByTrustees") != null) {
			criteria.add(Restrictions.eqOrIsNull("contributionPaidByTrustees", Double.valueOf((String) entity.get("contributionPaidByTrustees"))));
		}
		
		
		
		
		
		
		
		
		return (List<Regular24QSalary>) criteria.list();
	}
}
