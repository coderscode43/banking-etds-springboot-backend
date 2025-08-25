package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.LdcusedDao;
import domain.in.rjsa.model.fy.Ldcused;

@Repository("LdcusedDao")
public class LdcusedDaoImpl extends AbstractDaoFY<String, Ldcused> implements LdcusedDao{

	@Override
	public List<Ldcused> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

}
