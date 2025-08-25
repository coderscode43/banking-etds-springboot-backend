package domain.in.rjsa.model.tds;

import java.text.ParseException;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
//@Entity
//@Table(name = "Taxo.PANITR2324")
public class PANITR2324 extends CommonModelAbstract {
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "PAN")
	public String PAN;
	//@Column(name = "PANDetail")
	public String PANDetail;
	//@Column(name = "PANVerifiedDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date PANVerifiedDate;
	

	public void setData(JSONObject dataObject) throws ParseException{
		this.PAN = (String) dataObject.get("PAN");
		this.PANDetail = (String) dataObject.get("PANDetail");

		this.PANVerifiedDate = returnDate(dataObject.get("PANVerifiedDate").toString());
	}
}
