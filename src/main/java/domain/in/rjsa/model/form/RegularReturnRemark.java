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
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
//@Entity
//@Table(name = "AAACN4165C_form.regularReturnRemark")
public class RegularReturnRemark extends CommonModelAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452993067071903984L;
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;

	//@Column(name = "regularReturnId")
	public Long regularReturnId;

	//@Temporal(TemporalType.DATE)
	//@Column(name = "addedOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	@NotNull(message = "Date is a required field.")
	public Date addedOn;

	//@Column(name = "remark")
	public String remark;

	//@Column(name = "addedBy")
	public String addedBy;

	//@Column(name = "roCode")
	public String roCode;

	//@Column(name = "supportingDocName")
	public String supportingDocName;

	//@Column(name = "remarkStatus")
	public String remarkStatus;

}
