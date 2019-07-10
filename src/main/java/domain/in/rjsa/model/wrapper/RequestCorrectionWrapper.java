package domain.in.rjsa.model.wrapper;

import java.util.List;
import domain.in.rjsa.model.form.Regular24Q4Challan;
import domain.in.rjsa.model.form.Regular24Q4Deductee;
import domain.in.rjsa.model.form.Regular24Q4Salary;
import domain.in.rjsa.model.form.Regular26QChallan;
import domain.in.rjsa.model.form.Regular26QDeductee;
import domain.in.rjsa.model.form.RequestCorrection;
import lombok.Data;

@Data
public class RequestCorrectionWrapper {

	public List<Regular26QDeductee> Regular26QDeducteeList;
	public List<Regular26QChallan> Regular26QChallanList;
	public List<Regular24Q4Challan> Regular24Q4ChallanList;
	public List<Regular24Q4Deductee> Regular24Q4DeducteeList;
	public List<Regular24Q4Salary> Regular24Q4SalaryList;
	public RequestCorrection reqCorrection;
	
}
