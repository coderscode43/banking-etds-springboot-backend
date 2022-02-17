package domain.in.rjsa.model.wrapper;

import domain.in.rjsa.model.fy.Regular27QDeductee;
import domain.in.rjsa.model.fy.Regular27QDeducteeUpdateRequestDetail;
import lombok.Data;

@Data
public class Regular27QDeducteeUpdateRequestDetailWrapper {

	public Regular27QDeductee oldData;
	public Regular27QDeducteeUpdateRequestDetail newData;
}
