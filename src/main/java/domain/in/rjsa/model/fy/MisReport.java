package domain.in.rjsa.model.fy;

import java.text.ParseException;
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
import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
//@Entity
//@Table(name = "AAACN4165C_2324.misReport")
public class MisReport extends CommonModelAbstract{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;		
	//@Column(name = "branchCode")
	public Long branchCode;
	//@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	//@Column(name = "fromDate")
	private Date fromDate;
	//@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	//@Column(name = "toDate")
	private Date toDate;
	
	//@Column(name = "reportType")
	public String reportType;	
	//@Column(name = "fy")
	public String fy;
	//@Column(name = "userName")
	public String userName;
	
	
	public void setData(JSONObject dataObject) throws ParseException {
		this.branchCode = Long.valueOf(dataObject.get("BRANCHCODE").toString());
		this.reportType = dataObject.get("REPORTTYPE").toString();
		this.fy = dataObject.get("FY").toString();
		this.userName = dataObject.get("USERNAME").toString();

		this.fromDate = returnDate(dataObject.get("FROMDATE").toString());
		this.toDate = returnDate(dataObject.get("TODATE").toString());
	}

}
