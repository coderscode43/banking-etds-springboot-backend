package domain.in.rjsa.model.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "AAACU3561B_form.userDetails")
public class UserDetails extends CommonModelAbstract<UserDetails>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7399688719231441247L;
	@Id
	@Column(name = "employeeId")
	String employeeId;
	
	@Column(name = "typeOfUser")
	String typeOfUser;
}

