package domain.in.rjsa.dao;

import domain.in.rjsa.model.form.Comments;

public interface CommentsDao extends DaoInterfaceForm<Long, Comments>{
	public Comments getByKey(Long key) ;
}
