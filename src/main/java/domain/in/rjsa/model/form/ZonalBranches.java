package domain.in.rjsa.model.form;

import lombok.Data;

@Data
public class ZonalBranches extends CommonModelAbstract{
	public Long id;	
	public Long clientId;	
	public Long zonalId;
	public Long branchId;
	public String zoneCode;
	public Long branchCode;
}
