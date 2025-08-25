package domain.in.rjsa.exception;

import lombok.Data;

@Data
public class FieldErrorDTO {

	private String fieldName;
	
	private String fieldValue;
	 
    private String message;
    
    private String exceptionMsg;
    
    private String entityName;
     
 
    public FieldErrorDTO(String fieldName, String fieldValue, String message) {
        this.fieldName = fieldName;
        this.fieldValue=fieldValue;
        this.message = message;
    }
    public FieldErrorDTO() {
    	
    }
}
