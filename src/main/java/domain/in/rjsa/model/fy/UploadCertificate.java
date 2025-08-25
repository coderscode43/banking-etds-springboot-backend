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
//@Table(name = "AAACN4165C_2324.uploadCertificate")
public class UploadCertificate extends CommonModelAbstract{
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;
//	//@Column(name = "zipFile")
//	private byte[] zipFile;
	//@Column(name = "TAN")
	public String TAN;
	//@Column(name = "fy")
	public String fy;
	//@Column(name = "quarter")
	public String quarter;
	//@Column(name = "form")
	public String form;
	//@Column(name = "fileName")
	public String fileName;
	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "uploadedTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	public Date uploadedTime;
	//@Column(name = "userName")
	public String userName;
	//@Column(name = "status")
	public String status;

}
