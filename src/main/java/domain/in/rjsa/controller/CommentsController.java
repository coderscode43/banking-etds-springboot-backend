package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Comments;
import domain.in.rjsa.service.CommentsService;
@Controller
@RequestMapping("/apicomments")
public class CommentsController extends AbstractControllerForm<Long, Comments, CommentsService>{
	@Autowired
	CommentsService service;
	@Override
	public CommentsService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Comments> getEntity() {
		// TODO Auto-generated method stub
		return Comments.class;
	}

}
