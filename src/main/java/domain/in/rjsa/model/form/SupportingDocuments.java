package domain.in.rjsa.model.form;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SupportingDocuments extends CommonModelAbstract{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4452993067071903984L;
	public Long id;		
	public Long correctionRequestId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
	public Date dateOfAddition;
	
	public String fileName;
	

}
