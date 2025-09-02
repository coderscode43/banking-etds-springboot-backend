package domain.in.rjsa.model.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmployeeMaster extends CommonModelAbstract{
	private static final long serialVersionUID = 1L;
	public Long id;		
	public Long employeeId;
	public Long clientId;	
	public Long branchId;
	public String employeeCode;
	public String employeeName;
	public String pan;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date dob;
	
	public String designation;
	public String maker;
	public String checker;
	public String status;

}
