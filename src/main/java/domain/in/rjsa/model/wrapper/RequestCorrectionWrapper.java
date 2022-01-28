package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.RequestCorrection;
import domain.in.rjsa.model.fy.Regular24QDeductee;
import domain.in.rjsa.model.fy.Regular24QSalary;
import domain.in.rjsa.model.fy.Regular26QDeductee;
import domain.in.rjsa.model.fy.Regular27QDeductee;
import domain.in.rjsa.model.tds.CLIENTDETAILS;
import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
import domain.in.rjsa.model.tds.GOVERNMENTDETAILS;
import domain.in.rjsa.model.tds.RESPONSIBLEPERSONEDETAILS;
import lombok.Data;

@Data
public class RequestCorrectionWrapper {

	public List<Regular26QDeductee> Regular26QDeducteeList;
	public List<Regular24QDeductee> Regular24Q4DeducteeList;
	public List<Regular24QSalary> Regular24Q4SalaryList;
	public RequestCorrection reqCorrection;
	public DEDUCTORDETAILS deductorDetails;
	public RESPONSIBLEPERSONEDETAILS respersonDetails;
	public GOVERNMENTDETAILS govtDetails;
	public CLIENTDETAILS clientDetail;
	public List<Regular27QDeductee> regular27QDeductee;

}
