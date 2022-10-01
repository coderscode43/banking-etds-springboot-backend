package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.ChallanDetailsDao;
import domain.in.rjsa.model.fy.ChallanDetails;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.ChallanDetailsService;
@Transactional("transactionManager")
@Service("challanDetailsService")
public class ChallanDetailsServiceImpl extends AbstractServiceFY<Long, ChallanDetails, ChallanDetailsDao> implements  ChallanDetailsService {

	@Override
	public List<ChallanDetails> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChallanDetailsDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
