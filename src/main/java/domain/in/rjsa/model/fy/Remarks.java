package domain.in.rjsa.model.fy;


import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class Remarks extends CommonModelAbstract{/**
	 * 
	 */
	private static final long serialVersionUID = 7631763008988100134L;
	
	public Long id;		
	public String fy;
	public Long deducteeId;
	public String deducteeForm;
	public String remark;
	public String dateTime;
	public String userName;
	public Long branchCode;
	public String status;
	public String fileName;

}
