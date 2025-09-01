package domain.in.rjsa.model.form;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
//@Entity
//@Table(name = "AAACN4165C_form.login")
@Data
public class Login extends CommonModelAbstract{

	private static final long serialVersionUID = 8520939778549419922L;
	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	private long id;
	//@Column(name = "userName")
	private String userName;
	//@Column(name = "password")
	private String password;
	//@Column(name = "accessLevel")
	private int accessLevel;
	//@Column(name = "enabled")
	private Boolean enabled;
	//@Column(name = "type")
	private String type;
	//@Column(name = "refId")
	private long refId;
	//@Column(name = "clientId")
	private long clientId;
	//@Column(name = "passwordReset")
	private Boolean passwordReset;
	//@Column(name = "branchCode")
	private Long branchCode;
	//@Column(name = "authtoken")
//	private String authtoken;
	
}
