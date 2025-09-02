package domain.in.rjsa.model.form;

import lombok.Data;

@Data
public class VendorMaster extends CommonModelAbstract{
	private static final long serialVersionUID = 1L;
	public Long id;		
	public Long vendorId;
	public Long clientId;	
	public Long branchId;
	public String vendorCode;
	public String vendorName;
	public String address;
	public String maker;
	public String checker;
	public String status;
	
	

}
