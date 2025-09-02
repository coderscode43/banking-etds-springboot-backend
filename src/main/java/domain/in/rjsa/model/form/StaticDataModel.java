package domain.in.rjsa.model.form;

import lombok.Data;

@Data
public class StaticDataModel extends CommonModelAbstract{
	
	private static final long serialVersionUID = 1L;
	Long id;
	String	key; 
	String value;
}
