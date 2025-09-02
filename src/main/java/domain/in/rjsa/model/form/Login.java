package domain.in.rjsa.model.form;

import lombok.Data;
@Data
public class Login extends CommonModelAbstract{

	private static final long serialVersionUID = 8520939778549419922L;
	private long id;
	private String userName;
	private String password;
	private int accessLevel;
	private Boolean enabled;
	private String type;
	private long refId;
	private long clientId;
	private Boolean passwordReset;
	private Long branchCode;
	private String authtoken;
	
}
