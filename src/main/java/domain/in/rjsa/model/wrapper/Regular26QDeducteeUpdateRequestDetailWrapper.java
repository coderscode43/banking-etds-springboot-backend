package domain.in.rjsa.model.wrapper;

import domain.in.rjsa.model.fy.Regular26QDeductee;
import domain.in.rjsa.model.fy.Regular26QDeducteeUpdateRequestDetail;
import lombok.Data;

@Data
public class Regular26QDeducteeUpdateRequestDetailWrapper {

	public Regular26QDeducteeUpdateRequestDetail newData;
	public Regular26QDeductee oldData;
}
