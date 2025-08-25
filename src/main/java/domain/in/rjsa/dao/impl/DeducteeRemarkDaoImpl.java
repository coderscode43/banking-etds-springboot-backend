package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.DeducteeRemarkDao;
import domain.in.rjsa.model.fy.DeducteeRemark;

@Repository("DeducteeRemarkDao")
public class DeducteeRemarkDaoImpl extends AbstractDaoForm<Long, DeducteeRemark> implements DeducteeRemarkDao {

	@Override
	public List<DeducteeRemark> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

}
