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
@Table(name = "AAACU3561B_form.employeeMaster")
public class EmployeeMaster extends CommonModelAbstract{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "employeeId")
	public Long employeeId;
	@Column(name = "clientId")
	public Long clientId;	
	@Column(name = "branchId")
	public Long branchId;
	@Column(name = "employeeCode")
	public String employeeCode;
	@Column(name = "employeeName")
	public String employeeName;
	@Column(name = "pan")
	public String pan;
	@Column(name = "dob")
	public Date dob;
	@Column(name = "designation")
	public String designation;
	@Column(name = "maker")
	public String maker;
	@Column(name = "checker")
	public String checker;
	@Column(name = "status")
	public String status;

}
