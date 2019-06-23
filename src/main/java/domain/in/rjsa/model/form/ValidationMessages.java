package domain.in.rjsa.model.form;

import lombok.Data;

@Data
public class ValidationMessages {

	boolean valid=true;
	StringBuilder messages;

}
