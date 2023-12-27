package domain.in.rjsa.model.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "AAACU3561B_form.correctionRequest")
public class CorrectionRequest extends CommonModelAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452993067071903984L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	@NotNull(message = "Date is a required field.")
	public Date date;
	@Column(name = "custId")
	public Long custId;
	@Column(name = "nameOfCust")
	public String nameOfCust;
	@Column(name = "panOfCust")
	public String panOfCust;
	@Column(name = "nameOfRequest")
	public String nameOfRequest;
	@Column(name = "branchCode")
	public Long branchCode;
	@Column(name = "empNo")
	public Long empNo;
	@Column(name = "ticketNumber")
	public Long ticketNumber;
	@Column(name = "typeOfCorrection")
	public String typeOfCorrection;
	@Column(name = "remark")
	public String remark;
	@Column(name = "checkerApprovedBy")
	public String checkerApprovedBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "checkerApprovedOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date checkerApprovedOn;
	@Column(name = "taxTeamApprovedBy")
	public String taxTeamApprovedBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "taxTeamApprovedOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date taxTeamApprovedOn;
	@Column(name = "correctionBy")
	public String correctionBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "correctionOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date correctionOn;
	@Column(name = "makerBy")
	public String makerBy;
	@Column(name = "status")
	public String status;
	@Column(name = "fy")
	public String fy;
	@Column(name = "quarter")
	public String quarter;
	@Column(name = "rejectStatus")
	public boolean rejectStatus;

}
