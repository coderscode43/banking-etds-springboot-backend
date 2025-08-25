package domain.in.rjsa.model.form;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
//@Entity
//@Table(name = "AAACN4165C_form.employeeMaster")
public class EmployeeMaster extends CommonModelAbstract{
	private static final long serialVersionUID = 1L;
	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;		
	//@Column(name = "employeeId")
	public Long employeeId;
	//@Column(name = "clientId")
	public Long clientId;	
	//@Column(name = "branchId")
	public Long branchId;
	//@Column(name = "employeeCode")
	public String employeeCode;
	//@Column(name = "employeeName")
	public String employeeName;
	//@Column(name = "pan")
	public String pan;
	
	//@Temporal(TemporalType.DATE)
	//@Column(name = "dob")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date dob;
	
	//@Column(name = "designation")
	public String designation;
	//@Column(name = "maker")
	public String maker;
	//@Column(name = "checker")
	public String checker;
	//@Column(name = "status")
	public String status;

}
