package domain.in.rjsa.model.form;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegularReturn extends CommonModelAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452993067071903984L;
	public Long id;
	public String fy;
	public String tan;
	public String quarter;
	public String form;
	public String branchCode;
	public String addedBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	@NotNull(message = "Date is a required field.")
	public Date addedOn;
	public String latestRemark;
	public String status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	public Date returnFilingDate;

	public void setReturnList(List<RegularReturn> list) {
		// TODO Auto-generated method stub

	}

}
