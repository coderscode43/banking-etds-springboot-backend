package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.CommentsDao;
import domain.in.rjsa.model.form.Comments;

@Repository("commentsDao")
public class CommentsDaoImpl extends AbstractDaoForm<Long, Comments> implements CommentsDao{

}
