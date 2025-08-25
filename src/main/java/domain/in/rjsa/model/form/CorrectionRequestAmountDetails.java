package domain.in.rjsa.model.form;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
//@Entity
//@Table(name = "AAACN4165C_form.correctionRequestAmountDetails")
public class CorrectionRequestAmountDetails extends CommonModelAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452993067071903984L;
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long cradId;
	//@Column(name = "correctionRequestId")
	public Long correctionRequestId;
	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "dateOfPayment")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
	public Date dateOfPayment;
	//@Column(name = "amountPaid")
	public Long amountPaid;
	//@Column(name = "correctAmountPaid")
	public Long correctAmountPaid;
	//@Column(name = "tds")
	public Long tds;
	//@Column(name = "correctTds")
	public Long correctTds;
	//@Column(name = "pan")
	public String pan;
	//@Column(name = "correctPan")
	public String correctPan;
	//@Column(name = "sectionCode")
	public String sectionCode;
	//@Column(name = "correctSection")
	public String correctSection;
	//@Column(name = "correctRemark")
	public String correctRemark;
	//@Column(name = "quarter")
	public String quarter;
	//@Column(name = "name")
	public String name;

}
