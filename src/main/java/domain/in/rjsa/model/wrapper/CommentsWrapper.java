package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.Comments;
import domain.in.rjsa.model.form.Regular24QDeducteeDefualt;
import domain.in.rjsa.model.form.Regular26QDeducteeDefualt;
import domain.in.rjsa.model.form.Regular27EQDeductee;
import domain.in.rjsa.model.form.Regular27EQDeducteeDefualt;
import domain.in.rjsa.model.form.Regular27QDeducteeDefualt;
import lombok.Data;

@Data
public class CommentsWrapper {
	public Regular26QDeducteeDefualt deducteedefualt26;
	public Regular24QDeducteeDefualt deducteedefualt24;
	public Regular27QDeducteeDefualt deducteedefualt27;
	
	public List<Comments> listComments;
	
	
}
