package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.ChallanDetailsDao;
import domain.in.rjsa.model.fy.ChallanDetails;

@Repository("challanDetailsDao")
public class ChallanDetailsDaoImpl extends AbstractDaoFY<Long, ChallanDetails> implements ChallanDetailsDao {

	@Override
	public List<ChallanDetails> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

	
}
