package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.RemarkDaoNew;
import domain.in.rjsa.model.form.Remark;

@Repository("RemarkDaoNew")
public class RemarkDaoNewImpl extends AbstractDaoForm<Long, Remark>  implements RemarkDaoNew {

	@Override
	public List<Remark> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Remark searchForm(Long trackId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findRemark(HashMap<String, Object> constrains, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Remark> findTransaction(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		// TODO Auto-generated method stub
		return null;
	}



}
