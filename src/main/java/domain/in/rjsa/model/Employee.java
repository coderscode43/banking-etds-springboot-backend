package domain.in.rjsa.model;

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
public class Employee {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "EmployeeCode")
	public Long EmployeeCode;
	@Column(name = "name")
	public String name;
	@Column(name = "pan")
	public String pan;
	@Column(name = "designation")
	public String designation;
	@Column(name = "mobile")
	public String mobile;
	@Column(name = "email")
	public String email;
	@Column(name = "address")
	public String address;
}
