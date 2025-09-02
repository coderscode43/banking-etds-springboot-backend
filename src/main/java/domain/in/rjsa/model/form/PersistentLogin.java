package domain.in.rjsa.model.form;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PersistentLogin implements Serializable{
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String series;
	 
	    private String username;
	     
	    private String token;
	    
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	    private Date last_used;
	 
	    public String getSeries() {
	        return series;
	    }
	 
	    public void setSeries(String series) {
	        this.series = series;
	    }
	 
	    public String getUsername() {
	        return username;
	    }
	 
	    public void setUsername(String username) {
	        this.username = username;
	    }
	 
	    public String getToken() {
	        return token;
	    }
	 
	    public void setToken(String token) {
	        this.token = token;
	    }
	 
	    public Date getLast_used() {
	        return last_used;
	    }
	 
	    public void setLast_used(Date last_used) {
	        this.last_used = last_used;
	    }
}
