package domain.in.rjsa.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ValidationErrorDTO {
	private List<FieldErrorDTO> fieldErrors = new ArrayList<>();
	 
    public ValidationErrorDTO() {
 
    }
 
    public void addFieldError(String path,String value, String message) {
        FieldErrorDTO error = new FieldErrorDTO(path,value, message);
        fieldErrors.add(error);
    }
}
