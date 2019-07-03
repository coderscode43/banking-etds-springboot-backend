package domain.in.rjsa.model.wrapper;


import domain.in.rjsa.model.form.Regular24Q4Challan;
import domain.in.rjsa.model.form.Regular24Q4Deductee;
import domain.in.rjsa.model.form.Regular24Q4Salary;
import domain.in.rjsa.model.form.Regular26QChallan;
import domain.in.rjsa.model.form.Regular26QDeductee;
import domain.in.rjsa.model.form.Regular27QChallan;
import domain.in.rjsa.model.form.Regular27QDeductee;
import lombok.Data;
@Data
public class SalaryDetailWrapper {
	
	public Regular24Q4Salary salary;
	public Regular24Q4Deductee deductee;
	public Regular24Q4Challan challan;
	public Regular27QChallan challan27;
	public Regular27QDeductee deductee27;
	public Regular26QChallan challan26;
	public Regular26QDeductee deductee26;
}
