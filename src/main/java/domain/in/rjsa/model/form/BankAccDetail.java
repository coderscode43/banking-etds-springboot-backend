package domain.in.rjsa.model.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "bankAccDetail")
public class BankAccDetail extends CommonModelAbstract {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;
	@Column(name = "clientId")
	private Long clientId;
	@Column(name = "bankName")
	@NotBlank(message = "Bank Name is a required field.")
	@Size(min=1, max=75, message="Bank Name length should be between 1 to 75 characters.")
	@Pattern(regexp = "^[\\sA-Za-z&-.]*$",message="Bank Name is not valid.")
	private String bankName;
	@Column(name = "acNo")
	@NotBlank(message = "Account No. is a required field.")
	@Size(min=1, max=25, message="Account No. length should be between 1 to 25 digits.")
	@Pattern(regexp = "^[0-9]{4,25}",message="Account number is not valid.Enter numbers only.")
	private String acNo;
	@Column(name = "branch")
	@NotBlank(message = "Branch is a required field.")
	@Size(min=1, max=75, message="Branch length should be between 1 to 75 characters.")
	@Pattern(regexp = "^[\\sA-Za-z]*$",message="Branch is not valid.")
	private String branch;
	@Column(name = "ifsc")
	@NotBlank(message = "IFSC is a required field.")
	@Size(min=1, max=11, message="IFSC length should be between 1 to 11 characters.")
	@Pattern(regexp = "^[0-9A-Za-z]{5,11}",message="IFSC is not valid.")
	private String ifsc;
	@Column(name = "acType")
	@NotBlank(message = "Account Type is a required field.")
	@Size(min=1, max=50, message="Account Type length should be between 1 to 50 characters.")
	private String acType;
	@Column(name = "micrNo")
	@Size(min=0, max=25, message="MICR No length should be between 1 to 25 characters.")
	@Pattern(regexp = "^[0-9]{5,25}|^$",message="MICR is not valid.")
	private String micr;
}
