package domain.in.rjsa.model.form;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
//@Entity
//@Table(name = "AAACN4165C_form.regularReturn")
public class RegularReturn extends CommonModelAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452993067071903984L;
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;
	//@Column(name = "fy")
	public String fy;
	//@Column(name = "tan")
	public String tan;
	//@Column(name = "quarter")
	public String quarter;
	//@Column(name = "form")
	public String form;
	//@Column(name = "branchCode")
	public String branchCode;
	//@Column(name = "addedBy")
	public String addedBy;
	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "addedOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	@NotNull(message = "Date is a required field.")
	public Date addedOn;
	//@Column(name = "latestRemark")
	public String latestRemark;
	//@Column(name = "status")
	public String status;
	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "returnFilingDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	public Date returnFilingDate;

	public void setReturnList(List<RegularReturn> list) {
		// TODO Auto-generated method stub

	}

}
