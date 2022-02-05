package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Branch;

public interface BranchService extends ServiceInterfaceForm<Long, Branch> {

	public Branch getByKey(Long id);

}
