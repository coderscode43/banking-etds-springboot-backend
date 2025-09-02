package domain.in.rjsa.model.form;

import lombok.Data;

@Data
public class Branch extends CommonModelAbstract {
	private Long id;
	private String roCode;
	public Long branchCode;
	public String branchName;
	public String branchEmail;
	public String branchContactNo;
	public String branchAddress;
	public String branchPinCode;
	public String branchState;
	private String tan;
	
}
