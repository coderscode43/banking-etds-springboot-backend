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
//@Table(name = "AAACN4165C_2324.DEDUCTEEREMARK")
public class DeducteeRemark extends CommonModelAbstract{

	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	private Long id;

	//@Column(name = "DEDUCTEEID")
	private Long DEDUCTEEID;

	//@Column(name = "ADDEDBY")
	private String ADDEDBY;

	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "DATETIME")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	private Date DATETIME;

	//@Column(name = "FORMDATA")
	private String FORMDATA;
	
	//@Column(name = "STATUS")
	private String STATUS;
	
	//@Column(name = "REMARK")
	private String REMARK;
	
	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "APPROVEDON")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	private Date APPROVEDON;
	
	//@Column(name = "APPROVEDBY")
	private String APPROVEDBY;
	
	//@Column(name = "FY")
	private String FY;
	
	//@Column(name = "DEDUCTEEFORM")
	private String DEDUCTEEFORM;
	
	//@Column(name = "BRANCHCODE")
	private String BRANCHCODE;
	
	

}
