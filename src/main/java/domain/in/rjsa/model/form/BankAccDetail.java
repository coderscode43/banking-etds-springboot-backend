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
	private String bankName;
	@Column(name = "acNo")
	private String acNo;
	@Column(name = "branch")
	private String branch;
	@Column(name = "ifsc")
	private String ifsc;
	@Column(name = "acType")
	private String acType;
	@Column(name = "micrNo")
	private String micrNo;
}
