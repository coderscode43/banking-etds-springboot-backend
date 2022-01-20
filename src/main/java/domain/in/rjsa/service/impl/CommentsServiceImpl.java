package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.CommentsDao;
import domain.in.rjsa.model.form.Comments;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.CommentsService;

@Transactional("transactionManager")
@Service("commentsService")
public class CommentsServiceImpl extends AbstractService<Long, Comments, CommentsDao> implements CommentsService{
	@Autowired
	CommentsDao dao;
	@Override
	public Comments getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public CommentsDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
