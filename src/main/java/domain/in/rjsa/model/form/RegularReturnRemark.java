package domain.in.rjsa.model.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegularReturnRemark extends CommonModelAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452993067071903984L;
	public Long id;

	public Long regularReturnId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	@NotNull(message = "Date is a required field.")
	public Date addedOn;

	public String remark;

	public String addedBy;

	public String roCode;

	public String supportingDocName;

	public String remarkStatus;

}
