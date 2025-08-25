package domain.in.rjsa.model.fy;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
//@Entity
//@Table(name = "AAACN4165C_2324.logs")
public class Logs extends CommonModelAbstract{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;		
	//@Column(name = "username")
	public String username;
	//@Temporal(TemporalType.DATE)
	//@Column(name = "logsDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date logsDate;
	//@Column(name = "ipaddrs")
	public String ipaddrs;
	//@Column(name = "entity")
	public String entity;
//	//@Column(name = "idoftheuser")
//	public Long idoftheuser;
	//@Column(name = "action")
	public String action;
	//@Column(name = "details")
	public String details;
	//@Column(name = "fy")
	public String fy;
	
	//Entity
	//entity
	//Change in the 
	
}
