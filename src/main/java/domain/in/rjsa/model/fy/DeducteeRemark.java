package domain.in.rjsa.model.fy;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class DeducteeRemark extends CommonModelAbstract{

	private Long id;

	private Long DEDUCTEEID;

	private String ADDEDBY;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	private Date DATETIME;

	private String FORMDATA;
	
	private String STATUS;
	
	private String REMARK;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	private Date APPROVEDON;
	
	private String APPROVEDBY;
	
	private String FY;
	
	private String DEDUCTEEFORM;
	
	private String BRANCHCODE;
	
	

}
