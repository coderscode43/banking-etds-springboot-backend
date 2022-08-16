package domain.in.rjsa.exception;

import java.io.IOException;
import java.util.Set;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.google.gson.JsonSyntaxException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler{

	    @ExceptionHandler(ConstraintViolationException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public final ResponseEntity<Object> handleAllExceptions(ConstraintViolationException ex, WebRequest request) {
	    	 ValidationErrorDTO dto = new ValidationErrorDTO();
	    	Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
	    	for(ConstraintViolation constraintViolation:constraintViolations){
	    		String field="";
	    		String value="";
	    		String msg="";
	    		if(constraintViolation.getPropertyPath()!=null) {
	    			field=constraintViolation.getPropertyPath().toString();
	    		}
	    		if(constraintViolation.getInvalidValue()!=null) {
	    			value=constraintViolation.getInvalidValue().toString();
	    		}
	    		if(constraintViolation.getMessage()!=null) {
	    			msg=constraintViolation.getMessage().toString();
	    		}
	    		
	    		dto.addFieldError(field, value, msg);		
	    				
	    		
	    	}
	    	
	    return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
	}
	    
	    @ExceptionHandler(NullPointerException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public final ResponseEntity<Object> handleNullExceptions(NullPointerException ex, WebRequest request) {
	    	FieldErrorDTO dto=new FieldErrorDTO();
	    	String uri=request.getDescription(false);
	    	String[] uriSplit=uri.split("=");
	    	String[] uriParts=uriSplit[1].toString().split("/");
	    	String entityName=uriParts[2].substring(3);
	    	dto.setEntityName((entityName));
	    	dto.setExceptionMsg("Some of value provided is null.Please re-verify.");
	    	return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
	    }
	    
	    @ExceptionHandler(NumberFormatException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public final ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex, WebRequest request) {
	    	FieldErrorDTO dto=new FieldErrorDTO();
	    	String uri=request.getDescription(false);
	    	String[] uriSplit=uri.split("=");
	    	String[] uriParts=uriSplit[1].toString().split("/");
	    	String entityName=uriParts[2].substring(3);
	    	dto.setEntityName((entityName));
	    	
	    	dto.setExceptionMsg("Invalid value entered for number type.Please re-verify.");
	    	return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
	    }
	    
	    @ExceptionHandler(RuntimeException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public final ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
	    	FieldErrorDTO dto=new FieldErrorDTO();
	    	String uri=request.getDescription(false);
	    	String[] uriSplit=uri.split("=");
	    	String[] uriParts=uriSplit[1].toString().split("/");
	    	String entityName=uriParts[2].substring(3);
	    	dto.setEntityName((entityName));
	    	dto.setExceptionMsg("Something is going wrong.Please try again later.");
	    	return new ResponseEntity(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    @ExceptionHandler(InterruptedException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public final ResponseEntity<Object> handleInterruptedException(InterruptedException ex, WebRequest request) {
	    	FieldErrorDTO dto=new FieldErrorDTO();
	    	String uri=request.getDescription(false);
	    	String[] uriSplit=uri.split("=");
	    	String[] uriParts=uriSplit[1].toString().split("/");
	    	String entityName=uriParts[2].substring(3);
	    	dto.setEntityName((entityName));
	    	dto.setExceptionMsg("Ongoing operation is interupted.Please try again.");
	    	return new ResponseEntity(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    @ExceptionHandler(ArithmeticException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public final ResponseEntity<Object> handleArithmeticException(ArithmeticException ex, WebRequest request) {
	    	FieldErrorDTO dto=new FieldErrorDTO();
	    	String uri=request.getDescription(false);
	    	String[] uriSplit=uri.split("=");
	    	String[] uriParts=uriSplit[1].toString().split("/");
	    	String entityName=uriParts[2].substring(3);
	    	dto.setEntityName((entityName));
	    	dto.setExceptionMsg("Something going wrong when doing Arithmetic operations.Please re-verify.");
	    	return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
	    }
	    
	    
	    
	    @ExceptionHandler(PersistenceException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public final ResponseEntity<Object> handlePersistenceException(PersistenceException ex, WebRequest request) {
	    	FieldErrorDTO dto=new FieldErrorDTO();
	    	String uri=request.getDescription(false);
	    	String[] uriSplit=uri.split("=");
	    	String[] uriParts=uriSplit[1].toString().split("/");
	    	String entityName=uriParts[2].substring(3);
	    	dto.setEntityName((entityName));
	    	dto.setExceptionMsg("Duplicate Entry! Please re-verify.");
	    	return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
	    }
	    	    
	    
	    @ExceptionHandler(JsonSyntaxException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public final ResponseEntity<Object> handleJsonSyntaxException(JsonSyntaxException ex, WebRequest request) {
	    	FieldErrorDTO dto=new FieldErrorDTO();
	    	String uri=request.getDescription(false);
	    	String[] uriSplit=uri.split("=");
	    	String[] uriParts=uriSplit[1].toString().split("/");
	    	String entityName=uriParts[2].substring(3);
	    	dto.setEntityName((entityName));
	    	
	    	
	    	String Str=ex.getLocalizedMessage().toString();
	    	String[] arr=Str.split(":");
	    	dto.setExceptionMsg("Invalid input "+arr[1]+" : "+arr[2]);
	    	return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
	    }
	    
	    @ExceptionHandler(CustomException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public final ResponseEntity<Object> handleCustomException(CustomException ex, WebRequest request) {
	    	FieldErrorDTO dto=new FieldErrorDTO();
	    	String uri=request.getDescription(false);
	    	String[] uriSplit=uri.split("=");
	    	String[] uriParts=uriSplit[1].toString().split("/");
	    	String entityName=uriParts[2].substring(3);
	    	dto.setEntityName((entityName));
	    	String Str=ex.getLocalizedMessage().toString();
	    	dto.setExceptionMsg(Str);
	    	return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
	    }
//	    
	    
	    
	    @ExceptionHandler(IOException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public final ResponseEntity<Object> handleIOException(CustomException ex, WebRequest request) {
	    	FieldErrorDTO dto=new FieldErrorDTO();
	    	String Str=ex.getLocalizedMessage().toString();
	    	dto.setExceptionMsg(Str);
	    	return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
	    }
	    
	    @ExceptionHandler(MaxUploadSizeExceededException.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    @ResponseBody
	    public final ResponseEntity<Object> handleFileSizeException(MaxUploadSizeExceededException ex, WebRequest request) {
	    	FieldErrorDTO dto=new FieldErrorDTO();
	    	String uri=request.getDescription(false);
	    	String[] uriSplit=uri.split("=");
	    	String[] uriParts=uriSplit[1].toString().split("/");
	    	String entityName=uriParts[2].substring(3);
	    	dto.setEntityName((entityName));
	    	String Str="File size Exceeeds the allowed limit of 130Kb";
	    	dto.setExceptionMsg(Str);
	    	return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
	    }
	    
	    
	  

}