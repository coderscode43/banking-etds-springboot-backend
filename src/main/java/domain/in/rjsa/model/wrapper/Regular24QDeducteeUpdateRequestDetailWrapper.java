package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.Regular24QDeductee;
import domain.in.rjsa.model.form.Regular24QDeducteeUpdateRequestDetail;
import domain.in.rjsa.model.form.Remarks;
import lombok.Data;

@Data
public class Regular24QDeducteeUpdateRequestDetailWrapper {

	public List<Remarks> listRemarks;
	public Regular24QDeducteeUpdateRequestDetail newData;
	public Regular24QDeductee oldData;
}
