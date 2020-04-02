package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.tds.CLIENTDETAILS;
import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
import domain.in.rjsa.model.tds.EFILLINGLOGIN;
import domain.in.rjsa.model.tds.GOVERNMENTDETAILS;
import domain.in.rjsa.model.tds.RESPONSIBLEPERSONEDETAILS;
import domain.in.rjsa.model.tds.TRACESSLOGIN;
import lombok.Data;
@Data
public class DeductorDetailWrapper {
	public DEDUCTORDETAILS deductorDetails;
	public RESPONSIBLEPERSONEDETAILS respersonDetails;
	public GOVERNMENTDETAILS govtDetails;
	public CLIENTDETAILS clientDetail;
	public TRACESSLOGIN traces;
	public EFILLINGLOGIN efiling;
	public List<Branch> listBranch;
}
