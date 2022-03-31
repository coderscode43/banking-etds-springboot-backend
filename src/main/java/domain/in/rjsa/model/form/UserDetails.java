package domain.in.rjsa.model.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;
@Data
@Entity
@Table(name = "form.userDetails")
public class UserDetails extends CommonModelAbstract<UserDetails>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7399688719231441247L;
	@Id
	@Column(name = "employeeId")
	@NotNull
	private String employeeId;
	
	@Column(name = "typeOfUser")
	@NotNull
	private String typeOfUser;
}

