package domain.in.rjsa.model.form;
import lombok.Data;

@Data
public class Remark extends CommonModelAbstract<Remark>{
	 
	private static final long serialVersionUID = 1L;
	private Long ID; 
	
	private String DIN;
	
	private String	PAN; 
	
	private String USERNAME;
	
	private String REMARK;
	

	
	
}
