package domain.in.rjsa.model.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee extends CommonModelAbstract{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "employeeNo")
	@Size(min=0, max=15, message="Employee No should be 15 characters.")
	@NotNull(message = "Employee No is a required field")
	public String employeeNo;
	@Column(name = "name")
	@Size(min=0, max=45, message="Employee Name should be 45 characters.")
	@NotNull(message = "Employee Name is a required field")
	public String name;
	@Column(name = "fatherName")
	public String fatherName;
	@Column(name = "pan")
	public String pan;
	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date dob;
	@Temporal(TemporalType.DATE)
	@Column(name = "doj")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date doj;
	@Column(name = "designation")
	public String designation;
	@Column(name = "currentOffice")
	public String currentOffice;
	@Column(name = "empGrade")
	@Size(min=0, max=45, message="Employee Grade should be 45 characters.")
	@NotNull(message = "Employee Grade is a required field")
	public String empGrade;	
	@Column(name = "mobile1")
	@Size(min=0, max=10, message="Mobile Number should be 10 characters.")
	@NotNull(message = "Mobile Number is a required field")
	public String mobile1;
	@Column(name = "mobile2")
	public String mobile2;
	
	@Column(name = "email1")
	@Size(min=0, max=45, message="Invalid Emaiil Id")
	@NotNull(message = "Email is a required field")
	public String email1;
	@Column(name = "email2")
	public String email2;
	
	@Column(name = "curAddrId")
	public Long curAddrId;
	@Column(name = "perAddrId")
	public Long perAddrId;
	
	
	@Column(name = "bankId")
	public Long bankId;
}
