package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Comments;

public interface CommentsService extends ServiceInterfaceForm<Long, Comments>{
	public Comments getByKey(Long id);
}