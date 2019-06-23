package domain.in.rjsa.model.form;

import java.text.SimpleDateFormat;
import java.util.Date;



public abstract class CommonModelAbstract<T extends Model> implements Model{
	/**
	 * 
	 */

	public String getFomrattedDate(Date date){
		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}
	public void setEntity(T entity){
		
	}
}
