package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.fy.Regular27QDeductee;
import domain.in.rjsa.model.fy.Regular27QDeducteeUpdateRequestDetail;
import domain.in.rjsa.model.fy.Remarks;
import lombok.Data;

@Data
public class Regular27QDeducteeUpdateRequestDetailWrapper {

	public List<Remarks> listRemarks;
	public Regular27QDeductee oldData;
	public Regular27QDeducteeUpdateRequestDetail newData;
}
