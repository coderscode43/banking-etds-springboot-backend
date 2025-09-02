package domain.in.rjsa.model.form;
import lombok.Data;

@Data
public class OrganizationDetails extends CommonModelAbstract {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private Long id;
	public String organizationName;
	public String organizationPan;
	/*
	 * public Long clientId;
	 */
	
	
}
