package domain.in.rjsa.model.form;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
//@Entity
//@Table(name = "AAACN4165C_form.branch")
public class Branch extends CommonModelAbstract {
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	private Long id;
	//@Column(name = "roCode")
	private String roCode;
	//@Column(name = "branchCode")
	public Long branchCode;
	//@Column(name = "branchName")
	public String branchName;
	//@Column(name = "branchEmail")
	public String branchEmail;
	//@Column(name = "branchContactNo")
	public String branchContactNo;
	//@Column(name = "branchAddress")
	public String branchAddress;
	//@Column(name = "branchPinCode")
	public String branchPinCode;
	//@Column(name = "branchState")
	public String branchState;
	//@Column(name = "tan")
	private String tan;
	
}
