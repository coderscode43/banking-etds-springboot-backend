package domain.in.rjsa.model.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "bankAccDetail")
public class BankAccDetail extends CommonModelAbstract {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;
	@Column(name = "clientId")
	private Long clientId;
	@Column(name = "bankName")
	//@Size(min=0, max=45, message="Bank Name should be 45 characters.")
	private String bankName;
	@Column(name = "acNo")
	//@Size(min=0, max=45, message="Account No should be 45 characters.")
	private String acNo;
	@Column(name = "branch")
	//@Size(min=0, max=45, message="Branch should be 45 characters.")
	private String branch;
	@Column(name = "ifsc")
	//@Size(min=0, max=45, message="IFSC Code should be 45 characters.")
	private String ifsc;
	@Column(name = "acType")
//	@Size(min=0, max=45, message="Account Type should be 45 characters.")
	private String acType;
	@Column(name = "micrNo")
	//@Size(min=0, max=45, message="MICR No should be 45 characters.")
	private String micrNo;
}
