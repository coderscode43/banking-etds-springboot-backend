package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.Regular24QSalary;
import domain.in.rjsa.model.form.Regular24QSalaryUpdateRequestDetail;
import domain.in.rjsa.model.form.Remarks;
import lombok.Data;

@Data
public class Regular24QSalaryUpdateRequestDetailWrapper {
	public Regular24QSalary oldData;
	public Regular24QSalaryUpdateRequestDetail newData;
	public List<Remarks> listRemarks;
}
