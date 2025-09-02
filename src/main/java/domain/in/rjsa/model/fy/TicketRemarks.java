package domain.in.rjsa.model.fy;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;
@Data
public class TicketRemarks extends CommonModelAbstract{
	private static final long serialVersionUID = 1L;
	public Long id;	
	public Long ticketId;
	public String description;
	public String userName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date date;
	
}
