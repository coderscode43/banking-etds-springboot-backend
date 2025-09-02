package domain.in.rjsa.model.fy;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;
@Data
public class UploadCertificate extends CommonModelAbstract{
	
	public Long id;
//	//	private byte[] zipFile;
	public String TAN;
	public String fy;
	public String quarter;
	public String form;
	public String fileName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	public Date uploadedTime;
	public String userName;
	public String status;

}
