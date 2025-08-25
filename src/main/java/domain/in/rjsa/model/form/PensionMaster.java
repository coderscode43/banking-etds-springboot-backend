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
//@Table(name = "AAACN4165C_form.pensionMaster")
public class PensionMaster extends CommonModelAbstract{
	private static final long serialVersionUID = 1L;
	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;		
	//@Column(name = "pensionerId")
	public Long pensionerId;
	//@Column(name = "clientId")
	public Long clientId;	
	//@Column(name = "branchId")
	public Long branchId;
	//@Column(name = "pensionerCode")
	public String pensionerCode;
	//@Column(name = "pensionerName")
	public String pensionerName;
	//@Column(name = "address")
	public String address;
	//@Column(name = "maker")
	public String maker;
	//@Column(name = "checker")
	public String checker;
	//@Column(name = "status")
	public String status;
	

}
