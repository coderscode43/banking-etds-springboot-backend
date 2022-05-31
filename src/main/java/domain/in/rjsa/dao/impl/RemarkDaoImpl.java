package domain.in.rjsa.dao.impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.RemarkDao;
import domain.in.rjsa.model.fy.Remark;

@Repository("remarkDao")
public class RemarkDaoImpl extends AbstractDaoFY<Long, Remark> implements RemarkDao{

	@Override
	public List<Remark> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Remark> search(HashMap entity, int pageNo, int noOfResult) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
