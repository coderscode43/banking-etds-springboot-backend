package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.ZonalBranchesDao;
import domain.in.rjsa.model.form.ZonalBranches;

@Repository("zonalBranchesDao")
public class ZonalBranchesDaoImpl extends AbstractDaoForm<Long, ZonalBranches> implements ZonalBranchesDao{

	@Override
	public List<ZonalBranches> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

}
