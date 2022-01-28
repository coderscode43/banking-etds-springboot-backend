package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.fy.Regular26QDeductee;
import domain.in.rjsa.model.fy.Regular26QDeducteeUpdateRequestDetail;
import domain.in.rjsa.model.fy.Remarks;
import lombok.Data;

@Data
public class Regular26QDeducteeUpdateRequestDetailWrapper {

	public List<Remarks> listRemarks;
	public Regular26QDeducteeUpdateRequestDetail newData;
	public Regular26QDeductee oldData;
}
