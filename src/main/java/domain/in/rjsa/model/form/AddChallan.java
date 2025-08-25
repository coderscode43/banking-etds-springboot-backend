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
//@Table(name = "AAACN4165C_form.addChallan")
public class AddChallan   extends CommonModelAbstract {

	private static final long serialVersionUID = 4452993067071903984L;
//@Id
//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//@GenericGenerator(name = "native", strategy = "native")
//@Column(name = "id")
public Long id;

//@Column(name = "correctionRequestId")
private Long correctionRequestId;

//@Column(name = "challanSrNo")
private String challanSrNo;
//@Column(name = "challanBsrCode")
private String challanBsrCode;
//@Column(name = "challanSection")
private String challanSection;
//@Column(name = "challanAmount")
private Double challanAmount;

//@Temporal(TemporalType.DATE)
//@Column(name = "challanDate")
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
private Date challanDate;

//@Column(name = "challanSupportingDoc")
private String ChallanSupportingDocument;

}
