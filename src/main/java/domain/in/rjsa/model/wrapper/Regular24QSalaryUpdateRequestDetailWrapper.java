package domain.in.rjsa.model.wrapper;

import domain.in.rjsa.model.fy.Regular24QSalary;
import domain.in.rjsa.model.fy.Regular24QSalaryUpdateRequestDetail;
import lombok.Data;

@Data
public class Regular24QSalaryUpdateRequestDetailWrapper {
	public Regular24QSalary oldData;
	public Regular24QSalaryUpdateRequestDetail newData;
}
