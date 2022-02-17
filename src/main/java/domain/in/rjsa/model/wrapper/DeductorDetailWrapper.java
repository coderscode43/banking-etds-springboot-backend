package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
import domain.in.rjsa.model.tds.RESPONSIBLEPERSONEDETAILS;
import domain.in.rjsa.model.tds.TRACESSLOGIN;
import lombok.Data;
@Data
public class DeductorDetailWrapper {
	public DEDUCTORDETAILS deductorDetails;
	public RESPONSIBLEPERSONEDETAILS respersonDetails;
	public TRACESSLOGIN traces;
	public List<Branch> listBranch;
}
