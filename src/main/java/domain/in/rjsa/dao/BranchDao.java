package domain.in.rjsa.dao;

import domain.in.rjsa.model.form.Branch;

public interface BranchDao extends DaoInterface<Long,  Branch>{
	public Branch getByKey(Long key) ;
}
