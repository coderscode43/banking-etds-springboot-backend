package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.ZonalBranches;
import domain.in.rjsa.model.form.Zone;
import lombok.Data;

@Data
public class BranchDetailWrapper {

	
	public Branch branch;
	public List<Branch> branchList;
	public Zone zone;
	public List<ZonalBranches> zonalBranches;
}
