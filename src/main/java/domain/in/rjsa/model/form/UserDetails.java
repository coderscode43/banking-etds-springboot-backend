package domain.in.rjsa.model.form;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
@Data
//@Entity
//@Table(name = "AAACN4165C_form.userDetails")
public class UserDetails extends CommonModelAbstract<UserDetails>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7399688719231441247L;
	//@Id
	//@Column(name = "employeeId")
	String employeeId;
	
	//@Column(name = "typeOfUser")
	String typeOfUser;
}

