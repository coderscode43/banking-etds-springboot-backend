package domain.in.rjsa.model.wrapper;

import domain.in.rjsa.model.fy.Regular24QDeductee;
import domain.in.rjsa.model.fy.Regular24QDeducteeUpdateRequestDetail;
import lombok.Data;

@Data
public class Regular24QDeducteeUpdateRequestDetailWrapper {

	public Regular24QDeducteeUpdateRequestDetail newData;
	public Regular24QDeductee oldData;
}
