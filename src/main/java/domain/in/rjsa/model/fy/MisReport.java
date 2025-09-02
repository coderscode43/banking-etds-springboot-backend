package domain.in.rjsa.model.fy;

import java.text.ParseException;
import java.util.Date;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class MisReport extends CommonModelAbstract{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Long id;		
	public Long branchCode;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date fromDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date toDate;
	
	public String reportType;	
	public String fy;
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
