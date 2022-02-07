package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.Comments;
import domain.in.rjsa.model.fy.Regular24QDeducteeDefualt;
import domain.in.rjsa.model.fy.Regular27QDeducteeDefualt;
import lombok.Data;

@Data
public class CommentsWrapper {
	
	public Regular24QDeducteeDefualt deducteedefualt24;
	public Regular27QDeducteeDefualt deducteedefualt27;
	
	public List<Comments> listComments;
	
	
}
