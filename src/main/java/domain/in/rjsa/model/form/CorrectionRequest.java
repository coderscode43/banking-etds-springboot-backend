package domain.in.rjsa.model.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CorrectionRequest extends CommonModelAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452993067071903984L;
	public Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
	@NotNull(message = "Correction Request Date is a required field.")
	public Date correctionRequestDate;
	public Long custId;
	public String name;
	public String pan;
	public String nameOfRequest;
	public Long branchCode;
	public Long empNo;
	public Long ticketNumber;
	public String typeOfCorrection;
	public String remark;
	public String checkerApprovedBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
	public Date checkerApprovedOn;
	public String taxTeamApprovedBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
	public Date taxTeamApprovedOn;
	public String correctionBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Calcutta")
	public Date correctionOn;
	public String makerBy;
	public String status;
	public String fy;
	public String quarter;
	public boolean rejectStatus;
	public String fileName;
	public boolean correctionStatus;
	public String mobileNumber;
	public String typeOfForm;
	public String tan;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
	public Date lastUpdatedOn;
	public String reasonForExemption;

	public Boolean regenarateRequest;

	public Long newRequestTicketNo;
	
//	public Date getCorrectionRequestDate() {
//	    ZonedDateTime zonedDateTime = correctionRequestDate.toInstant().atZone(ZoneId.of("Asia/Kolkata"));
//	    return Date.from(zonedDateTime.toInstant());
//	}

}
