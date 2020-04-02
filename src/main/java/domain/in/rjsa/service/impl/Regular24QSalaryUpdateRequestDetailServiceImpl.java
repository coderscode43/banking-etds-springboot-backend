package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.Regular24QSalaryUpdateRequestDetailDao;
import domain.in.rjsa.model.form.Regular24QSalaryUpdateRequestDetail;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.Regular24QSalaryUpdateRequestDetailService;

@Transactional
@Service("regular24QSalaryUpdateRequestDetailService")
public class Regular24QSalaryUpdateRequestDetailServiceImpl
		extends AbstractService<Long, Regular24QSalaryUpdateRequestDetail, Regular24QSalaryUpdateRequestDetailDao>
		implements Regular24QSalaryUpdateRequestDetailService {

	@Autowired
	Regular24QSalaryUpdateRequestDetailDao dao;

	@Override
	public Regular24QSalaryUpdateRequestDetailDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Regular24QSalaryUpdateRequestDetail getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
