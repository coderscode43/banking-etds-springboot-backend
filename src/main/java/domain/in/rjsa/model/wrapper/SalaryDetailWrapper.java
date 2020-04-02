package domain.in.rjsa.model.wrapper;

import domain.in.rjsa.model.form.Regular24QDeductee;
import domain.in.rjsa.model.form.Regular24QSalary;
import domain.in.rjsa.model.form.Regular26QDeductee;
import domain.in.rjsa.model.form.Regular27QDeductee;
import lombok.Data;

@Data
public class SalaryDetailWrapper {

	public Regular24QSalary salary;
	public Regular24QDeductee deductee;
	public Regular27QDeductee deductee27;
	public Regular26QDeductee deductee26;
}
