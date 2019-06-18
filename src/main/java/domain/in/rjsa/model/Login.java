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
@Table(name = "login")
public class Login extends CommonModelAbstract{

	private static final long serialVersionUID = 8520939778549419922L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private long id;
	@Column(name = "userName")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "accessLevel")
	private int accessLevel;
	@Column(name = "enabled")
	private Boolean enabled;
	@Column(name = "employeeId")
	private long employeeId;
	@Column(name = "clientId")
	private long clientId;
	@Column(name = "passwordReset")
	private Boolean passwordReset;
	
}
