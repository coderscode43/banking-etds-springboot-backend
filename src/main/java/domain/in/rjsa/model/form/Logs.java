package domain.in.rjsa.model.form;

import java.time.LocalDateTime;
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

import lombok.Data;

@Data
@Entity
@Table(name = "logs")
public class Logs extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "username")
	public String username;
	@Temporal(TemporalType.DATE)
//	@Column(name = "date")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date date;
	@Column(name = "ipaddrs")
	public String ipaddrs;
	@Column(name = "entity")
	public String entity;
//	@Column(name = "idoftheuser")
//	public Long idoftheuser;
	@Column(name = "action")
	public String action;
	
	
	//Entity
	//entity
	//Change in the 
	
}
