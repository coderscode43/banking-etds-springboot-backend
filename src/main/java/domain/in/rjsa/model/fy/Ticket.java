package domain.in.rjsa.model.fy;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;
@Data
@Entity
@Table(name = "FYDetails.ticket")
public class Ticket extends CommonModelAbstract {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;
	@Column(name = "branchCode")
	public Long branchCode;
	@Column(name = "fy")
	public String fy;
	@Column(name = "quarter")
	// @NotNull(message = "Quarter is a required field")
	// @Size(min=0, max=45, message="Quarter length should not be more than 45
	// characters.")
	public String quarter;
	@Column(name = "form")
	// @NotNull(message = "Form is a required field")
	// @Size(min=0, max=45, message="Form length should not be more than 45
	// characters.")
	public String form;
	@Column(name = "type")
//	@NotNull(message = "Type is a required field")
//	@Size(min=0, max=45, message="Type length should not be more than 45 characters.")
	public String type;
	@Column(name = "status")
	// @NotNull(message = "Status is a required field")
	// @Size(min=0, max=45, message="Status length should not be more than 45
	// characters.")
	public String status;
	@Column(name = "description")
	// @NotNull(message = "Description is a required field")
	// @Size(min=0, max=45, message="Description length should not be more than 45
	// characters.")
	public String description;
	@Column(name = "attachment")
	// @NotNull(message = "Message is a required field")
	// @Size(min=0, max=45, message="Message length should not be more than 45
	// characters.")
	public String attachment;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfOpening")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date dateOfOpening;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfChange")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	// @NotNull(message = "Date Tax Deposit is a required field")
	public Date dateOfChange;
	@Column(name = "remarks")
//	@NotNull(message = "Remarks is a required field")
//	@Size(min=0, max=45, message="Remarks length should not be more than 45 characters.")
	public String remarks;
	@Column(name = "fileId")
	public Long fileId;
	
	@Column(name = "userName")
	public String userName;
	
	@Column(name = "resolved")
	public boolean resolved;
	
	@Column(name = "custVendId")
	@Size(min = 0, max = 15, message = "Customer/Vendor Id should not be greater than 15 characters.")
	public String custVendId;
	
	@Column(name = "pan")
	@Size(min = 0, max = 10, message = "PAN Number should not be greater than 10 characters.")
	@Pattern(regexp = "/^([A-Za-z]{5}[0-9]{4}[A-Za-z]{1})*$/", message = "PAN Number is not valid.")
	public String pan;


}
