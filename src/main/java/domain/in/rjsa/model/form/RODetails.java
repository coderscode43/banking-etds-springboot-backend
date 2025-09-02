package domain.in.rjsa.model.form;

import lombok.Data;
@Data
public class RODetails extends CommonModelAbstract {
	

	private static final long serialVersionUID = 1L;
	private Long id;
		private String roCode;
		private String roName;
		private String roAddress;
		private String roState;
		private String roPincode;
		private String roEmail;
		
}
