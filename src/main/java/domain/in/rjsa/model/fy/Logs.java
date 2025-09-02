package domain.in.rjsa.model.fy;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class Logs extends CommonModelAbstract{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Long id;		
	public String username;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date logsDate;
	public String ipaddrs;
	public String entity;
//	//	public Long idoftheuser;
	public String action;
	public String details;
	public String fy;
	
	//Entity
	//entity
	//Change in the 
	
}
