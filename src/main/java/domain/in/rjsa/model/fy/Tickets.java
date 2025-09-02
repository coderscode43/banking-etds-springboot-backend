package domain.in.rjsa.model.fy;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class Tickets extends CommonModelAbstract {

	public Long id;
	public Long branchCode;
	public String fy;
	// @NotNull(message = "Quarter is a required field")
	// @Size(min=0, max=45, message="Quarter length should not be more than 45
	// characters.")
	public String quarter;
	// @NotNull(message = "Form is a required field")
	// @Size(min=0, max=45, message="Form length should not be more than 45
	// characters.")
	public String form;
	//	@NotNull(message = "Type is a required field")
//	@Size(min=0, max=45, message="Type length should not be more than 45 characters.")
	public String type;
	// @NotNull(message = "Status is a required field")
	// @Size(min=0, max=45, message="Status length should not be more than 45
	// characters.")
	public String status;
	// @NotNull(message = "Description is a required field")
	// @Size(min=0, max=45, message="Description length should not be more than 45
	// characters.")
	public String description;
	// @NotNull(message = "Message is a required field")
	// @Size(min=0, max=45, message="Message length should not be more than 45
	// characters.")
	public String attachment;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	public Date dateOfOpening;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	// @NotNull(message = "Date Tax Deposit is a required field")
	public Date dateOfChange;
	//	@NotNull(message = "Remarks is a required field")
//	@Size(min=0, max=45, message="Remarks length should not be more than 45 characters.")
	public String remarks;
	public Long fileId;
	
	public String userName;
	
	public boolean resolved;
	
	@Size(min = 0, max = 15, message = "Customer/Vendor Id should not be greater than 15 characters.")
	public String custVendId;
	
	@Size(min = 0, max = 10, message = "PAN Number should not be greater than 10 characters.")
//	@Pattern(regexp = "/^([A-Z a-z]{5}[0-9]{4}[A-Z a-z]{1})*$/", message = "PAN Number is not valid.")
	public String pan;


}
