package domain.in.rjsa.model.form;

import lombok.Data;
@Data
public class UserDetails extends CommonModelAbstract<UserDetails>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7399688719231441247L;
	String employeeId;
	
	String typeOfUser;
}

