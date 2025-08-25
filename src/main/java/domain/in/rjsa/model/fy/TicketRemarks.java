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
//@Table(name = "AAACN4165C_2324.ticketRemark")
public class TicketRemarks extends CommonModelAbstract{
	private static final long serialVersionUID = 1L;
	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")

	//@Column(name = "id")
	public Long id;	
	//@Column(name = "ticketId")
	public Long ticketId;
	//@Column(name = "description")
	public String description;
	//@Column(name = "userName")
	public String userName;
	//@Temporal(TemporalType.DATE)
	//@Column(name = "date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date date;
	
}
