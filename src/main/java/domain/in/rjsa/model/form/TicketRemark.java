package domain.in.rjsa.model.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class TicketRemark extends CommonModelAbstract{
	private static final long serialVersionUID = 1L;
	public Long id;	
	public Long ticketId;
	public String description;
	public String userName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date date;
	
}
