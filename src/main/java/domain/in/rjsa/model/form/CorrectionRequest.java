package domain.in.rjsa.model.form;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

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
//@Table(name = "AAACN4165C_form.correctionRequest")
public class CorrectionRequest extends CommonModelAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452993067071903984L;
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;
	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "correctionRequestDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
	@NotNull(message = "Correction Request Date is a required field.")
	public Date correctionRequestDate;
	//@Column(name = "custId")
	public Long custId;
	//@Column(name = "name")
	public String name;
	//@Column(name = "pan")
	public String pan;
	//@Column(name = "nameOfRequest")
	public String nameOfRequest;
	//@Column(name = "branchCode")
	public Long branchCode;
	//@Column(name = "empNo")
	public Long empNo;
	//@Column(name = "ticketNumber")
	public Long ticketNumber;
	//@Column(name = "typeOfCorrection")
	public String typeOfCorrection;
	//@Column(name = "remark")
	public String remark;
	//@Column(name = "checkerApprovedBy")
	public String checkerApprovedBy;
	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "checkerApprovedOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
	public Date checkerApprovedOn;
	//@Column(name = "taxTeamApprovedBy")
	public String taxTeamApprovedBy;
	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "taxTeamApprovedOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
	public Date taxTeamApprovedOn;
	//@Column(name = "correctionBy")
	public String correctionBy;
	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "correctionOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Calcutta")
	public Date correctionOn;
	//@Column(name = "makerBy")
	public String makerBy;
	//@Column(name = "status")
	public String status;
	//@Column(name = "fy")
	public String fy;
	//@Column(name = "quarter")
	public String quarter;
	//@Column(name = "rejectStatus")
	public boolean rejectStatus;
	//@Column(name = "fileName")
	public String fileName;
	//@Column(name = "correctionStatus")
	public boolean correctionStatus;
	//@Column(name = "mobileNumber")
	public String mobileNumber;
	//@Column(name = "typeOfForm")
	public String typeOfForm;
	//@Column(name = "tan")
	public String tan;
	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "lastUpdatedOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
	public Date lastUpdatedOn;
	//@Column(name = "reasonForExemption")
	public String reasonForExemption;

	//@Column(name = "regenarateRequest")
	public Boolean regenarateRequest;

	//@Column(name = "newRequestTicketNo")
	public Long newRequestTicketNo;
	
//	public Date getCorrectionRequestDate() {
//	    ZonedDateTime zonedDateTime = correctionRequestDate.toInstant().atZone(ZoneId.of("Asia/Kolkata"));
//	    return Date.from(zonedDateTime.toInstant());
//	}

}
