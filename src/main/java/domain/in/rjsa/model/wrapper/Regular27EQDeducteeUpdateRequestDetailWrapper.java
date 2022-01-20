package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.Regular27EQDeductee;
import domain.in.rjsa.model.form.Regular27EQDeducteeUpdateRequestDetail;
import domain.in.rjsa.model.form.Remarks;
import lombok.Data;

@Data
public class Regular27EQDeducteeUpdateRequestDetailWrapper {

	public List<Remarks> listRemarks;
	public Regular27EQDeductee oldData;
	public Regular27EQDeducteeUpdateRequestDetail neData;
	
}
