package domain.in.rjsa.model.tds;

import java.text.ParseException;
import java.util.Date;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class PANITR2324 extends CommonModelAbstract {
	public String PAN;
	public String PANDetail;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date PANVerifiedDate;
	

	public void setData(JSONObject dataObject) throws ParseException{
		this.PAN = (String) dataObject.get("PAN");
		this.PANDetail = (String) dataObject.get("PANDetail");

		this.PANVerifiedDate = returnDate(dataObject.get("PANVerifiedDate").toString());
	}
}
