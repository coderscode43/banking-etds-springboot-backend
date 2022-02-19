package domain.in.rjsa.model.fy;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
@Entity
@Table(name = "FYDetails.logs")
public class Logs extends CommonModelAbstract{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "username")
	public String username;
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date date;
	@Column(name = "ipaddrs")
	public String ipaddrs;
	@Column(name = "entity")
	public String entity;
//	@Column(name = "idoftheuser")
//	public Long idoftheuser;
	@Column(name = "action")
	public String action;
	@Column(name = "details")
	public String details;
	@Column(name = "fy")
	public Long fy;
	
	//Entity
	//entity
	//Change in the 
	
}
