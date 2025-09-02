package domain.in.rjsa.model.form;

import lombok.Data;
@Data
public class PensionMaster extends CommonModelAbstract{
	private static final long serialVersionUID = 1L;
	public Long id;		
	public Long pensionerId;
	public Long clientId;	
	public Long branchId;
	public String pensionerCode;
	public String pensionerName;
	public String address;
	public String maker;
	public String checker;
	public String status;
	

}
