package domain.in.rjsa.model.wrapper;

import domain.in.rjsa.model.fy.Regular24QDeductee;
import domain.in.rjsa.model.fy.Regular24QDeducteeDefualt;
import domain.in.rjsa.model.fy.Regular24QSalary;
import domain.in.rjsa.model.fy.Regular26QDeductee;
import domain.in.rjsa.model.fy.Regular26QDeducteeDefualt;
import domain.in.rjsa.model.fy.Regular27QDeductee;
import domain.in.rjsa.model.fy.Regular27QDeducteeDefualt;
import lombok.Data;

@Data
public class SalaryDetailWrapper {

	public Regular24QSalary salary;
	public Regular24QDeductee deductee;
	public Regular24QDeducteeDefualt deducteeDefault24;
	public Regular27QDeductee deductee27;
	public Regular27QDeducteeDefualt deducteeDefault27;
	public Regular26QDeductee deductee26;
	public Regular26QDeducteeDefualt deductee2;
}
