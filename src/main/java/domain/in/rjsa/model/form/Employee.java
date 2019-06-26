package domain.in.rjsa.model.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "employeeNo")
	public String employeeNo;
	@Column(name = "name")
	public String name;
	@Column(name = "fatherName")
	public String fatherName;
	@Column(name = "pan")
	public String pan;
	@Column(name = "dob")
	public Date dob;
	@Column(name = "doj")
	public Date doj;
	@Column(name = "designation")
	public String designation;
	@Column(name = "currentOffice")
	public String currentOffice;
	@Column(name = "empGrade")
	public String empGrade;	
	@Column(name = "mobile1")
	public String mobile1;
	@Column(name = "mobile2")
	public String mobile2;
	
	@Column(name = "email1")
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
