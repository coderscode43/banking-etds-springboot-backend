package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.CommentsDao;
import domain.in.rjsa.model.form.Comments;

@Repository("commentsDao")
public class CommentsDaoImpl extends AbstractNewDao<Long, Comments> implements CommentsDao{

}
