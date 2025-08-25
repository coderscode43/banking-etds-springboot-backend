package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.Branch;
import lombok.Data;

@Data
public class BranchDetailWrapper {

	
	public List<Branch> branchList;
	
	public Branch branch;
	
}
