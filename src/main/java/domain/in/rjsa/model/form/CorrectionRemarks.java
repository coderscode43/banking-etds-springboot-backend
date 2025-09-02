package domain.in.rjsa.model.form;


import lombok.Data;

@Data
public class CorrectionRemarks extends CommonModelAbstract{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4452993067071903984L;
	public Long id;	
	
	public Long correctionRequestId;
	
	public String dateTime;
	
	public String correctionRemark;
	
	public String addedBy;
	
	public Long branchCode;
	
	public String supportingDocName;
	
	public String remarkStatus;
	

}
