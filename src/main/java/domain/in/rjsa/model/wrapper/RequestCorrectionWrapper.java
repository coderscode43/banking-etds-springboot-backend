package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.Regular26QChallan;
import domain.in.rjsa.model.form.Regular26QDeductee;
import domain.in.rjsa.model.form.RequestCorrection;
import lombok.Data;

@Data
public class RequestCorrectionWrapper {

	public List<Regular26QDeductee> Regular26QDeducteeList;
	public List<Regular26QChallan> Regular26QChallanList;
	public RequestCorrection reqCorrection;
	
}
