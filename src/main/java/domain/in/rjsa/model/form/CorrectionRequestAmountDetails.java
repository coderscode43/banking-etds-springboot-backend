package domain.in.rjsa.model.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CorrectionRequestAmountDetails extends CommonModelAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452993067071903984L;
	public Long id;
	public Long correctionRequestId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
	public Date dateOfPayment;
	public Long amountPaid;
	public Long correctAmountPaid;
	public Long tds;
	public Long correctTds;
	public String pan;
	public String correctPan;
	public String sectionCode;
	public String correctSection;
	public String correctRemark;
	public String quarter;
	public String name;

}
