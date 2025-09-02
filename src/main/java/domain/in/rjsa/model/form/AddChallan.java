package domain.in.rjsa.model.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AddChallan   extends CommonModelAbstract {

	private static final long serialVersionUID = 4452993067071903984L;
public Long id;

private Long correctionRequestId;

private String challanSrNo;
private String challanBsrCode;
private String challanSection;
private Double challanAmount;

@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
private Date challanDate;

private String ChallanSupportingDocument;

}
