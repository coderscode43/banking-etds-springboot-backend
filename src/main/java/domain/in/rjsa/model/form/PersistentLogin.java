package domain.in.rjsa.model.form;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

//@Entity
//@Table(name="persistent_logins")
public class PersistentLogin implements Serializable{
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		//@Id
	    private String series;
	 
	    //@Column(name="username", unique=true, nullable=false)
	    private String username;
	     
	    //@Column(name="token", unique=true, nullable=false)
	    private String token;
	    
	    //@Temporal(TemporalType.TIMESTAMP)
	    //@Column(name="last_used")
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
