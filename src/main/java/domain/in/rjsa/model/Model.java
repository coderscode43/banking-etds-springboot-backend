package domain.in.rjsa.model;

import java.io.Serializable;
import java.util.Date;

public interface Model extends Serializable{
	
	public String getFomrattedDate(Date date);
}
